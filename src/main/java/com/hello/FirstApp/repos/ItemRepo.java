package com.hello.FirstApp.repos;

import com.hello.FirstApp.modals.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends CrudRepository<Item, Integer> {
}
