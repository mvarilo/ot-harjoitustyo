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
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MV
 */
public class AstarTest {

    Board board;
    Astar astar;
    Tile start, goal;

    public AstarTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.board = new Board();
        this.astar = new Astar(board);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSearchPriorityQueue() {
        this.astar.searchPriorityQueue();
        ArrayList path = this.astar.getPath();

        assertEquals("[x: 6, y: 1, x: 6, y: 2, x: 6, y: 3, x: 6, y: 4, x: 6, y: 5"
                + ", x: 6, y: 6, x: 6, y: 7, x: 6, y: 8, x: 6, y: 9, x: 6, y: 10,"
                + " x: 6, y: 11, x: 6, y: 12, x: 6, y: 13, x: 6, y: 14, x: 6,"
                + " y: 15, x: 7, y: 15, x: 8, y: 15, x: 9, y: 15, x: 10, y: 15"
                + ", x: 10, y: 14, x: 10, y: 13, x: 10, y: 12, x: 10, y: 11, x:"
                + " 10, y: 10, x: 10, y: 9, x: 10, y: 8, x: 10, y: 7, x: 10,"
                + " y: 6, x: 10, y: 5, x: 11, y: 5, x: 12, y: 5, x: 13, y: 5,"
                + " x: 14, y: 5, x: 15, y: 5, x: 15, y: 6, x: 15, y: 7, x: 15,"
                + " y: 8, x: 15, y: 9, x: 15, y: 10, x: 15, y: 11, x: 15, y: 12,"
                + " x: 15, y: 13, x: 15, y: 14, x: 15, y: 15, x: 15, y: 16,"
                + " x: 15, y: 17, x: 15, y: 18, x: 15, y: 19, x: 15, y: 20,"
                + " x: 15, y: 21, x: 15, y: 22, x: 15, y: 23, x: 15, y: 24,"
                + " x: 15, y: 25, x: 15, y: 26, x: 15, y: 27, x: 15, y: 28,"
                + " x: 15, y: 29]", path.toString());
    }

    @Test
    public void testSearchPriorityQueueEmpty() {
        this.board = new Board();
        this.board.emptyBoard();
        this.astar = new Astar(board);
        this.astar.searchPriorityQueue();
    }

}
