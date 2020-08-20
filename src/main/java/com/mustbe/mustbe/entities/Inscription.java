package com.mustbe.mustbe.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.mustbe.mustbe.views.Views;

import javax.persistence.*;

@Entity
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    private long id;

    @ManyToOne
    @JsonView({Views.Event.class, Views.Inscription.class})
    private Player player;

    @ManyToOne
    @JsonView({Views.Player.class, Views.Inscription.class})
    private Event event;

    public Inscription(){}
    public Inscription(Event event, Player player){
        this.event = event;
        this.player = player;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
