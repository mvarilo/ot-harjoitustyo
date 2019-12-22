/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

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
public class TileTest {

    Tile tile;

    public TileTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.tile = new Tile(1, 1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetX() {
        int s = this.tile.getX();
        assertEquals(s, 1);
    }

    @Test
    public void testGetY() {
        int s = this.tile.getY();
        assertEquals(s, 1);
    }

    @Test
    public void testToString() {
        String s = this.tile.toString();
        assertEquals("x: 1, y: 1", s);
    }

    @Test
    public void testObstacle() {
        Tile instance = new Tile(0, 0);
        instance.setObstacle();
        boolean expResult = true;
        boolean result = instance.isObstacle();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testObstacleString() {
        Tile instance = new Tile(0, 0, "WALL");
        instance.setObstacle();
        boolean expResult = true;
        boolean result = instance.isObstacle();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testObstacleFalse() {
        Tile instance = new Tile(0, 0);
        boolean expResult = false;
        boolean result = instance.isObstacle();
        assertEquals(expResult, result);
    }

    @Test
    public void testGScore() {
        int i = 3;
        Tile instance = new Tile(0, 0);
        instance.setGScore(i);
        int expResult = 3;
        int result = instance.getGScore();
        assertEquals(expResult, result);
    }

    @Test
    public void testCompareToNegative() {
        Tile instance = new Tile(2, 2);
        instance.setFScore(this.tile);
        this.tile.setFScore(instance);
        Tile second = new Tile(3, 3);
        int result = this.tile.compareTo(second);
        assertEquals(-1, result);
    }
    
    @Test
    public void testCompareToNeutral() {
        Tile instance = new Tile(2, 2);
        instance.setFScore(this.tile);
        this.tile.setFScore(instance);
        int result = this.tile.compareTo(instance);
        assertEquals(0, result);
    }

    @Test
    public void testPrevious() {
        Tile s = new Tile(1, 2);
        this.tile.setPrevious(s);
        String previous = this.tile.getPrevious().toString();
        assertEquals("x: 1, y: 2", previous);
    }

    @Test
    public void testVisited() {
        this.tile.setVisited();
        boolean result = this.tile.isVisited();

        assertEquals(true, result);
    }
}
