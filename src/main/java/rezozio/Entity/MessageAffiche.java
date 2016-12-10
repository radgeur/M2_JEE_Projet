package rezozio.Entity;


import java.util.ArrayList;


public class MessageAffiche{

    private String user;
    private String texteMessage;
    private ArrayList<Hashtag> listHT;

    public MessageAffiche(String user, String texteMessage, ArrayList<Hashtag> lht) {
        this.user = user;
        this.texteMessage = texteMessage;
        this.listHT = lht;
    }

}
