package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class CharacterController {
    @Autowired
    private CharacterRepository repository;

    //Lister tous les personnages
    @RequestMapping(value="/characters")
    public Iterable<Character> getAllCharacters(@RequestParam(value="name",defaultValue="null") String name) {
        if(name.equals("null")){
          return repository.findAll();
        } else {
          return repository.findByLastName(name);
        }
    }

    //Enregistrer un nouveau personnage
    @RequestMapping(value="/characters/",method=RequestMethod.POST)
    public void addCharacter(@RequestBody Character c){
        repository.save(c);
    }

    //Récupérer un personnage en fonction de son id
    @RequestMapping(value="/characters/{id}")
    public Character getCharacter(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    //Modifier un personnage existant
    @RequestMapping(value="/characters/{id}",method=RequestMethod.PUT)
    public void modifyCharacter(@PathVariable("id") Long id, @RequestParam String firstName, @RequestParam String lastName) {
        Character c = repository.findOne(id);
        c.setLastName(lastName);
        c.setFirstName(firstName);
        repository.save(c);
    }

}
