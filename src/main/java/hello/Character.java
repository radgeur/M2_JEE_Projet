package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Character{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    protected Character() {}

    public Character(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Character[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public void setFirstName(String firstName){
      this.firstName = firstName;
    }

    public void setLastName(String lastName){
      this.lastName = lastName;
    }

    public String getFirstName(){
      return this.firstName;
    }

    public String getLastName(){
      return this.lastName;
    }

}
