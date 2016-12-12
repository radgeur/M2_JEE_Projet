package rezozio.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rezozio.Application;
import rezozio.Entity.Fusion;
import rezozio.Entity.Hashtag;
import rezozio.Entity.Message;
import rezozio.Entity.MessageAffiche;
import rezozio.Entity.User;
import rezozio.Repository.FusionRepository;
import rezozio.Repository.HashtagRepository;
import rezozio.Repository.MessageRepository;
import rezozio.Repository.UserRepository;

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

    //Lors de l'appui sur le bouton "login"
    @RequestMapping("/login.html")
    public String loginForm(Model model){
    	model.addAttribute("message", "");
    	return "login";
    }

    //Lors de l'appui sur le bouton "inscription"
    @RequestMapping("/inscription.html")
    public String inscriptionForm(Model model){
    	model.addAttribute("message", " ");
    	return "inscription";
    }

    //Lors de la validation du formulaire de login
    @PostMapping("/connection")
    public String connection(@ModelAttribute User user, Model model){
    	User userFind = ur.findByLogin(user.getLogin());
    	if(userFind != null)
    		if(user.getPassword().equals(userFind.getPassword())) {
    			Application.userConnected = userFind;
    			return "redirect:/profils/" + user.getLogin();
    		}
    		else {
    			model.addAttribute("message", "Mot de passe incorrect.");
        		return "login";
    		}
    	else {
    		model.addAttribute("message", "Login inexistant.");
    		return "login";
    	}
    }

    //Lors de la validation du formulaire d'inscription
    @PostMapping("/inscription")
    public String inscription(@ModelAttribute User user, Model model) {
    	User userFind = ur.findByLogin(user.getLogin());
    	if(user.getLogin().equals("") || user.getPassword().equals("")) {
    		model.addAttribute("message", "Les champs login et password ne peuvent pas être vide.");
    		return "inscription";
    	}
    	if(userFind != null) {
    		model.addAttribute("message", "Login déjà existant");
    		return "inscription";
    	}
    	else {
    		ur.save(user);
    		Application.userConnected = user;
    		return "redirect:/profils/" + user.getLogin();
    	}
    }

    //Lors de la déconnection
    @RequestMapping(value = "/disconnection")
    public String disconnection(Model model){
    	Application.userConnected = null;
    	/*model.addAttribute("login", "");
    	model.addAttribute("message", "coucou");*/
    	return "redirect:/";
    }

    //Appelé lorsque l'utilisateur se connecte OU lorsqu'il envoi un message
    @PostMapping("/logged.html")
    public String publishedMessage( @ModelAttribute Message message,Model model)
    {

        model.addAttribute("connected", true);
        model.addAttribute("login", Application.userConnected.getLogin());

          //On crée le message et on l'enregistre en BDD
          Message newMessage = new Message(Application.userConnected.getId(),message.gettexteMessage());
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
          model.addAttribute("login", Application.userConnected.getLogin());
          model.addAttribute("message", list);
          model.addAttribute("page", "Derniers messages");

        return "index";
    }


    @RequestMapping("profils/{userName}")
    public String profil(Model model, @PathVariable("userName") String userName){
    	if(Application.userConnected != null)
    		model.addAttribute("connected", true);

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

        return "profil";

    }

    //Méthode qui recupère les hashtags dans un message
   private ArrayList<Hashtag> getHashtagsMessage(String message){
       ArrayList<Hashtag> list = new ArrayList<Hashtag>();
			 int j;
			 String tmp ="";
       //Pour que la boucle while se termine bien
       message += " ";

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
      return list;
   }

}
