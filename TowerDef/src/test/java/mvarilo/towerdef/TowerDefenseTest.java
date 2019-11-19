/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvarilo.towerdef;

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
    public void dontBuyTowerIfNoMoney() {
        towerDefense.setMoney(0);
        assertEquals(false, towerDefense.buyTower());
    }
}
