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
import java.util.Stack;
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
//        Stack stack = this.astar.getStack();
//
//        String solmu1 = stack.pop().toString();
//        String solmu2 = stack.pop().toString();
//        String solmu3 = stack.pop().toString();
//        String solmu4 = stack.pop().toString();
//        String solmu5 = stack.pop().toString();
//        String solmu6 = stack.pop().toString();
//        String solmu7 = stack.pop().toString();
//        String solmu8 = stack.pop().toString();
//        String solmu9 = stack.pop().toString();
//        String solmu10 = stack.pop().toString();
//        String solmu11 = stack.pop().toString();
//
//        String lyhinPolku = solmu1 + "; " + solmu2 + "; " + solmu3 + "; " + solmu4
//                + "; " + solmu5 + "; " + solmu6 + "; " + solmu7 + "; " + solmu8 + "; " + solmu9
//                + "; " + solmu10 + "; " + solmu11;
//
//        assertEquals("x: 2, y: 0; x: 3, y: 0; x: 3, y: 1; x: 3, y: 2; x: 4, y: 2; x: 5, y: 2; x: 6, y: 2; x: 6, y: 3; x: 6, y: 4; x: 5, y: 4; x: 4, y: 4",
//                lyhinPolku);
    }

}
