package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaOikein() {
        kortti.lataaRahaa(90);
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaOikein() {
        kortti.otaRahaa(10);
        assertEquals("saldo: 0.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaLiikaa() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void metodiTrueJosRahatRiittivat() {
        assertEquals(true, kortti.otaRahaa(1));
    }
    
    @Test
    public void metodiFalseJosRahatEivatRiittaneet() {
        assertEquals(false, kortti.otaRahaa(100));
    }
}
