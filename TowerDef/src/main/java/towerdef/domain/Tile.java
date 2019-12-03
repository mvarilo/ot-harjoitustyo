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
public enum Tile implements Comparable<Tile> {

    WALL, ROAD, BASE, SPAWN, TOWER, EMPTY;

    private int x, y;
    private boolean obstacle;
    private int roadToStart;
    private int fScore;
    private int index;
    private Tile previous;
    private boolean visited;

    public void setGScore(int i) {
        this.roadToStart = i;
    }

    public void setFScore(Tile goal) {
        this.fScore = Math.abs(this.x - goal.getX()) + Math.abs(this.y - goal.getY()) + getGScore();
    }

    public boolean isVisited() {
        if (this.visited == true) {
            return true;
        }
        return false;
    }

    public Tile getPrevious() {
        return this.previous;
    }

    public int getGScore() {
        return this.roadToStart;
    }

    public void setPrevious(Tile current) {
        this.previous = current;
    }

    public void setVisited() {
        this.visited = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    boolean isObstacle() {
        return obstacle;
    }

    public int getFScore() {
        return this.fScore;
    }
}
