package com.hello.FirstApp.modals;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private String name;
    private String category;
    private String type;
    private String description;

    @OneToOne(mappedBy = "player")
    private Payment payment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Series> series;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> matches;

    public Player() {
    }

    public Player(Integer id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.description = desc;
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

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
