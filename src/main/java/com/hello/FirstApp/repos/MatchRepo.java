package com.hello.FirstApp.repos;

import com.hello.FirstApp.modals.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepo extends CrudRepository<Match, Integer> {
}
