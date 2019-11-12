/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author MV
 */
public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti maksukortti;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(0);
    }

    @Test
    public void kassassaRahaaOikeaSumma() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void edullisiaLounaitaMyytyOikeaMaara() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaitaLounaitaMyytyOikeaMaara() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiToimii() {
        kassapaate.syoEdullisesti(500);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiToimii() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiOikeaVaihtoraha() {
        assertEquals(20, kassapaate.syoEdullisesti(260));
    }

    @Test
    public void syoMaukkaastiOikeaVaihtoraha() {
        assertEquals(20, kassapaate.syoMaukkaasti(420));
    }

    @Test
    public void syoEdullisestiKasvattaaMyytyjaJosRahaa() {
        kassapaate.syoEdullisesti(240);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKasvattaaMyytyjaJosRahaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKasvattaaMyytyjaJosEiRahaa() {
        kassapaate.syoEdullisesti(100);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKasvattaaMyytyjaJosEiRahaa() {
        kassapaate.syoMaukkaasti(100);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void rahatPalautetaanVaihtorahanaJosEiRahaaEdulliseen() {
        assertEquals(5, kassapaate.syoEdullisesti(5));
    }

    @Test
    public void rahatPalautetaanVaihtorahanaJosEiRahaaMaukkaaseen() {
        assertEquals(5, kassapaate.syoMaukkaasti(5));
    }

    @Test
    public void syoEdullisestiEiKasvataJosEiRahaa() {
        kassapaate.syoEdullisesti(5);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiEiKasvataJosEiRahaa() {
        kassapaate.syoMaukkaasti(5);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKortillaPalauttaaTrueJosRahaa() {
        maksukortti.lataaRahaa(1000);
        assertEquals(true, kassapaate.syoEdullisesti(maksukortti));
    }

    @Test
    public void syoMaukkaastiKortillaPalauttaaTrueJosRahaa() {
        maksukortti.lataaRahaa(1000);
        assertEquals(true, kassapaate.syoMaukkaasti(maksukortti));
    }

    @Test
    public void syoEdullisestiKortillaKasvattaaMyytyjaJosRahaa() {
        maksukortti.lataaRahaa(1000);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKortillaKasvattaaMyytyjaJosRahaa() {
        maksukortti.lataaRahaa(1000);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKortillaPalauttaaFalseJosEiRahaa() {
        maksukortti.lataaRahaa(0);
        assertEquals(false, kassapaate.syoEdullisesti(maksukortti));
    }

    @Test
    public void syoMaukkaastiKortillaPalauttaaFalseJosEiRahaa() {
        maksukortti.lataaRahaa(0);
        assertEquals(false, kassapaate.syoMaukkaasti(maksukortti));
    }

    @Test
    public void syoEdullisestiKortillaVeloittaaSummanJosRahaa() {
        maksukortti.lataaRahaa(1000);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(760, maksukortti.saldo());
    }

    @Test
    public void syoMaukkaastiKortillaVeloittaaSummanJosRahaa() {
        maksukortti.lataaRahaa(1000);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(600, maksukortti.saldo());
    }

    @Test
    public void syoEdullisestiKortillaEiVeloittaSummanJosEiRahaa() {
        maksukortti.lataaRahaa(100);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(100, maksukortti.saldo());
    }

    @Test
    public void syoMaukkaastiKortillaEiVeloittaSummanJosEiRahaa() {
        maksukortti.lataaRahaa(100);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(100, maksukortti.saldo());
    }

    @Test
    public void syoEdullisestiKortillaEiKasvataMyytyjaLounaitaJosEiRahaa() {
        maksukortti.lataaRahaa(100);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKortillaEiKasvataMyytyjaLounaitaJosEiRahaa() {
        maksukortti.lataaRahaa(100);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KassassanRahaEiMuutuKortillaOstaessaEdullinen() {
        maksukortti.lataaRahaa(1000);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void KassassanRahaEiMuutuKortillaOstaessaMaukas() {
        maksukortti.lataaRahaa(1000);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleRahaaToimii() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(100100, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleRahaaEiVoiNegatiivista() {
        kassapaate.lataaRahaaKortille(maksukortti, -100);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
