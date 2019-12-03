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
    private int health;
    private static Board board;
    private List<Enemy> enemies;
    private Wave wave;

    public TowerDefense() {
        this.money = 40;
        this.towers = new ArrayList<>();
        this.health = 100;
        TowerDefense.board = new Board();
        priorityQueue();

        this.enemies = new ArrayList<>();
        this.wave = new Wave();
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
        return this.wave.getNumber();
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
        if (board.getTilePosition(x, y) == Tile.WALL
                && board.getTilePosition(x + 10, y + 10) == Tile.WALL
                && board.getTilePosition(x - 3, y - 3) == Tile.WALL) {
            if (this.money >= 5) {
                this.money = this.money - 5;
                towers.add(new Tower(x, y));
                board.setTower(x, y);
                return true;
            }
        }
        return false;
    }

    public void newWave() {
        this.wave.update();
    }

    public Board getBoard() {
        return this.board;
    }

    public void update(double deltaTime) {
        spawnEnemies(deltaTime);
        moveEnemies(deltaTime);
    }

    private void spawnEnemies(double deltaTime) {
        Enemy newEnemy = new Enemy();
        enemies.add(newEnemy);
    }

    private void moveEnemies(double deltaTime) {
        for (Enemy enemy : enemies) {
            Tile tile;
            tile = board.getTilePosition(enemy.getPositionY(), enemy.getPositionX());

            enemy.move(tile.getY() * deltaTime, tile.getX() * deltaTime);

        }
    }

    private static void priorityQueue() {
        Astar astar;
        astar = new Astar(board);
        astar.searchPriorityQueue();

    }
}
