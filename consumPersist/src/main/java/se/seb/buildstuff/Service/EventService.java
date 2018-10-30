package se.seb.buildstuff.Service;


import org.springframework.stereotype.Service;
import se.seb.buildstuff.Domain.Event;
import se.seb.buildstuff.Repository.EventRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class EventService {

    private EventRepository eventRepository;
    @PersistenceContext
    EntityManager entityManager;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Iterable<Event> list() {
        return eventRepository.findAll();
    }


    public void save(List<Event> event) {
        eventRepository.saveAll(event);
    }


}
