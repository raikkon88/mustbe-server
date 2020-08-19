package com.mustbe.mustbe.controllers;

import com.mustbe.mustbe.entities.Player;
import com.mustbe.mustbe.exceptions.ServiceException;
import com.mustbe.mustbe.services.InscriptionService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @GetMapping
    public ResponseEntity<?> getInscriptions(UsernamePasswordAuthenticationToken token) {
        Player player = (Player) token.getPrincipal();
        return new ResponseEntity<>(inscriptionService.getInscriptionsByPlayerId(player.getId()), HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<?> getInscriptionsFromEvent(@PathVariable long eventId) {
        return new ResponseEntity<>(inscriptionService.getInscriptionsByEventId(eventId), HttpStatus.OK);
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<?> subscribeEvent(UsernamePasswordAuthenticationToken token,
                                         @PathVariable long eventId){
        Player player = (Player) token.getPrincipal();
        try {
            return new ResponseEntity<>( inscriptionService.createNewInscription(eventId, player), HttpStatus.OK);
        }catch (ServiceException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> unsubscribeEvent(UsernamePasswordAuthenticationToken token,
                                              @PathVariable long eventId){
        Player player = (Player) token.getPrincipal();
        try {
            inscriptionService.deleteInscription(eventId, player);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ServiceException ex){
            return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
        }
    }

}
