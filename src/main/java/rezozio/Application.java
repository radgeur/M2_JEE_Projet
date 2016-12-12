package rezozio;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rezozio.Entity.Fusion;
import rezozio.Entity.Hashtag;
import rezozio.Entity.Message;
import rezozio.Entity.User;
import rezozio.Repository.FusionRepository;
import rezozio.Repository.HashtagRepository;
import rezozio.Repository.MessageRepository;
import rezozio.Repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner  {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HashtagRepository hashtagRepository;
    @Autowired
    private FusionRepository fusionRepository;
    
    public static User userConnected = null;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

        User u1 = new User("Nath", "1234", "nat@hotmail.fr","/resources/photo.png", "nath", "nathtweet", "NDebaque");
        User u2 = new User("Jean", "1234", "nat@hotmail.fr","/resources/photo.png", "nath", "nathtweet", "NDebaque");

        //save users
        userRepository.save(u1);
        userRepository.save(u2);

        Message m1 = new Message(u1.getId(), "Je suis le premier message");
        Message m2 = new Message(u2.getId(), "message, je suis ton père");

        // save a message
		messageRepository.save(m1);
		messageRepository.save(m2);

        //Gestion des hashtags
        Hashtag h1 = new Hashtag("OKAY");
        Hashtag h2 = new Hashtag("DARKSIDE");

        //save hashtags
        hashtagRepository.save(h1);
        hashtagRepository.save(h2);

        ArrayList<Hashtag> list = new ArrayList<Hashtag>();
        list.add(h1);
        list.add(h2);

        //Pour chaque hashtag on lie le message au hashtag dans la table fusion
        for(Hashtag ht : list) {
            Fusion f = new Fusion(m1.getId(), ht.getId());
            Fusion f2 = new Fusion(m2.getId(), ht.getId());
            fusionRepository.save(f);
            fusionRepository.save(f2);
        }


	}


}
