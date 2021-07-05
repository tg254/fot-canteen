package com.hello.FirstApp.services;

import com.hello.FirstApp.modals.Bonus;
import com.hello.FirstApp.repos.BonusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BonusService {
    @Autowired
    private BonusRepo bonusRepo;

    public List<Bonus> readAllBonuses(){
        return (List<Bonus>) bonusRepo.findAll();
    }

    public Optional<Bonus> readBonus(Integer id){
        return bonusRepo.findById(id);
    }

    public boolean createBonus(Bonus bonus){
        bonusRepo.save(bonus);
        return true;
    }

    public boolean updateBonus(Bonus bonus){
        createBonus(bonus);
        return true;
    }

    public boolean deleteBonus(Integer id){
        bonusRepo.deleteById(id);
        return true;
    }
}
