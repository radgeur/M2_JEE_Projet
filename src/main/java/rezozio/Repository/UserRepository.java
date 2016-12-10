package rezozio.Repository;

import org.springframework.data.repository.CrudRepository;

import rezozio.Entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    //Chercher un utilisateur par son login
    User findByLogin(String login);

    //Chercher un utilisateur par son id
    User findById(Long id);


}
