package rezozio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;
    private String email;
    private String photo;
    private String idFacebook;
    private String idTwitter;
    private String idLinkedin;


    protected User() {}

    public User(String login, String password, String email, String photo, String idFacebook, String idTwitter, String idLinkedin) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.idFacebook = idFacebook;
        this.idTwitter = idTwitter;
        this.idLinkedin = idLinkedin;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, pseudo='%s', email='%s']",
                id, login, email);
    }

    public Long getId(){
      return id;
    }

    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}

	public String getIdTwitter() {
		return idTwitter;
	}

	public void setIdTwitter(String idTwitter) {
		this.idTwitter = idTwitter;
	}

	public String getIdLinkedin() {
		return idLinkedin;
	}

	public void setIdLinkedin(String idLinkedin) {
		this.idLinkedin = idLinkedin;
	}

}
