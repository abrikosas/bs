package se.seb.buildstuff.Controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.seb.buildstuff.Domain.Event;
import se.seb.buildstuff.Service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService= eventService;
    }

    @GetMapping("/list")
    public Iterable<Event> list() {
        return eventService.list();
    }

}
