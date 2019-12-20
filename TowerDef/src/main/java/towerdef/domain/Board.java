/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author MV
 */
public class Board implements Serializable {

    private Tile[][] tiles;
    private int tileSize;
    private int height;
    private int width;
    private double spawnX;
    private double spawnY;
    private int baseX;
    private int baseY;
    private Tile start;
    private Tile goal;

    /**
     * Konstruktori asettaa pelilaudalle ominaisuudet ja kutsuu metodia joka
     * rakentaa sokkelon pelilaudalle.
     *
     */
    public Board() {
        this.tileSize = 20;
        this.width = 600 / 20;
        this.height = 400 / 20;
        this.tiles = new Tile[height][width];

        createBoard();
    }

    /**
     * Luo eräänlaisen laudan
     *
     */
    private void createBoard() {
        emptyBoard();
        createRoad();

        spawnX = (6 * tileSize);
        spawnY = (0 * tileSize);

        Tile spawnTile = new Tile(6, 0);
        spawnTile.setType("SPAWN");
        this.start = spawnTile;
        tiles[6][0] = spawnTile;

        baseX = 15;
        baseY = 29;
        Tile baseTile = new Tile(15, 29);
        baseTile.setType("BASE");
        this.goal = baseTile;
        tiles[baseX][baseY] = baseTile;
    }

    /**
     * Rakentaa laudalle reitin josta viholliset voivat kulkea
     *
     */
    private void createRoad() {
        for (int i = 0; i < width / 2; i++) {
            Tile newTile = new Tile(6, i);
            newTile.setType("ROAD");
            tiles[6][i] = newTile;
        }
        for (int i = 6; i < 10; i++) {
            Tile newTile = new Tile(i, width / 2);
            newTile.setType("ROAD");
            tiles[i][width / 2] = newTile;
        }
        for (int i = 15; i > 5; i--) {
            Tile newTile = new Tile(10, i);
            newTile.setType("ROAD");
            tiles[10][i] = newTile;
        }
        for (int i = 10; i < 15; i++) {
            Tile newTile = new Tile(i, 5);
            newTile.setType("ROAD");
            tiles[i][5] = newTile;
        }
        for (int i = 5; i < width; i++) {
            Tile newTile = new Tile(15, i);
            newTile.setType("ROAD");
            tiles[15][i] = newTile;
        }
    }

    /**
     * Tekee tyhjän laudan
     *
     */
    public void emptyBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile newTile = new Tile(i, j);
                newTile.setType("WALL");
                this.tiles[i][j] = newTile;
            }
        }
    }

    public Tile getTile(int i, int j) {
        return tiles[i][j];
    }

    public Tile getTilePosition(double x, double y) {
        return getTile((int) (x / tileSize), (int) (y / tileSize));
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public double getSpawnX() {
        return this.spawnX;
    }

    public double getSpawnY() {
        return this.spawnY;
    }

    public int getBaseX() {
        return this.baseX;
    }

    public int getBaseY() {
        return this.baseY;
    }

    /**
     * Asettaa tornin halutulle paikalle.
     *
     * @param x leveysarvo
     * @param y syvyysarvo
     */
    public void setTower(double x, double y) {
        Tile towerTile = new Tile((int) (x / tileSize), (int) (y / tileSize));
        towerTile.setType("TOWER");
        tiles[(int) (x / tileSize)][(int) (y / tileSize)] = towerTile;
    }

    public int distBetween(Tile current, Tile neighbour) {
        return 1;
    }

    public Tile getStart() {
        return this.start;
    }

    public Tile getGoal() {
        return this.goal;
    }

    /**
     * Antaa ruudun naapurit peliruudukosta
     *
     * @param current käsiteltävä ruutu
     * @return lista naapureista
     */
    public ArrayList<Tile> getNeighbours(Tile current) {
        ArrayList<Tile> neighbours = new ArrayList<>();

        if (left(current)) {
            Tile neighbour = this.tiles[current.getX()][current.getY() - 1];
            addNeighbour(neighbours, neighbour);
        }

        if (right(current)) {
            Tile neighbour = this.tiles[current.getX()][current.getY() + 1];
            addNeighbour(neighbours, neighbour);
        }

        if (up(current)) {
            Tile neighbour = this.tiles[current.getX() - 1][current.getY()];
            addNeighbour(neighbours, neighbour);
        }

        if (down(current)) {
            Tile neighbour = this.tiles[current.getX() + 1][current.getY()];
            addNeighbour(neighbours, neighbour);
        }
        return neighbours;
    }

    private void addNeighbour(ArrayList<Tile> neighbours, Tile neighbour) {
        if (neighbour.isObstacle() != true && neighbour.isVisited() != true) {
            neighbours.add(neighbour);
        }

    }

    private boolean left(Tile current) {
        return current.getY() > 0;
    }

    private boolean right(Tile current) {
        return current.getY() < this.getWidth() - 1;
    }

    private boolean up(Tile current) {
        return current.getX() > 0;
    }

    private boolean down(Tile current) {
        return current.getX() < this.getHeight() - 1;
    }

    /**
     * Visualisoi ruudukon
     *
     * @param stack
     */
    void visualize(ArrayList stack) {
        visualizeHelp();
        for (int j = 0; j < this.getHeight(); j++) {
            for (int i = 0; i < this.getWidth(); i++) {
                if (tiles[j][i] == start) {
                    System.out.print("[S]");
                } else if (tiles[j][i] == goal) {
                    System.out.print("[G]");
                } else if (tiles[j][i].isObstacle()) {
                    System.out.print("[#]");
                } else if (stack.contains(this.getTile(j, i))) {
                    System.out.print("[p]");
                } else if (this.getTile(j, i).isVisited()) {
                    System.out.print("[v]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    private void visualizeHelp() {
        System.out.println("\nBoard:");
        System.out.println("start = S");
        System.out.println("goal = G");
        System.out.println("obstacle = #");
        System.out.println("path = p");
        System.out.println("visited = v \n");
    }

    boolean hitBase(double positionY, double positionX) {
        return (int) (positionY / tileSize) == baseY
                && (int) (positionX / tileSize) == baseX;
    }

}
