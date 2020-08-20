package com.mustbe.mustbe.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.mustbe.mustbe.views.Views;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Public.class})
    private long id;

    @JsonView({Views.Public.class})
    private String phone;
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @JsonView({Views.Player.class})
    private List<Inscription> inscriptions;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonView({Views.Player.class})
    private List<Event> createdEvents;

    public Player() {}

    public Player(String phone, String password) {
        this(phone, password, new ArrayList<>(), new ArrayList<>());
    }

    public Player(String phone, String password, List<Inscription> inscriptions, List<Event> events) {
        this.phone = phone;
        this.password = password;
        this.inscriptions = inscriptions;
        this.createdEvents = events;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public List<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(List<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
