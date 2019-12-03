/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * @author MV
 */
public class Astar {

    private Board board;
    private Tile start;
    private Tile goal;
    private PriorityQueue<Tile> open;
    private ArrayList<Tile> evaluated;
    private HashMap<Tile, Tile> cameFrom;
    private Stack stack;

    /**
     * Konstruktori saa parametreinä labyrintin, lähtö- ja maalisolmun.
     *
     * @param board
     */
    public Astar(Board board) {
        this.board = board;
        this.start = this.board.getStart();
        this.goal = this.board.getGoal();
        this.open = new PriorityQueue<>();
        this.evaluated = new ArrayList<>();
        this.cameFrom = new HashMap<>();
    }

    public boolean searchPriorityQueue() {
        this.open.add(start);
        this.start.setGScore(0);
        this.start.setFScore(this.goal);

        while (!this.open.isEmpty()) {
            Tile current = this.open.poll();

            if (current.equals(goal)) {
                reconstructPath();
                return true;
            }

            current.setVisited();
            handleNeighbours(current);
        }
        return false;

    }

    private void reconstructPath() {
        Tile s = this.goal;

        while (!(s.equals(this.start))) {
            this.stack.push(s);
            s = s.getPrevious();
        }
        this.stack.push(s);
    }

    private void handleNeighbours(Tile current) {

        ArrayList<Tile> neighbours = this.board.getNeighbours(current);

        for (Tile neighbour : neighbours) {

            if (!neighbour.isVisited()) {

                int arvioAlkuun = current.getGScore()
                        + board.distBetween(current, neighbour);

                if (!this.open.contains(neighbour) || arvioAlkuun < neighbour.getGScore()) {
                    neighbour.setPrevious(current);
                    neighbour.setGScore(arvioAlkuun);
                    neighbour.setFScore(goal);

                    if (!this.open.contains(neighbour)) {
                        this.open.add(neighbour);
                    }
                }

            }

        }

    }

    public Stack getStack() {
        return this.stack;
    }
}
