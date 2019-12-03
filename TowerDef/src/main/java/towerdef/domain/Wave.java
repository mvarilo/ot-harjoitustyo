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
public class Wave {

    private int number;

    public Wave() {
        this.number = 1;
    }

    public int getNumber() {
        return this.number;
    }

    public void update() {
        this.number++;
    }

}
