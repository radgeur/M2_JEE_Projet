package rezozio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner  {

  @Autowired
	private MessageRepository messageRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

    	// save a message
		messageRepository.save(new Message(1L, "Je suis le premier message"));
		messageRepository.save(new Message(1L, "message, je suis ton père"));
		


		System.out.println("--------------TEST-----------------------------");
        for (Message m : messageRepository.findAll()) {
			System.out.println(m);
    	}
	}


}
