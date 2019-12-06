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
import java.util.List;

class Tower {

    private int power;
    private double range;
    private double x;
    private double y;
    private Enemy target;
    private double cooldown;
    private double firerate;
    private boolean inRange;

    public Tower() {
        this.power = 20;
        this.range = 20;
    }

    Tower(double x, double y) {
        this.power = 5;
        this.range = 50;
        this.x = x;
        this.y = y;
        this.cooldown = 0;
        this.firerate = 5;
    }

    double getPositionX() {
        return this.x;
    }

    double getPositionY() {
        return this.y;
    }

    public boolean hitTarget(Enemy enemy) {
        cooldown = firerate;
        if (this.target == enemy) {
            this.target = null;
            return true;
        }
        return false;
    }

    public Enemy getTarget() {
        return target;
    }

    public void findTarget(List<Enemy> targetList) {
        if (getTarget() != null) {
            return;
        }

        Enemy closestTarget = null;
        double closestDistance = 0.0;

        for (Enemy newEnemy : targetList) {
            double distanceX = newEnemy.getPositionX() - this.y;
            double distanceY = newEnemy.getPositionY() - this.x;

            double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

            if (closestTarget != null) {
                if (distanceTotal < closestDistance) {
                    distanceTotal = closestDistance;
                    closestTarget = newEnemy;
                    this.target = closestTarget;
                }
            }
            if (closestDistance > this.range) {
                this.inRange = false;
            }
            if (distanceTotal < this.range) {
                this.inRange = true;

            }

        }
    }

    public boolean canShoot() {
        if (cooldown <= 0 && this.inRange) {
            cooldown = firerate;
            return true;
        }
        return false;
    }

    public void countdown(double deltaTime) {
        cooldown -= deltaTime;
    }
}
