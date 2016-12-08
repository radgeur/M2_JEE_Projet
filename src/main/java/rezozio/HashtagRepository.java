package rezozio;

import org.springframework.data.repository.CrudRepository;

public interface HashtagRepository extends CrudRepository<Hashtag, Long> {

    //Hashtag correspondant au nom
    Hashtag findByTexteHashtag(String texteHashtag);

    //Hashtag par id
    Hashtag findById(Long id);

}
