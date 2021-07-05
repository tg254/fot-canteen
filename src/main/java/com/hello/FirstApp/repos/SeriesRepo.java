package com.hello.FirstApp.repos;

import com.hello.FirstApp.modals.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepo extends CrudRepository<Series, Integer> {
}