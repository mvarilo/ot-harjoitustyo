/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

/**
 *
 * @author MV
 */
class Tower {

    private int power;
    private double range;
    private double x;
    private double y;

    public Tower() {
        this.power = 20;
        this.range = 20;
    }

    Tower(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getPositionX() {
        return this.x;
    }

    double getPositionY() {
        return this.y;
    }
}
