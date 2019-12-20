/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import towerdef.domain.TowerDefense;

/**
 *
 * @author MV
 */
public class DaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File userFile;
    Dao dao;

    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");
        dao = new Dao("testfile_users.txt");
        TowerDefense towerDefense = new TowerDefense();
        dao.save(towerDefense);
    }

    @Test
    public void readCorrectly() throws IOException, ClassNotFoundException {
        TowerDefense towerDef = dao.load();
        TowerDefense comparison = new TowerDefense();
        assertEquals(towerDef.getHealth(), comparison.getHealth());
    }

    @Test
    public void canSave() throws Exception {
        TowerDefense save = new TowerDefense();
        dao.save(save);
    }

    @Test
    public void valuesAddedCanBeRead() throws Exception {
        TowerDefense save = new TowerDefense();
        save.setMoney(1000);
        dao.save(save);
        TowerDefense towerDef = dao.load();
        TowerDefense comparison = new TowerDefense();
        comparison.setMoney(1000);
        assertEquals(towerDef.getMoney(), comparison.getMoney());
    }

    @After
    public void tearDown() {
        userFile.delete();
    }

}
