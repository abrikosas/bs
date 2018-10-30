package se.seb.buildstuff;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.seb.buildstuff.Domain.Event;
import se.seb.buildstuff.KafkaReceiver.KafkaReceiver;
import se.seb.buildstuff.Service.EventService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class BuildstuffApplication {

	@Autowired
	KafkaReceiver kafkaReceiver;



	public static void main(String[] args) {
		SpringApplication.run(BuildstuffApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(EventService eventService){
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();



			TypeReference<List<Event>> typeReference = new TypeReference<List<Event>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream(
					"/json/events.json");


			/*try {
				List<Event> events = mapper.readValue(inputStream,typeReference);
				eventService.save(events);
				//eventService.insert(events);
				System.out.println("Events Saved!");
			} catch (IOException e){
				System.out.println("Unable to save events: " + e.getMessage());
				e.printStackTrace();
			}*/
		};
	}



}
