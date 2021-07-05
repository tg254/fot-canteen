package com.hello.FirstApp.controllers;

import com.hello.FirstApp.modals.Match;
import com.hello.FirstApp.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping
    public List<Match> getAllMatches(){
        return matchService.readAllMatches();
    }

    @GetMapping(path ="{id}")
    public Optional<Match> getMatch(@PathVariable("id") Integer id){
        return matchService.readMatch(id);
    }

    @PostMapping
    public void postMatch(@NotNull @RequestBody Match match){
        matchService.createMatch(match);
    }

    @PutMapping(path = "{id}")
    public void putMatch(@PathVariable("id") Integer id, @NotNull @RequestBody Match match){
        match.setId(id);
        matchService.updateMatch(match);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMatch(@PathVariable("id") Integer id){
        matchService.deleteMatch(id);
    }
}
