package com.hello.FirstApp.services;

import com.hello.FirstApp.modals.Match;
import com.hello.FirstApp.repos.MatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private MatchRepo matchRepo;

    public List<Match> readAllMatches(){
        return (List<Match>) matchRepo.findAll();
    }

    public Optional<Match> readMatch(Integer id){
        return matchRepo.findById(id);
    }

    public boolean createMatch(Match match){
        matchRepo.save(match);
        return true;
    }

    public boolean updateMatch(Match match){
        createMatch(match);
        return true;
    }

    public boolean deleteMatch(Integer id){
        matchRepo.deleteById(id);
        return true;
    }
}
