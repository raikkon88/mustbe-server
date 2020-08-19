package com.mustbe.mustbe.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String phone;
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
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

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
