/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.io.Serializable;

/**
 *
 * Peli toimii niin että viholliset tulevat aalloissa tämän luokan tehtävänä on
 * pitää siitä huolta aaltojen hallinnsta.
 */
public class Wave implements Serializable {

    private int number;
    private int maxEnemies;
    private Enemy enemy;
    private double spawnCooldown;
    private double spawnFrame;
    private int enemiesSpawned;

    public Wave() {
        this.number = 0;
    }

    /**
     *
     * Konstruktori luo aallon ja asettaa sille vihollisen määritelmän ja kuinka
     * monta niitä on.
     *
     * @param newEnemy vihollisen määritelmä
     * @param enemies monta vihollista on
     */
    public Wave(Enemy newEnemy, int enemies) {
        this.number = 0;
        this.enemy = newEnemy;
        this.maxEnemies = enemies;
        this.spawnCooldown = 0.5;
        this.spawnFrame = 0.0;
        this.enemiesSpawned = 0;
    }

    public int getNumber() {
        return this.number;
    }

    /**
     * Päivittää seuraavan kierroksen vaikeammaksi.
     */
    public void update() {
        this.number++;
        this.maxEnemies = this.maxEnemies + 4;
        this.enemiesSpawned = 0;
        this.enemy.speedUp();
    }

    /**
     * Luo viholliset laudalle
     *
     * @param deltaTime
     * @return
     */
    public Enemy update(double deltaTime) {
        if (!isSpawningFinished()) {
            if (spawnFrame >= 0) {
                spawnFrame -= deltaTime;
            } else {
                spawnFrame = spawnCooldown;
                enemiesSpawned++;
                return enemy.clone();
            }
        }
        return null;
    }

    public boolean isSpawningFinished() {
        return enemiesSpawned >= maxEnemies;
    }

}
