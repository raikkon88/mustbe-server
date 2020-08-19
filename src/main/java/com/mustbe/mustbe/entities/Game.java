package com.mustbe.mustbe.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int maxPlayers;
    private int minPlayers;

    @OneToMany(mappedBy = "game")
    private List<Event> events;

}
