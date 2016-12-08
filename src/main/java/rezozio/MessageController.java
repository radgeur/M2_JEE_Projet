package rezozio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.ArrayList;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository mr;
    @Autowired
    private UserRepository ur;
    @Autowired
    private FusionRepository fr;
    @Autowired
    private HashtagRepository hr;

    @RequestMapping("/")
    public String index(Model model)
    {
        //Sur l'index,on affiche la liste de tous les messages
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
        model.addAttribute("message", list);
        model.addAttribute("page", "Derniers messages");

        return "index";
    }


    @RequestMapping("hashtags/{hashtagName}")
    public String hashtags(Model model, @PathVariable("hashtagName") String HTName){
      //Liste des messages qui seront affichés
      ArrayList<MessageAffiche> list = new ArrayList<MessageAffiche>();

      //On recupère le hashtag correspondant au paramètre
      Hashtag ht = this.hr.findByTexteHashtag(HTName);

      //On recupère les id messages correspondant au hashtag
      ArrayList<Fusion> lf = this.fr.findByIdHashtag(ht.getId());
      ArrayList<Long> listIdMessage = new ArrayList<Long>();
      for(Fusion f : lf){
        listIdMessage.add(f.getidMessage());
      }

      //On recupère les messages grâce à l'ID, et
      for(Long l : listIdMessage) {
        Message m = this.mr.findById(l);
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
        for(Long l2 : listHTID) {
          listHT.add(this.hr.findById(l2));
        }

        //On ajoute le message aux messages à afficher
        MessageAffiche ma = new MessageAffiche(u.getLogin(),m.gettexteMessage(),listHT);
        list.add(ma);
      }

      //On passe au model en attributs la liste des messages avec pour chaque message, l'user et les hashtags
      model.addAttribute("message", list);
      model.addAttribute("page", "Hashtag : "+HTName);

      return "hashtag";
    }
}
