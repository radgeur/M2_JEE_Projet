package rezozio.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long  id;
    private Long idUser;
    private String texteMessage;


    protected Message() {}

    public Message(Long idUser, String texteMessage) {
        this.idUser = idUser;
        this.texteMessage = texteMessage;
    }

    public Long getId(){
      return this.id;
    }


    @Override
    public String toString() {
        return String.format(
                "Message[id=%d, idUser=%d, message='%s']",
                id, idUser, texteMessage);
    }

    public Long getIdUser(){
      return idUser;
    }

    public void setIdUser(Long idUser){
      this.idUser = idUser;
    }

    public String gettexteMessage(){
      return texteMessage;
    }

    public void settexteMessage(String message){
      this.texteMessage = message;
    }


}
