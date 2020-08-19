package com.mustbe.mustbe.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String phone;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Inscription> inscriptions;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Event> createdEvents;

}
