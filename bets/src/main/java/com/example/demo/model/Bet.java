package com.example.demo.model;

import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "bet")
public class Bet {

    public Bet(){}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    private String status = "pending";
    private double homeOdd;
    private double awayOdd;
    private double drawOdd;
    private String outcome;

    public Bet(String name, String status, double homeOdd, double awayOdd, double drawOdd, String outcome) {
        this.name = name;
        this.status = status;
        this.homeOdd = homeOdd;
        this.awayOdd = awayOdd;
        this.drawOdd = drawOdd;
        this.outcome = outcome;
    }

    public Bet(String name, double homeOdd, double awayOdd, double drawOdd) {
        this.name = name;
        this.homeOdd = homeOdd;
        this.awayOdd = awayOdd;
        this.drawOdd = drawOdd;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BetData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", homeOdd=" + homeOdd +
                ", awayOdd=" + awayOdd +
                ", drawOdd=" + drawOdd +
                ", outcome='" + outcome + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getHomeOdd() {
        return homeOdd;
    }

    public void setHomeOdd(double homeOdd) {
        this.homeOdd = homeOdd;
    }

    public double getAwayOdd() {
        return awayOdd;
    }

    public void setAwayOdd(double awayOdd) {
        this.awayOdd = awayOdd;
    }

    public double getDrawOdd() {
        return drawOdd;
    }

    public void setDrawOdd(double drawOdd) {
        this.drawOdd = drawOdd;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }



}