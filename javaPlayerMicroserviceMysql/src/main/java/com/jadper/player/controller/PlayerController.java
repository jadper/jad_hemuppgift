package com.jadper.player.controller;

import com.jadper.player.model.Player;
import com.jadper.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/players")
    public List<Player> list() {
        return playerService.listPlayers();
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Player> get(@PathVariable Integer id) {
        try {
            Player player = playerService.getPlayer(id);
            return new ResponseEntity<Player>(player, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/player")
    public void add(@RequestBody Player player) {        
        playerService.savePlayer(player);
    }

    @PutMapping("/player/{id}") // replace player
    public ResponseEntity<?> update(@RequestBody Player player, @PathVariable Integer id) {
        try {            
            playerService.getPlayer(id);
            playerService.savePlayer(player);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {            
            playerService.getPlayer(id);
            playerService.deletePlayer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    @PatchMapping(value = "/player/{id}")  
    public ResponseEntity<?> partialUpdateGeneric(
        @RequestBody Map<String, Object> updates,
        @PathVariable("id") Integer id) {
        try {            
            playerService.updatePlayer(updates, id);
            // get player by id and return for easy confirm
            Player p = playerService.getPlayer(id);
            return new ResponseEntity<Player>(p, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}




