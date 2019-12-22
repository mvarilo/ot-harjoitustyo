/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.io.Serializable;

/**
 *
 * Vihollinen jonka pelaaja yrittää tuhota ennen kuin se pääsee maaliin
 */
public class Enemy implements Serializable {

    private double positionX;
    private double positionY;
    private double speed;
    private int health;

    /**
     * Konstruktori luo vihollisen ja antaa sille koordinaaatit sekä nopeuden
     *
     * @param y
     * @param x
     * @param speed
     */
    public Enemy(double y, double x, double speed) {
        this.positionX = x;
        this.positionY = y;
        this.speed = speed;
        this.health = 2;

    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    /**
     * Liikuttaa vihollista nopeuden mukaan
     *
     * @param y
     * @param x
     */
    public void move(double y, double x) {
        positionY += y * speed;
        positionX += x * speed;
    }

    /**
     * Kloonaa vihollisen
     */
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

    public double getSpeed() {
        return this.speed;
    }

    public void takeDamage(int i) {
        health -= i;
    }

    public int getHealth() {
        return health;
    }
}
