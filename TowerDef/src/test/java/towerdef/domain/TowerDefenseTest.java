/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.domain;

import java.util.Iterator;
import java.util.List;
import towerdef.domain.TowerDefense;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author MV
 */
public class TowerDefenseTest {

    TowerDefense towerDefense;

    @Before
    public void setUp() {
        towerDefense = new TowerDefense();
    }

    @Test
    public void moneyCorrect() {
        assertEquals(40, towerDefense.getMoney());
    }

    @Test
    public void putMoneyCorrectly() {
        towerDefense.setMoney(50);
        assertEquals(50, towerDefense.getMoney());
    }

    @Test
    public void buyTowerCorrectly() {
        towerDefense.buyTower();
        assertEquals(35, towerDefense.getMoney());
    }

    @Test
    public void buyTowerCorrectlyPosition() {
        towerDefense.buyTower(1.0, 1.0);
        assertEquals(35, towerDefense.getMoney());
    }

    @Test
    public void dontBuyTowerIfNoMoney() {
        towerDefense.setMoney(0);
        assertEquals(false, towerDefense.buyTower());
    }

    @Test
    public void dontBuyTowerIfNoMoneyPosition() {
        towerDefense.setMoney(0);
        assertEquals(false, towerDefense.buyTower(1.0, 1.0));
    }

    @Test
    public void getCorrectWave() {
        assertEquals(0, towerDefense.getWaveNumber());
    }

    @Test
    public void getCorrectHealth() {
        assertEquals(100, towerDefense.getHealth());
    }

    @Test
    public void isGameOverNo() {
        assertEquals(false, towerDefense.isGameOver());
    }

    @Test
    public void isGameOverYes() {
        towerDefense.takeDamage(100);
        assertEquals(true, towerDefense.isGameOver());
    }

    @Test
    public void isWaveOver() {
        assertEquals(true, towerDefense.isWaveOver());
    }

    @Test
    public void nextWave() {
        towerDefense.newWave();
        assertEquals(1, towerDefense.getWaveNumber());
    }

    @Test
    public void getTowerPosition() {
        towerDefense.buyTower(1.0, 1.0);
        List<Tower> towers = towerDefense.getTowers();
        assertEquals(1.0, towers.get(0).getPositionX());
        assertEquals(1.0, towers.get(0).getPositionY());
    }
}
