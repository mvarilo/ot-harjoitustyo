/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvarilo.towerdef;

import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;

/**
 *
 * @author MV
 */
public class TowerDefense {

    private int money;
    private List<Tower> towers;

    public TowerDefense() {
        this.money = 40;
        this.towers = new ArrayList<>();

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

    void setMoney(int i) {
        this.money = i;
    }
}
