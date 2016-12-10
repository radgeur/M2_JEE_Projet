package rezozio;

import java.util.ArrayList;

//Classe représentant un message avec la liste de ses hashtags

public class MessageHashtag{

    private String message;
    private ArrayList<Hashtag> listHT;

    public MessageHashtag(String message, ArrayList<Hashtag> listHT) {
        this.message = message;
        this.listHT = listHT;
    }

    public String getMessage(){
      return message;
    }

    public ArrayList<Hashtag> getListHT(){
      return listHT;
    }


}
