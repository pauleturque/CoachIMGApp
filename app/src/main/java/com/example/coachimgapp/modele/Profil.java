package com.example.coachimgapp.modele;

import java.io.Serializable;
import java.util.Date;

public class Profil implements Serializable {

    /**
     * Constantes de base de l'indice de masse graisseuse
     */
    public static final Integer minFemme = 15;
    public static final Integer maxFemme = 30;
    public static final Integer minHomme = 10;
    public static final Integer maxHomme = 25;


    /**
     * Variables nécessaires au calcul de l'img
     */
    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float imgNormal;
    private String message;

    /**
     * Constructeur du profil
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(Date dateMesure, Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    // getters et setter des proprietes

    public Date getDate(){
        return dateMesure;
    }
    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return imgNormal;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Méthode de calcul de l'indice de masse graisseuse
     */
    private void calculIMG() {
        float tailleM = ((float)taille)/100;
        this.imgNormal = (float)((1.2 * poids / (tailleM * tailleM)) + (0.23 * age) - (10.83 * sexe) - 5.4);
    }

    /**
     * Méthode déterminant les constantes locales de masse graisseuse
     * Initialise le message personnalisé
     */
    private void resultIMG(){
        Integer min;
        Integer max;
        if(sexe == 0){ //femme
            min = minFemme;
            max = maxFemme;
        }else { //homme
            min = minHomme;
            max = maxHomme;
        }

        //message personnalise
        message = "Normal";
        if(imgNormal < min){
            message = "Trop faible";
        } else {
            if(imgNormal > max){
                message = "Trop élevé";
            }
        }
    }
}
