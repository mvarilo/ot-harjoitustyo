/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Toteuttaa A*-algoritmiin pohjautuvan lyhimmän reitin etsinnän. Javan
 * tietorakenteita käyttäen. Perustuu Wikipedian pseudokoodiin.
 * http://en.wikipedia.org/wiki/A*_search_algorithm
 *
 *
 * @author MV
 */
public class Astar implements Serializable {

    private Board board;
    private Tile start;
    private Tile goal;
    private PriorityQueue<Tile> open;
    private ArrayList<Tile> evaluated;
    private HashMap<Tile, Tile> cameFrom;
    private Stack<Tile> stack;
    private ArrayList<Tile> path;

    /**
     * Konstruktori saa parametreinä pelilaudan, lähtö- ja maalisolmun.
     *
     * @param board pelilauta
     *
     */
    public Astar(Board board) {
        this.board = board;
        this.start = this.board.getStart();
        this.goal = this.board.getGoal();
        this.open = new PriorityQueue<>();
        this.evaluated = new ArrayList<>();
        this.path = new ArrayList<>();
        this.cameFrom = new HashMap<>();
        this.stack = new Stack<>();
    }

    /**
     * Etsii lyhimmän PriorityQueuen avulla
     *
     *
     */
    public void searchPriorityQueue() {
        this.open.add(start);

        this.start.setGScore(0);
        this.start.setFScore(this.goal);

        while (!this.open.isEmpty()) {
            Tile current = this.open.poll();

            if (current.equals(goal)) {
                reconstructPath(cameFrom, goal);
            }

            this.open.remove(current);
            this.evaluated.add(current);

            handleNeighbours(current);

        }

    }

    /**
     * Käy läpi solmut.
     *
     */
    private void handleNeighbours(Tile current) {
        ArrayList<Tile> neighbours = this.board.getNeighbours(current);
        for (Tile neighbour : neighbours) {
            if (!evaluated.contains(neighbour)) {
                int tentativeGScore = current.getGScore() + board.distBetween(current, neighbour);

                if (!this.open.contains(neighbour) || tentativeGScore < neighbour.getGScore()) {
                    cameFrom.put(neighbour, current);
                    neighbour.setGScore(tentativeGScore);
                    neighbour.setFScore(goal);
                }
                if (!this.open.contains(neighbour)) {
                    this.open.add(neighbour);
                }
            }
        }
    }

    /**
     * Rekonstruoi lyhimmän reitin.
     *
     */
    private void reconstructPath(HashMap<Tile, Tile> cameFrom, Tile currentNode) {

        Tile current = this.goal;
        this.stack = new Stack<>();

        while (!(current.equals(this.start))) {
            this.stack.push(current);
            current = cameFrom.get(current);
        }

        while (!this.stack.empty()) {
            current = this.stack.pop();
            path.add(current);

        }
    }

    public ArrayList getPath() {
        return this.path;
    }
}
