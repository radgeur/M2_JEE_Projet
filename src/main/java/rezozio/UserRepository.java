package projet;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    //Chercher un utilisateur par son login
    List<User> findByLogin(String login);
}
