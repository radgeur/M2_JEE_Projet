package rezozio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    //Chercher un utilisateur par son login
    User findByLogin(String login);

    //Chercher un utilisateur par son id
    User findById(Long id);


}
