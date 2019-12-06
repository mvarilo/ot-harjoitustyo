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
    private Stack<Tile> stack;
    private ArrayList<Tile> path;

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
        this.path = new ArrayList<>();
        this.cameFrom = new HashMap<>();
        this.stack = new Stack<>();
    }

    public void searchPriorityQueue() {
        this.open.add(start);

        this.start.setGScore(0);
        this.start.setFScore(this.goal);

        int test = 0;

        while (!this.open.isEmpty()) {
            Tile current = this.open.poll();
            System.out.println(test++);

            if (current.equals(goal)) {
                System.out.println("2");
                reconstructPath(cameFrom, goal);
            }

            this.open.remove(current);
            this.evaluated.add(current);

            ArrayList<Tile> neighbours = this.board.getNeighbours(current);
            for (Tile neighbour : neighbours) {
                if (!evaluated.contains(neighbour)) {
                    int tentative_g_score = current.getGScore() + board.distBetween(current, neighbour);

                    if (!this.open.contains(neighbour) || tentative_g_score < neighbour.getGScore()) {
                        cameFrom.put(neighbour, current);
                        neighbour.setGScore(tentative_g_score);
                        neighbour.setFScore(goal);
                    }
                    if (!this.open.contains(neighbour)) {
                        this.open.add(neighbour);
                    }
                }
            }

        }

    }

    private void reconstructPath(HashMap<Tile, Tile> cameFrom, Tile currentNode) {
        System.out.println(this.start.toString());

        Tile current = this.goal;
        this.stack = new Stack<>();

        while (!(current.equals(this.start))) {
            this.stack.push(current);
            current = cameFrom.get(current);
        }
        while (!this.stack.empty()) {;
            current = this.stack.pop();
            path.add(current);
            System.out.println(current.toString());
        }
    }

    public Stack getStack() {
        return this.stack;
    }

    public ArrayList getPath() {
        return this.path;
    }
}
