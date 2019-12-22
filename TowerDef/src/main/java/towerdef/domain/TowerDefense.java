/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;

/**
 *
 * Pelin logiikan pääluokka, joka pyörittää peliä
 */
public class TowerDefense implements Serializable {

    private int money;
    private List<Tower> towers;
    private int health;
    private Board board;
    private List<Enemy> enemies;
    private Wave wave;
    private Astar astar;

    /**
     *
     * Konstruktori luo peliluokan, joka puolestaan asettaa pelin säännöt, luo
     * muut oliot ja laskee reitin pelilaudan läpi ja laittaa pelin
     * aloituspisteeseen.
     */
    public TowerDefense() {
        this.money = 40;
        this.towers = new ArrayList<>();
        this.health = 100;

        this.board = new Board();
        astar = new Astar(board);
        astar.searchPriorityQueue();
        board.visualize(astar.getPath());
        this.enemies = new ArrayList<>();

        Enemy newEnemy = new Enemy(board.getSpawnX(), board.getSpawnY(), 40);
        this.wave = new Wave(newEnemy, 0);
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
        return enemies.isEmpty() && wave.isSpawningFinished();
    }

    public boolean isGameOver() {
        return health <= 0;
    }

    public void takeDamage(int i) {
        this.health = health - i;
    }

    /**
     * Ostaa tornin ja palauttaa pystyikö ostamaan
     *
     * @param x
     * @param y
     * @return true jos onnistui ostamaan tornin, false jos ei
     */
    public boolean buyTower(double x, double y) {
        if (board.getTilePosition(x, y).getType().equals("ROAD")
                || board.getTilePosition(x + 10, y + 10).getType().equals("ROAD")
                || board.getTilePosition(x - 3, y - 3).getType().equals("ROAD")) {
            return false;
        } else {
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

    /**
     * Päivittää vihollisten tilan ja ampuu niitä torneilla
     */
    public void update(double deltaTime) {
        spawnEnemies(deltaTime);
        moveEnemies(deltaTime);
        shootEnemies(deltaTime);
        updateEnemies(deltaTime);
    }

    /**
     * Luo viholliset
     */
    private void spawnEnemies(double deltaTime) {
        Enemy newEnemy = wave.update(deltaTime);
        if (newEnemy != null) {
            enemies.add(newEnemy);
        }
    }

    /**
     * Liikuttaa vihollisia pelilaudalla
     *
     */
    private void moveEnemies(double deltaTime) {
        for (Enemy enemy : enemies) {
            Tile tile = board.getTilePosition(enemy.getPositionY(), enemy.getPositionX());
            ArrayList<Tile> temp = astar.getPath();
            Tile nextTile = temp.get(temp.indexOf(tile) + 1);

            enemy.move((nextTile.getX() - tile.getX()) * deltaTime, (nextTile.getY() - tile.getY()) * deltaTime);
        }
    }

    public Iterable<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Päivittää vihollisten tilan, mikäli ne osuvat tukikohtaan tai niillä on 0
     * tai alle elämää jäljellä ne poistetaan pelistä
     *
     */
    private void updateEnemies(double deltaTime) {
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (board.hitBase(enemy.getPositionX(), enemy.getPositionY())) {
                iterator.remove();
                this.takeDamage(enemy.getHealth());
            }
            if (enemy.getHealth() <= 0) {
                iterator.remove();
                System.out.println("Enemy destroyed");
                money++;
            }
        }
    }

    /**
     * Ampuu vihollisia.
     *
     */
    private void shootEnemies(double deltaTime) {
        for (Tower tower : towers) {
            tower.findTarget(enemies);
            for (Enemy enemy : enemies) {
                tower.shootTarget(enemy, deltaTime);
            }
        }
    }
}
