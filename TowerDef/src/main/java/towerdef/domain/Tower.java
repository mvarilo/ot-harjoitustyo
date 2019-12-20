/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

/**
 *
 * Torni joka ampuu vihollisia
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tower implements Serializable {

    private int power;
    private double range;
    private double x;
    private double y;
    private Enemy target;
    private double cooldown;
    private double firerate;
    private ArrayList<Shot> shots = new ArrayList<>();
    private double shotTimer;
    private boolean shooting;

    /**
     *
     * Konstruktori luo tornin jota ei ole laitettu kentälle
     */
    public Tower() {
        this.power = 1;
        this.range = 20;
    }

    /**
     *
     * Konstruktori luo tornin ja asettaa sille koordinaatit, kuinka nopeasti se
     * ampuu jne.
     *
     * @param x
     * @param y
     */
    Tower(double x, double y) {
        this.power = 1;
        this.range = 100;
        this.x = x;
        this.y = y;
        this.firerate = 5;
        this.cooldown = 0;
        this.shotTimer = 0;
        this.shooting = false;
    }

    public double getPositionX() {
        return this.x;
    }

    public double getPositionY() {
        return this.y;
    }

    public Enemy getTarget() {
        return target;
    }

    /**
     * Etsii kohteen jota ampua
     *
     * @param targetList
     */
    public void findTarget(List<Enemy> targetList) {
        if (this.getTarget() == null) {
            Enemy closestTarget = null;
            double closestDistance = 0.0;

            fireTargets(targetList, closestTarget, closestDistance);
        }
    }

    private void fireTargets(List<Enemy> targetList, Enemy closestTarget, double closestDistance) {
        for (Enemy newEnemy : targetList) {
            double distanceTotal = calculateDistance(newEnemy);

            if (closestTarget != null) {
                if (distanceTotal < closestDistance) {
                    closestTarget = newEnemy;
                    this.target = closestTarget;
                }
            }
        }
    }

    private double calculateDistance(Enemy newEnemy) {
        double distanceX = newEnemy.getPositionX() - this.y;
        double distanceY = newEnemy.getPositionY() - this.x;

        double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        return distanceTotal;
    }

    public boolean canShoot() {
        if (cooldown <= 0) {
            cooldown = firerate;
            return true;
        }
        return false;
    }

    public void countdown(double deltaTime) {
        cooldown -= deltaTime;
    }

    public int getPower() {
        return this.power;
    }

    /**
     * Ampuu kohdetta enemy
     *
     * @param enemy vihollinen jota ammutaan
     * @param deltaTime
     */
    public void shootTarget(Enemy enemy, double deltaTime) {
        double distance = calculateDistance(enemy);
        if (canShoot() && distance <= this.range) {
            enemy.takeDamage(power);
            System.out.println("Enemy takes " + power + " damage.");
            shots.add(new Shot(this.getPositionX(), this.getPositionY(),
                    enemy.getPositionX(), enemy.getPositionY()));
            this.shotTimer = 0;
        } else {
            this.countdown(deltaTime);
        }
    }

    public ArrayList<Shot> drawShots() {
        return shots;
    }

    public boolean shooting() {
        return shooting;
    }

    public void update(double deltaTime) {
        shotTimer += deltaTime;
    }

    public boolean timeoutShot() {
        if (this.shotTimer >= 0.5) {
            this.shotTimer = 0;
            return true;
        }
        return false;
    }
}
