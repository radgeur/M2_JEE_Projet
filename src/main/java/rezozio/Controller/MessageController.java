package rezozio.Controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    	if(Application.userConnected != null) {
    		model.addAttribute("connected", true);
    		model.addAttribute("login", Application.userConnected.getLogin());
    	}
    	else
    		model.addAttribute("login", "");

        //Sur l'index,on affiche la liste de tous les messages
        //Pour chaque message, on recupère l'User, le message, les hashtags du message et on les affichent
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

        return "index";
    }


    @RequestMapping("hashtags/{hashtagName}")
    public String hashtags(Model model, @PathVariable("hashtagName") String HTName){
    	if(Application.userConnected != null) {
    		model.addAttribute("connected", true);
    		model.addAttribute("login", Application.userConnected.getLogin());
    	}
    	else
    		model.addAttribute("login", "");

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

    @RequestMapping("/data")
    public String data(Model model)
    {
      //Récupération de la liste des Users
      model.addAttribute("users",this.ur.findAll());
      //Récupération de la liste des hashtags
      model.addAttribute("hashtags", this.hr.findAll());

      return "rest";


    }
}
