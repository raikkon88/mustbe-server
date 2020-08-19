package com.mustbe.mustbe.repositories;

import com.mustbe.mustbe.entities.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
