package com.freniche.adventure.model;

import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable {

    private int healthPoints = 100;

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int attack() {
        Random random = new Random();
        return random.nextInt(50);
    }
}
