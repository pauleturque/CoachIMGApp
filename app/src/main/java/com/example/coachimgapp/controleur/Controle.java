package com.example.coachimgapp.controleur;

import android.content.Context;

import com.example.coachimgapp.modele.AccesLocal;
import com.example.coachimgapp.modele.Profil;
import com.example.coachimgapp.outils.Serializer;

import java.util.Date;

public final class Controle {

    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveProfil";
    private static AccesLocal accesLocal;

    /**
     * Constructeur privé
     */
    private Controle(){
        super();
    }

    /**
     * Création de l'instance
     * @return instance
     */
    public static final Controle getInstance(Context contexte){
        if(instance == null){
            Controle.instance = new Controle();
            accesLocal = new AccesLocal(contexte);
            profil = accesLocal.recupDernier();
            recupSerialize(contexte);
        }
        return Controle.instance;
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme et 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte){
        profil = new Profil(new Date(), poids, taille, age, sexe);
        accesLocal.ajout(profil);
        Serializer.serialize(nomFic, profil, contexte);
    }

    /**
     * Récupération img de l'objet profil
     * @return img
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     * Récupération message de l'objet profil
     * @return message
     */
    public String getMessage(){
        return profil.getMessage();
    }

    /**
     * Récupération de l'objet sérialisé : profil
     * @param contexte
     */
    private static void recupSerialize(Context contexte){
        profil = (Profil)Serializer.deSerialize(nomFic, contexte);
    }

    public Integer getPoids(){
        if(profil == null){
            return null;
        } else{
            return profil.getPoids();
        }
    }

    public Integer getTaille(){
        if(profil == null){
            return null;
        } else{
            return profil.getTaille();
        }
    }

    public Integer getAge(){
        if(profil == null){
            return null;
        } else{
            return profil.getAge();
        }
    }

    public Integer getSexe(){
        if(profil == null){
            return null;
        } else{
            return profil.getSexe();
        }
    }
}
