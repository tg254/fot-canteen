package com.hello.FirstApp.services;

import com.hello.FirstApp.modals.Player;
import com.hello.FirstApp.repos.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepo playerRepo;

    public List<Player> readAllPlayers(){
        return (List<Player>) playerRepo.findAll();
    }

    public Optional<Player> readPlayer(Integer id){
        return playerRepo.findById(id);
    }

    public boolean createPlayer(Player player){
        playerRepo.save(player);
        return true;
    }

    public boolean updatePlayer(Player player){
        createPlayer(player);
        return true;
    }

    public boolean deletePlayer(Integer id){
        playerRepo.deleteById(id);
        return true;
    }
}
