package rezozio.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import rezozio.Entity.Fusion;

public interface FusionRepository extends CrudRepository<Fusion, Long> {

    //Liste des idMessage pour un idHashtag
    ArrayList<Fusion> findByIdHashtag(Long idHashtag);

    //Liste des idhashtags pour un messages
    ArrayList<Fusion> findByIdMessage(Long idMessage);

}
