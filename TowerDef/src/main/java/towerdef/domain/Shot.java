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
public class Shot {

    private double startX;
    private double startY;
    private double endX;
    private double endY;

    Shot(double positionX, double positionY, double endX, double endY) {
        this.startX = positionX;
        this.startY = positionY;
        this.endX = endX;
        this.endY = endY;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }
}
