package rezozio.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hashtag{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long  id;
    private String texteHashtag;

    protected Hashtag() {}

    public Hashtag(String texteHashtag) {
        this.texteHashtag = texteHashtag;
    }

    @Override
    public String toString() {
        return String.format(
                "Hastag[id=%d,  hastag='%s']",
                id, texteHashtag);
    }

    public Long getId(){
      return this.id;
    }

    public String gettexteHashtag(){
      return texteHashtag;
    }

    public void settexteHashtags(String hashtag){
      this.texteHashtag = hashtag;
    }


}
