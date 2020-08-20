package com.mustbe.mustbe.entities;

import com.fasterxml.jackson.annotation.JsonView;
import com.mustbe.mustbe.views.Views;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    private long id;

    @JsonView(Views.Public.class)
    private String address;
    @JsonView(Views.Public.class)
    private String rules;
    @JsonView(Views.Public.class)
    private double lat;
    @JsonView(Views.Public.class)
    private double lon;
    @JsonView(Views.Public.class)
    private Calendar startDate;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonView({Views.Event.class})
    private List<Inscription> inscriptions;

    @ManyToOne
    @JsonView(Views.Event.class)
    private Player owner;

    @ManyToOne
    @JsonView({Views.EventPlayer.class, Views.Inscription.class})
    private Game game;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
