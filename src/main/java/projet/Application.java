package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner  {

  	@Autowired
		private CharacterRepository repository;

		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}

		@Override
		public void run(String... args) throws Exception {

			repository.deleteAll();

			// save a couple of Characters
			repository.save(new Character("Alice", "Smith"));
			repository.save(new Character("Bob", "Smith"));

		}


}
