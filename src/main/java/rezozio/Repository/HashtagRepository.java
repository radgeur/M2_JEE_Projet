package rezozio.Repository;

import org.springframework.data.repository.CrudRepository;

import rezozio.Entity.Hashtag;

public interface HashtagRepository extends CrudRepository<Hashtag, Long> {

    //Hashtag correspondant au nom
    Hashtag findByTexteHashtag(String texteHashtag);

    //Hashtag par id
    Hashtag findById(Long id);

}
