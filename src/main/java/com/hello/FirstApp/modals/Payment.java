package com.hello.FirstApp.modals;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment")
public class Payment {
    @Id @GeneratedValue
    private Integer tokenid;
    private Double payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bonus> bonuses;

    public Payment() {
    }

    public Payment(Integer tokenid, Double payment, Player player) {
        this.tokenid = tokenid;
        this.payment = payment;
        this.player = player;
    }

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }
}
