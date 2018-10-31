package se.seb.buildstuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.seb.buildstuff.KafkaReceiver.KafkaReceiver;

@SpringBootApplication
public class BuildstuffApplication {

	@Autowired
	KafkaReceiver kafkaReceiver;



	public static void main(String[] args) {
		SpringApplication.run(BuildstuffApplication.class, args);
	}
}
