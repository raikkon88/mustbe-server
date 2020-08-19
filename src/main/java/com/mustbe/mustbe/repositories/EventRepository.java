package com.mustbe.mustbe.repositories;

import com.mustbe.mustbe.entities.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
