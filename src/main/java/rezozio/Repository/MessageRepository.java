package rezozio.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import rezozio.Entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

    //Liste des messages par idUser
    List<Message> findByIdUser(Long idUser);

    //Message par id
    Message findById(Long id);


}
