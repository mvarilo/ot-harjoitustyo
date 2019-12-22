/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MV
 */
public class TowerTest {

    Tower tower;

    public TowerTest() {
        tower = new Tower(1.0, 1.0);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getPositionX method, of class Tower.
     */
    @Test
    public void testGetPositionX() {
        double expResult = 1.0;
        double result = tower.getPositionX();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of getPositionY method, of class Tower.
     */
    @Test
    public void testGetPositionY() {
        double expResult = 1.0;
        double result = tower.getPositionY();
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of getTarget method, of class Tower.
     */
    @Test
    public void testGetTargetNull() {
        Tower instance = new Tower();
        assertEquals(null, tower.getTarget());
    }

    /**
     * Test of findTarget method, of class Tower.
     */
    @Test
    public void testFindTargetAndCanShoot() {
        System.out.println("findTarget");
        List<Enemy> targetList = new ArrayList<>();
        Enemy enemy = new Enemy(1.0, 1.0, 10);
        targetList.add(enemy);
        Tower instance = new Tower();
        instance.findTarget(targetList);
        instance.shootTarget(enemy, 0);
        assertEquals(1, enemy.getHealth());
    }

    @Test
    public void testFindTargetAndCannotShoot() {
        System.out.println("findTarget");
        List<Enemy> targetList = new ArrayList<>();
        Enemy enemy = new Enemy(999.0, 999.0, 10);
        targetList.add(enemy);
        Tower instance = new Tower();
        instance.findTarget(targetList);
        instance.shootTarget(enemy, 0);
        assertEquals(2, enemy.getHealth());
    }

}
