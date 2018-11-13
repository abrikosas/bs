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
    private Double x;
    private Double y;
    private Integer ballId;
    private Integer holeId;
    private Integer time;
    private Integer combinationId;
    private Integer status;

    public Event() {}
}
