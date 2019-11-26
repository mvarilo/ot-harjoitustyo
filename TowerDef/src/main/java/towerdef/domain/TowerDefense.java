/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MV
 */
public class TowerDefense {

    private int money;
    private List<Tower> towers;
    private int wave;
    private int health;

    public TowerDefense() {
        this.money = 40;
        this.towers = new ArrayList<>();
        this.wave = 1;
        this.health = 100;

    }

    public int getMoney() {
        return money;
    }

    public boolean buyTower() {
        towers.add(new Tower());
        if (this.money >= 5) {
            this.money = this.money - 5;
            return true;
        }
        return false;
    }

    public void setMoney(int i) {
        this.money = i;
    }

    public int getWaveNumber() {
        return this.wave;
    }

    public int getHealth() {
        return this.health;
    }

    public List<Tower> getTowers() {
        return this.towers;
    }

    public boolean isWaveOver() {
        return true;
    }

    boolean isGameOver() {
        if (health > 0) {
            return false;
        }
        return true;
    }

    public void takeDamage(int i) {
        this.health = health - i;
    }

    public boolean buyTower(double x, double y) {
        towers.add(new Tower(x, y));
        if (this.money >= 5) {
            this.money = this.money - 5;
            return true;
        }
        return false;
    }

    public void newWave() {
        this.wave++;
    }
}
