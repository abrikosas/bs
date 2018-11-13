package se.seb.buildstuff.KafkaReceiver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import se.seb.buildstuff.Domain.Event;
import se.seb.buildstuff.Service.EventService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class KafkaReceiver {
    @Autowired
    EventService eventService;

    @KafkaListener(topics = "${app.topic.name}")
    public  void receiveTopic1(ConsumerRecord<?, ?> consumerRecord) throws IOException{
        System.out.println(consumerRecord.value());

        ObjectMapper mapper = new ObjectMapper();



        TypeReference<List<Event>> typeReference = new TypeReference<List<Event>>(){};

        InputStream sk = IOUtils.toInputStream(consumerRecord.value().toString(),"UTF-8");

			try {
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

                //Files.write(Paths.get("event"+timeStamp+".json"), consumerRecord.value().toString().getBytes());


                List<Event> events = mapper.readValue(sk,typeReference);
				eventService.save(events);
				//eventService.insert(events);
				System.out.println("Events Saved!");
			} catch (IOException e){
				System.out.println("Unable to save events: " + e.getMessage());
				e.printStackTrace();
			}
    }


    }


