package com.mustbe.mustbe.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long lat;
    private long lon;

    private Calendar startDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;

    @ManyToOne
    private Player owner;

    @ManyToOne
    private Game game;



}
