package com.hello.FirstApp.services;

import com.hello.FirstApp.modals.Series;
import com.hello.FirstApp.repos.SeriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {
    @Autowired
    private SeriesRepo seriesRepo;

    public List<Series> readAllSeries(){
        return (List<Series>) seriesRepo.findAll();
    }

    public Optional<Series> readSeries(Integer id){
        return seriesRepo.findById(id);
    }

    public boolean createSeries(Series series){
        seriesRepo.save(series);
        return true;
    }

    public boolean updateSeries(Series series){
        createSeries(series);
        return true;
    }

    public boolean deleteSeries(Integer id){
        seriesRepo.deleteById(id);
        return true;
    }
}
