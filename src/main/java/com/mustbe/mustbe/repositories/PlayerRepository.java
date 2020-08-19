package com.mustbe.mustbe.repositories;

import com.mustbe.mustbe.entities.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Optional<Player> findByPhone(String phone);
}
