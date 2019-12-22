/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.io.Serializable;

/**
 *
 * Yksittäinen ruutu pelilaudalla
 */
public class Tile implements Comparable<Tile>, Serializable {

    private int x, y;
    private boolean obstacle;
    private int roadToStart;
    private int fScore;
    private int index;
    private Tile previous;
    private boolean visited;
    private String type;

    /**
     * Luo ruudun ja kertoo sille sen koordinaatit
     *
     * @param i
     * @param j
     */
    public Tile(int i, int j) {
        this.roadToStart = Integer.MAX_VALUE;
        this.x = i;
        this.y = j;
        this.obstacle = false;
        this.previous = null;
        this.visited = false;
    }

    /**
     * Luo ruudun ja kertoo sille sen koordinaatit sekä sen tyypin
     *
     * @param i
     * @param j
     * @param type ruudun tyyppi, esim. seinä, maali, tyhjä, tie
     */
    public Tile(int i, int j, String type) {
        this.roadToStart = Integer.MAX_VALUE;
        this.x = i;
        this.y = j;
        this.type = type;
        this.obstacle = false;
        this.previous = null;
        this.visited = false;
    }

    public void setGScore(int i) {
        this.roadToStart = i;
    }

    public void setFScore(Tile goal) {
        this.fScore = Math.abs(this.x - goal.getX()) + Math.abs(this.y - goal.getY()) + getGScore();
    }

    /**
     * Palauttaa onko ruutu käyty läpi reitinhakutarkoituksessa.
     *
     * @return
     */
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

    /**
     * Palauttaa voiko ruudun läpi kulkea
     *
     * @return
     */
    public boolean isObstacle() {
        return "WALL".equals(this.type) || "TOWER".equals(this.type);
    }
    
    public void setObstacle() {
        this.type = "WALL";
    }

    public int getFScore() {
        return this.fScore;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public int compareTo(Tile o) {
        if (this.getFScore() < o.getFScore()) {
            return -1;
        } else if (this.getFScore() > o.getFScore()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "x: " + this.x + ", y: " + this.y;
    }
}
