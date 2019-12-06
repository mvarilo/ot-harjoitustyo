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
    private int health;

    public Enemy(double y, double x, double speed) {
        this.positionX = x;
        this.positionY = y;
        this.speed = speed;
        this.health = 1;

    }

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

    @Override
    public Enemy clone() {
        return new Enemy(
                positionY,
                positionX,
                speed
        );
    }

    public void speedUp() {
        this.speed += 10;
    }

    public void takeDamage(int i) {
        health -= i;
    }

    public int getHealth() {
        return health;
    }
}
