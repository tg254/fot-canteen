package com.hello.FirstApp.modals;

import javax.persistence.*;

@Entity
@Table(name = "inning")
public class Match {
    @Id
    @GeneratedValue
    private Integer id;
    private  String venue;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_player", referencedColumnName = "id")
    private  Player playerOfMatch;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_series", referencedColumnName = "id")
    private Series series;

    public Match() {
    }

    public Match(Integer id, String venue) {
        this.id = id;
        this.venue = venue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Player getPlayerOfMatch() {
        return playerOfMatch;
    }

    public void setPlayerOfMatch(Player playerOfMatch) {
        this.playerOfMatch = playerOfMatch;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
