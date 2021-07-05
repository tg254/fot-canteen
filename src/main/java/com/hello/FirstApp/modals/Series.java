package com.hello.FirstApp.modals;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private String name;
    private boolean won;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_player", referencedColumnName = "id")
    private Player playerOfSeries;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> matches;

    public Series() {
    }

    public Series(Integer id, String name, boolean won) {
        this.id = id;
        this.name = name;
        this.won = won;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public Player getPlayerOfSeries() {
        return playerOfSeries;
    }

    public void setPlayerOfSeries(Player playerOfSeries) {
        this.playerOfSeries = playerOfSeries;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
