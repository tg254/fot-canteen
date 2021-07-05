package com.hello.FirstApp.controllers;

import com.hello.FirstApp.modals.Player;
import com.hello.FirstApp.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers(){
        return playerService.readAllPlayers();
    }

    @GetMapping(path ="{id}")
    public Optional<Player> getPlayer(@PathVariable("id") Integer id){
        return playerService.readPlayer(id);
    }

    @PostMapping
    public void postPlayer(@NotNull @RequestBody Player player){
        playerService.createPlayer(player);
    }

    @PutMapping(path = "{id}")
    public void putPlayer(@PathVariable("id") Integer id, @NotNull @RequestBody Player player){
        player.setId(id);
        playerService.updatePlayer(player);
    }

    @DeleteMapping(path = "{id}")
    public void deletePlayer(@PathVariable("id") Integer id){
        playerService.deletePlayer(id);
    }
}
