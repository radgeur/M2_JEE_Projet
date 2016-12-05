package rezozio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fusion{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long idMessage;
    private Long idHashtag;

    protected Fusion() {}

    public Fusion(Long idMessage, Long idHashtag) {
        this.idMessage = idMessage;
        this.idHashtag = idHashtag;
    }

    @Override
    public String toString() {
        return String.format(
                "Fusion[id=%d, idMessage='%d', idHashtag='%d']",
                id, idMessage, idHashtag);
    }

    public Long getidMessage(){
      return idMessage;
    }

    public void setidMessage(Long idMessage){
      this.idMessage = idMessage;
    }

    public Long getidHashtag(){
      return idHashtag;
    }

    public void setidHashtag(Long idHashtag){
      this.idHashtag = idHashtag;
    }
}
