package com.mustbe.mustbe.repositories;

import com.mustbe.mustbe.entities.Inscription;
import org.springframework.data.repository.CrudRepository;

public interface InscriptionRepository extends CrudRepository<Inscription, Long> {
}
