package com.hello.FirstApp.modals;

import javax.persistence.*;

@Entity
@Table(name = "bonus")
public class Bonus {
    @Id
    private Integer type;
    private Double percentage;
    private String name;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_payment", referencedColumnName = "tokenid")
    private Payment payment;

    public Bonus() {
    }

    public Bonus(Integer type, Double percentage, String name, String description, Payment payment) {
        this.type = type;
        this.percentage = percentage;
        this.name = name;
        this.description = description;
        this.payment = payment;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
