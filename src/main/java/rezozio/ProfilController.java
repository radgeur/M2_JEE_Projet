package rezozio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.ArrayList;

@Controller
public class ProfilController {

    @Autowired
    private UserRepository ur;
    @Autowired
    private MessageRepository mr;
    @Autowired
    private FusionRepository fr;
    @Autowired
    private HashtagRepository hr;

    //Appelé lorsque l'utilisateur se connecte OU lorsqu'il envoi un message
    @PostMapping("/logged.html")
    public String connection(@ModelAttribute User user, @ModelAttribute Message message,Model model)
    {
        //Si la page est appelé lors de la connexion -> On a les infos dans user
        if(user.getLogin() != null){
          //On vérifie que les identifiants lors du login sont bons

        } else {
        //Sinon lorsque l'utilisateur envoi un message -> On a les infos dans message

          //On crée le message et on l'enregistre en BDD
          //Premier paramètre = "ID DE LUSER DE LA SESSION" -> Il faudra modifier
          Message newMessage = new Message(1L,message.gettexteMessage());
          this.mr.save(newMessage);

          //On recupère les hashtags du message
          ArrayList<Hashtag> listehashtags = getHashtagsMessage(newMessage.gettexteMessage());

          //Pour chaque hashtag on l'ajoute dans la BDD et on le fusionne au message dans la BDD
          for(Hashtag h : listehashtags){
            //On vérifie que le hashtag n'existe pas déjà dans la BDD
            if(this.hr.findByTexteHashtag(h.gettexteHashtag()) == null){
              this.hr.save(h);
            } else {
              //Le hashtag existe, on le récupère
              h = this.hr.findByTexteHashtag(h.gettexteHashtag());
            }
            Fusion f = new Fusion(newMessage.getId(), h.getId());
            this.fr.save(f);
          }

        }
          //Pour chaque message, on recupère l'User, le message, les hashtags du message et on les affiche
          ArrayList<MessageAffiche> list = new ArrayList<MessageAffiche>();

          for(Message m : this.mr.findAll()) {
            //On recupère l'user du message
            User u = this.ur.findById(m.getIdUser());

            //On recupère la liste des Hashtags du message grâce à la table Fusion
            ArrayList<Fusion> listFusion = this.fr.findByIdMessage(m.getId());
            ArrayList<Long> listHTID = new ArrayList<Long>();
            for(Fusion f : listFusion){
              listHTID.add(f.getidHashtag());
            }

            ArrayList<Hashtag> listHT = new ArrayList<Hashtag>();

            //Pour chaque id hashtag on recupère le hashtag)
            for(Long l : listHTID) {
              listHT.add(this.hr.findById(l));
            }

            //On ajoute le message aux messages à afficher
            MessageAffiche ma = new MessageAffiche(u.getLogin(),m.gettexteMessage(),listHT);
            list.add(ma);
          }

          //On passe au model en attributs la liste des messages avec pour chaque message, l'user et les hashtags
          // LOGIN DE LA SESSION ICI-> Il faudra modifier
          model.addAttribute("login", "LOGIN DE LA SESSION ICI");
          model.addAttribute("message", list);
          model.addAttribute("page", "Derniers messages");

        return "logged";
    }


    @RequestMapping("profils/{userName}")
    public String profil(Model model, @PathVariable("userName") String userName){
        //On recupère les informations de l'user
        User user = this.ur.findByLogin(userName);

        //Infos du profil
        model.addAttribute("login", user.getLogin());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("idTwitter", user.getIdTwitter());

        //Messages de l'user
        List<Message> listMessages = this.mr.findByIdUser(user.getId());

        //Liste des messages à afficher
        ArrayList<MessageAffiche> list = new ArrayList<MessageAffiche>();

        for(Message m : listMessages) {

          //On recupère la liste des Hashtags du message grâce à la table Fusion
          ArrayList<Fusion> listFusion = this.fr.findByIdMessage(m.getId());
          ArrayList<Long> listHTID = new ArrayList<Long>();
          for(Fusion f : listFusion){
            listHTID.add(f.getidHashtag());
          }

          ArrayList<Hashtag> listHT = new ArrayList<Hashtag>();

          //Pour chaque id hashtag on recupère le hashtag)
          for(Long l : listHTID) {
            listHT.add(this.hr.findById(l));
          }

          //On ajoute le message aux messages à afficher
          MessageAffiche ma = new MessageAffiche(user.getLogin(),m.gettexteMessage(),listHT);
          list.add(ma);
        }

        model.addAttribute("message", list);
        model.addAttribute("page", "Derniers messages");

        return "profil";

    }

    //Méthode qui recupère les hashtags dans un message
   private ArrayList<Hashtag> getHashtagsMessage(String message){
       ArrayList<Hashtag> list = new ArrayList<Hashtag>();
			 int j;
			 String tmp ="";
       //Pour que la boucle while se termine bien
       message += " ";

       System.out.println("teeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeseeeeeeeeeeeeeeeeee----------------------------------------------------------");
		     for(int i = 0; i < message.length() ; i++){
		       tmp="";
		       if(message.charAt(i) == '#'){
		    	   j = i+1;
		    	   while(message.charAt(j) != ' '){
		    		   tmp += message.charAt(j);
		    		   j++;
		    	   }
		    	   list.add(new Hashtag(tmp));
		       }
		     }
         System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa----------------------------------------------------------");
      return list;
   }

}
