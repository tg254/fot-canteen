package com.hello.FirstApp.repos;

import com.hello.FirstApp.modals.Bonus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusRepo extends CrudRepository<Bonus, Integer> {
}
