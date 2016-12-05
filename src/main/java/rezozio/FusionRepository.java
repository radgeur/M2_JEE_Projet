package rezozio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FusionRepository extends CrudRepository<Fusion, Long> {

    //Liste des idMessage pour un idHashtag
    List<Long> findByIdHashtag(Long idHashtag);

}
