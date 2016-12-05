package rezozio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

    //Liste des messages par User
    List<Message> findByIdUser(Long idUser);

}
