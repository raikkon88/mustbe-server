package com.mustbe.mustbe.controllers;


import com.mustbe.mustbe.entities.Event;
import com.mustbe.mustbe.entities.Player;
import com.mustbe.mustbe.exceptions.ServiceException;
import com.mustbe.mustbe.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/{gameId}")
    public ResponseEntity<?> createNewEvent(UsernamePasswordAuthenticationToken token,
                                            @PathVariable long gameId,
                                            @RequestBody Event event){
        Player p = (Player) token.getPrincipal();
        try{
            return new ResponseEntity<>(eventService.createNewEvent(gameId, event, p), HttpStatus.OK);
        }catch (ServiceException ex)  {
            return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
        }
    }

}
