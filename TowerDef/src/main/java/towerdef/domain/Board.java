/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.util.ArrayList;

/**
 *
 * @author MV
 */
public class Board {

    private Tile[][] tiles;
    private int tileSize = 20;
    private int height = 400 / 20;
    private int width = 600 / 20;
    private int spawnX;
    private int spawnY;
    private int baseX;
    private int baseY;
    private Tile start;
    private Tile goal;

    public Board() {
        this.tiles = new Tile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tiles[i][j] = Tile.WALL;
            }
        }

        createBoard();
    }

    private void createBoard() {
        for (int i = 0; i < width / 2; i++) {
            tiles[height - 14][i] = Tile.ROAD;
        }
        for (int i = height - 14; i < 16; i++) {
            tiles[i][width / 2] = Tile.ROAD;
        }
        for (int i = width / 2 + 1; i < width; i++) {
            tiles[15][i] = Tile.ROAD;
        }

        spawnX = 6;
        spawnY = 0;
        tiles[spawnX][spawnY] = Tile.SPAWN;
        start = tiles[spawnX][spawnY];

        baseX = 15;
        baseY = 29;
        goal = tiles[baseX][baseY];
        tiles[baseX][baseY] = Tile.BASE;
    }

    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }

    public Tile getTilePosition(double x, double y) {
        return getTile((int) (y / tileSize), (int) (x / tileSize));
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getSpawnX() {
        return this.spawnX;
    }

    public int getSpawnY() {
        return this.spawnY;
    }

    public int getBaseX() {
        return this.baseX;
    }

    public int getBaseY() {
        return this.baseY;
    }

    public void setTower(double x, double y) {
        tiles[(int) (y / tileSize)][(int) (x / tileSize)] = Tile.TOWER;
    }

    public int distBetween(Tile current, Tile neighbour) {
        return 1;
    }

    Tile getStart() {
        return this.start;
    }

    Tile getGoal() {
        return this.goal;
    }

    public ArrayList<Tile> getNeighbours(Tile current) {
        ArrayList<Tile> neighbours = new ArrayList<>();

        if (left(current)) {
            Tile neighbour = this.tiles[current.getX() - 1][current.getY()];
            addNeighbour(neighbours, neighbour);
        }

        if (right(current)) {
            Tile naapuri = this.tiles[current.getX() + 1][current.getY()];
            addNeighbour(neighbours, naapuri);
        }

        if (up(current)) {
            Tile naapuri = this.tiles[current.getX()][current.getY() - 1];
            addNeighbour(neighbours, naapuri);
        }

        if (down(current)) {
            Tile naapuri = this.tiles[current.getX()][current.getY() + 1];
            addNeighbour(neighbours, naapuri);
        }
        return neighbours;
    }

    private void addNeighbour(ArrayList<Tile> neighbours, Tile neighbour) {
        if (neighbour != Tile.WALL && neighbour.isVisited() != true) {
            neighbours.add(neighbour);
        }

    }

    private boolean left(Tile current) {
        return current.getX() > 0;
    }

    private boolean right(Tile current) {
        return current.getX() < this.tiles.length - 1;
    }

    private boolean up(Tile current) {
        return current.getY() > 0;
    }

    private boolean down(Tile current) {
        return current.getY() < this.tiles.length - 1;
    }

}
