package com.mustbe.mustbe.services;

import com.mustbe.mustbe.entities.Event;
import com.mustbe.mustbe.entities.Game;
import com.mustbe.mustbe.entities.Player;
import com.mustbe.mustbe.exceptions.ServiceException;
import com.mustbe.mustbe.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GameService gameService;

    @Transactional
    public Event createNewEvent(long gameId, Event event, Player player) throws ServiceException {
        Game g = gameService.findById(gameId);
        event.setGame(g);
        event.setOwner(player);
        eventRepository.save(event);
        return event;
    }

    @Transactional
    public Event findById(long eventId){
        Optional<Event> event = eventRepository.findById(eventId);
        if(event.isPresent())
            return event.get();
        return null;
    }

}
