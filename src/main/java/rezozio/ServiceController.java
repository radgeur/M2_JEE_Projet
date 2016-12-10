package rezozio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.ArrayList;

//RestController servant à récupérer les informations sous forme de web service
@RestController
@RequestMapping("/restData")
public class ServiceController {

    @Autowired
    private UserRepository ur;
    @Autowired
    private MessageRepository mr;
    @Autowired
    private FusionRepository fr;
    @Autowired
    private HashtagRepository hr;


    //Récupération de la liste des utilisateurs
    @RequestMapping("/users")
    public ArrayList<User> users(){
          return (ArrayList<User>)this.ur.findAll();
    }

    //Récupération de la liste des messages
    @RequestMapping("/messages")
    public ArrayList<Message> messages(){
          return (ArrayList<Message>)this.mr.findAll();
    }

    //Récupération de la liste des hashtags
    @RequestMapping("/hashtags")
    public ArrayList<Hashtag> hashtags(){
          return (ArrayList<Hashtag>)this.hr.findAll();
    }

    //Récupération de la correspondance Message/Hashtags
    @RequestMapping("/messagesHashtags")
    public ArrayList<MessageHashtag> messagesHashtags(){
          //Pour chaque message, on recupère ses Hashtags
          ArrayList<MessageHashtag> listAffiche = new ArrayList<MessageHashtag>();

          ArrayList<Message> listMessages = (ArrayList<Message>)this.mr.findAll();

          for(Message m : listMessages){
            ArrayList<Fusion> listFusion = this.fr.findByIdMessage(m.getId());
            ArrayList<Hashtag> listHashtags = new ArrayList<Hashtag>();
            //Pour chaque fusion, on recupère le hashtag qu'on ajoute à la liste
            for(Fusion f : listFusion){
              Long idHT = f.getidHashtag();
              Hashtag ht = this.hr.findById(idHT);
              listHashtags.add(ht);
            }

            listAffiche.add(new MessageHashtag(m.gettexteMessage(),listHashtags));
          }

          return listAffiche;
    }

    //Récupérer les informations d'un utilisateur
    @RequestMapping("/user")
    public User userInfo(@RequestParam(value="login", defaultValue="") String login) {
         return this.ur.findByLogin(login);
     }

    //Récupérer les messages d'un utilisateur
    @RequestMapping("/messagesUser")
    public List<Message> messagesUser(@RequestParam(value="login", defaultValue="") String login) {
         User u = this.ur.findByLogin(login);
         return this.mr.findByIdUser(u.getId());
     }

    //Récupérer les hashtags utilisés par un utilisateur
    @RequestMapping("/hashtagsUser")
    public ArrayList<Hashtag> hashtagsUser(@RequestParam(value="login", defaultValue="") String login) {
         User u = this.ur.findByLogin(login);
         List<Message> listMessages = this.mr.findByIdUser(u.getId());

         //On recupère les hashtags de sa liste de messages
         ArrayList<Hashtag> listAffiche = new ArrayList<Hashtag>();

         for(Message m : listMessages){
           ArrayList<Fusion> listFusion = this.fr.findByIdMessage(m.getId());
           //Pour chaque fusion, on recupère le hashtag qu'on ajoute à la liste
           for(Fusion f : listFusion){
             Long idHT = f.getidHashtag();
             Hashtag ht = this.hr.findById(idHT);
             //On ajoute le hashtag à la liste s'il n'y est pas encore
             if(!listAffiche.contains(ht)){
                listAffiche.add(ht);
             }
           }
        }
        return listAffiche;
     }

    //Récupérer les messages contenant le hashtag en paramètre
    @RequestMapping("/hashtag")
    public ArrayList<Message> hashtag(@RequestParam(value="hashtag", defaultValue="") String hashtag) {
           ArrayList<Fusion> listFusion = this.fr.findByIdHashtag(this.hr.findByTexteHashtag(hashtag).getId());
           ArrayList<Message> listMessages = new ArrayList<Message>();

           //Pour chaque fusion, on recupère le message qu'on ajoute à la liste
           for(Fusion f : listFusion){
             Long idMessage = f.getidMessage();
             Message m = this.mr.findById(idMessage);
             //On ajoute le message à la liste s'il n'y est pas encore
             if(!listMessages.contains(m)){
                listMessages.add(m);
             }
           }

        return listMessages;
     }


}
