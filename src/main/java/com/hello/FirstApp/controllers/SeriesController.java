package com.hello.FirstApp.controllers;

import com.hello.FirstApp.modals.Series;
import com.hello.FirstApp.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/series")
public class SeriesController {
    @Autowired
    private SeriesService seriesService;

    @GetMapping
    public List<Series> getAllSeries(){
        return seriesService.readAllSeries();
    }

    @GetMapping(path ="{id}")
    public Optional<Series> getSeries(@PathVariable("id") Integer id){
        return seriesService.readSeries(id);
    }

    @PostMapping
    public void postSeries(@NotNull @RequestBody Series series){
        seriesService.createSeries(series);
    }

    @PutMapping(path = "{id}")
    public void putSeries(@PathVariable("id") Integer id, @NotNull @RequestBody Series series){
        series.setId(id);
        seriesService.updateSeries(series);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSeries(@PathVariable("id") Integer id){
        seriesService.deleteSeries(id);
    }
}