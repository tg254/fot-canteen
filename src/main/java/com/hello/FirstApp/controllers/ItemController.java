package com.hello.FirstApp.controllers;

import com.hello.FirstApp.modals.Item;
import com.hello.FirstApp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.readAllItems();
    }

    @GetMapping(path ="{id}")
    public Optional<Item> getOrder(@PathVariable("id") Integer id){
        return itemService.readItem(id);
    }

    @PostMapping
    public void postItem(@NotNull @RequestBody Item item){
        itemService.createItem(item);
    }

    @PutMapping(path = "{id}")
    public void putItem(@PathVariable("id") Integer id, @NotNull @RequestBody Item item){
        item.setId(id);
        itemService.updateItem(item);
    }

    @DeleteMapping(path = "{id}")
    public void deleteItem(@PathVariable("id") Integer id){
        itemService.deleteItem(id);
    }
}
