package com.mustbe.mustbe.services;

import com.mustbe.mustbe.entities.Event;
import com.mustbe.mustbe.entities.Inscription;
import com.mustbe.mustbe.entities.Player;
import com.mustbe.mustbe.exceptions.ServiceException;
import com.mustbe.mustbe.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private EventService eventService;

    @Transactional
    public Inscription createNewInscription(long eventId, Player player) throws ServiceException {
        Event event = eventService.findById(eventId);
        if(event == null)
            throw new ServiceException("The event " + eventId + " does not exist.", HttpStatus.NOT_FOUND);
        // TODO : Contar les inscripcions d'un event per tal que no hi hagi més del nombre màxim de jugadors.
        if(inscriptionRepository.countByEventId(eventId) >= event.getGame().getMaxPlayers()){
            throw new ServiceException("Event is completed, you canno't join it.", HttpStatus.BAD_REQUEST);
        }
        if(inscriptionRepository.findByEventIdAndPlayerId(eventId, player.getId()).isPresent()){
            throw new ServiceException("You have already joined this event.", HttpStatus.BAD_REQUEST);
        }
        Inscription inscription = new Inscription(event, player);
        inscriptionRepository.save(inscription);
        return inscription;
    }

    @Transactional
    public void deleteInscription(long eventId, Player player) throws ServiceException {
        Optional<Inscription> inscription = inscriptionRepository.findByEventIdAndPlayerId(eventId, player.getId());
        if(!inscription.isPresent()){
            throw new ServiceException("You are not subscribed in this event.", HttpStatus.NOT_FOUND);
        }
        inscriptionRepository.delete(inscription.get());
    }

    @Transactional
    public List<Inscription> getInscriptionsByPlayerId(long playerId) {
        return inscriptionRepository.findByPlayerId(playerId);
    }

    @Transactional
    public List<Inscription> getInscriptionsByEventId(long eventId) {
        return inscriptionRepository.findByEventId(eventId);
    }
}
