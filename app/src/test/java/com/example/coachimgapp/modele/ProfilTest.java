package com.example.coachimgapp.modele;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Date;

public class ProfilTest extends TestCase {

    //creation profil test
    private Profil profil = new Profil(new Date(), 67, 165, 35, 0);
    //resultat IMG attendu
    private float IMG = (float)32.2;
    private String message = "Trop élevé";

    @Test
    public void testGetImg() throws Exception {
        assertEquals(IMG, profil.getImg(), (float)0.1);
    }

    @Test
    public void testGetMessage() throws Exception{
        assertEquals(message, profil.getMessage());
    }
}