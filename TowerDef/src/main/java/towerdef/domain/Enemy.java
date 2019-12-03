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
public class Enemy {

    private double positionX;
    private double positionY;
    private double speed;

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    void move(double y, double x) {
        positionY += y * speed;
        positionX += x * speed;
    }

}
