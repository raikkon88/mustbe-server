package com.mustbe.mustbe.repositories;

import com.mustbe.mustbe.entities.Inscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InscriptionRepository extends CrudRepository<Inscription, Long> {

    long countByEventId(long eventId);

    List<Inscription> findByPlayerId(long playerId);

    List<Inscription> findByEventId(long playerId);

    Optional<Inscription> findByEventIdAndPlayerId(long eventId, long playerId);

}
