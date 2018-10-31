package se.seb.buildstuff.Repository;

import org.springframework.data.repository.CrudRepository;

        import org.springframework.data.repository.CrudRepository;
import se.seb.buildstuff.Domain.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}

