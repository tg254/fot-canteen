package com.hello.FirstApp.services;

import com.hello.FirstApp.modals.Item;
import com.hello.FirstApp.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;

    public List<Item> readAllItems(){
        return (List<Item>) itemRepo.findAll();
    }

    public Optional<Item> readItem(Integer id){
        return itemRepo.findById(id);
    }

    public boolean createItem(Item item){
        itemRepo.save(item);
        return true;
    }

    public boolean updateItem(Item item){
        createItem(item);
        return true;
    }

    public boolean deleteItem(Integer id){
        itemRepo.deleteById(id);
        return true;
    }
}
