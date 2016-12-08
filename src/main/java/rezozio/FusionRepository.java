package rezozio;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

public interface FusionRepository extends CrudRepository<Fusion, Long> {

    //Liste des idMessage pour un idHashtag
    ArrayList<Fusion> findByIdHashtag(Long idHashtag);

    //Liste des idhashtags pour un messages
    ArrayList<Fusion> findByIdMessage(Long idMessage);

}
