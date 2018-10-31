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
    private Integer id;
    private Integer gameId;
    private String name;
    private Integer x;
    private Integer y;
    private Integer ballId;
    private Integer holeId;
    private String time;
    private String combinationId;

    private String events;




    public Event() {}
}
