package se.seb.buildstuff.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private Long gameId;
    private String name;
    private Long x;
    private Long y;
    private Long ballId;
    private Long holeId;
    private String time;
    private String combinationId;

    private String events;




    public Event() {}
}
