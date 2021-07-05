package com.hello.FirstApp.controllers;

import com.hello.FirstApp.modals.Bonus;
import com.hello.FirstApp.services.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bonuses")
public class BonusController {
    @Autowired
    private BonusService bonusService;

    @GetMapping
    public List<Bonus> getAllBonuses(){
        return bonusService.readAllBonuses();
    }

    @GetMapping(path ="{id}")
    public Optional<Bonus> getBonus(@PathVariable("id") Integer id){
        return bonusService.readBonus(id);
    }

    @PostMapping
    public void postBonus(@NotNull @RequestBody Bonus bonus){
        bonusService.createBonus(bonus);
    }

    @PutMapping(path = "{id}")
    public void putBonus(@PathVariable("id") Integer id, @NotNull @RequestBody Bonus bonus){
        bonus.setType(id);
        bonusService.updateBonus(bonus);
    }

    @DeleteMapping(path = "{id}")
    public void deleteBonus(@PathVariable("id") Integer id){
        bonusService.deleteBonus(id);
    }
}
