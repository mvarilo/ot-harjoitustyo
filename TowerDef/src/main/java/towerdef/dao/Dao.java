/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towerdef.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import towerdef.domain.TowerDefense;

/**
 *
 * Kirjoittaa ja lataa ohjelman tilan tiedostoon.
 *
 * @author MV
 */
public class Dao {

    private String path;

    /**
     * Konstruktori saa parametreinä halutun tiedoston nimen.
     *
     * @param path
     *
     */
    public Dao(String path) {
        this.path = path;
    }

    /**
     * Tallentaa towerDefensen tiedstoon.
     *
     *
     * @param towerDefense
     * @throws java.io.IOException jos ei pysty kirjoittamaan tiedostoon
     */
    public void save(TowerDefense towerDefense) throws IOException {
        try (FileOutputStream file = new FileOutputStream(path);
                ObjectOutputStream output = new ObjectOutputStream(file)) {
            output.writeObject(towerDefense);
        }
    }

    /**
     *
     *
     * @return towerDefense antaa towerDefense-luokan palautuksena
     * @throws java.io.IOException jos ei löydä tiedostoa
     * @throws java.lang.ClassNotFoundException jos tiedossa ei ole oikeanlaista
     * dataa
     */
    public TowerDefense load() throws IOException, ClassNotFoundException {
        try (FileInputStream file = new FileInputStream(path); ObjectInputStream input = new ObjectInputStream(file)) {
            TowerDefense towerDefense = (TowerDefense) input.readObject();
            return towerDefense;
        }
    }

}
