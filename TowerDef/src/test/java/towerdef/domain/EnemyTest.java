/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MV
 */
public class EnemyTest {

    Enemy enemy;

    public EnemyTest() {
        enemy = new Enemy(1.0, 1.0, 10);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getPositionX method, of class Enemy.
     */
    @Test
    public void testGetPositionX() {
        Enemy instance = new Enemy(1.0, 1.0, 10);
        assertEquals(1.0, instance.getPositionX(), 0.001);
    }

    /**
     * Test of getPositionY method, of class Enemy.
     */
    @Test
    public void testGetPositionY() {
        Enemy instance = new Enemy(1.0, 1.0, 10);
        assertEquals(1.0, instance.getPositionY(), 0.001);
    }

    /**
     * Test of move method, of class Enemy.
     */
    @Test
    public void testMove() {
        enemy.move(1.0, 1.0);
        assertEquals(11.0, enemy.getPositionY(), 0.001);
        assertEquals(11.0, enemy.getPositionX(), 0.001);
    }

    /**
     * Test of clone method, of class Enemy.
     */
    @Test
    public void testClone() {
        Enemy result = enemy.clone();

        assertEquals(enemy.getHealth(), result.getHealth());
        assertEquals(enemy.getSpeed(), result.getSpeed(), 0.001);
    }

    /**
     * Test of speedUp method, of class Enemy.
     */
    @Test
    public void testSpeedUp() {
        enemy.speedUp();
        assertEquals(enemy.getSpeed(), 20, 0.001);
    }

    /**
     * Test of takeDamage method, of class Enemy.
     */
    @Test
    public void testTakeDamage() {
        enemy.takeDamage(1);
        assertEquals(enemy.getHealth(), 1);
    }

    /**
     * Test of getHealth method, of class Enemy.
     */
    @Test
    public void testGetHealth() {
        assertEquals(enemy.getHealth(), 2);

    }

}
