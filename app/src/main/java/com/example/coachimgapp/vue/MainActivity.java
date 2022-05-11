package com.example.coachimgapp.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coachimgapp.R;
import com.example.coachimgapp.controleur.Controle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.controle = Controle.getInstance(this);
        init();

    }

    //propriétés
    private EditText txtPoids;
    private EditText txtAge;
    private EditText txtTaille;
    private RadioButton rbtnHomme;
    private RadioButton rbtnFemme;
    private ImageView imgNormal;
    private TextView txtCalcul;
    private Controle controle;

    /**
     * initialisation des liens avec les objects graphiques
     */
    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        rbtnHomme = (RadioButton) findViewById(R.id.rbtnHomme);
        rbtnFemme = (RadioButton) findViewById(R.id.rbtnFemme);
        imgNormal = (ImageView) findViewById(R.id.imgNormal);
        txtCalcul = (TextView) findViewById(R.id.txtCalcul);
        ecouteCalculClic();
        recupProfil();
    }

    /**
     * Écoute l'évènement clic sur bouton calcul
     */
    private void ecouteCalculClic(){
        ((Button) findViewById(R.id.btnCalculer)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                //Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT).show();
                Date dateMesure;
                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sexe = 0;
                //récupération des données saisies
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch(Exception e) {e.printStackTrace();};
                if(rbtnHomme.isChecked()){
                    sexe = 1;
                }
                // contrôle des données saisies
                if(poids == 0 || taille == 0 || age == 0){
                    Toast.makeText(MainActivity.this, "Saisie incorrecte !", Toast.LENGTH_SHORT).show();
                } else {
                    affichageResultat(new Date(), poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * Affichage de l'IMG, du message et de l'image correspondante
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void affichageResultat(Date dateMesure, Integer poids, Integer taille, Integer age, Integer sexe){
        //Création du profil et récupération de l'ensemble des informations
        this.controle.creerProfil(poids, taille, age, sexe, this);
        float img = this.controle.getImg();
        String msg = this.controle.getMessage();

        //Affichage
        if(msg == "Normal"){
            imgNormal.setImageResource(R.drawable.normal);
            txtCalcul.setTextColor(Color.GREEN);
        } else {
            if(msg == "Trop faible"){
                imgNormal.setImageResource((R.drawable.maigre));
                txtCalcul.setTextColor(Color.RED);
            } else {
                imgNormal.setImageResource(R.drawable.graisse);
                txtCalcul.setTextColor(Color.RED);
            }
        }
        txtCalcul.setText(String.format("%,01f", img) + " : IMG " + msg);
    }

    /**
     * Récupération du profil s'il a été sérialisé
     */
    private void recupProfil(){
        try{
            if(controle.getPoids() != null){
                txtPoids.setText(controle.getPoids().toString());
                txtAge.setText(controle.getAge().toString());
                txtTaille.setText(controle.getTaille().toString());
                rbtnFemme.setChecked(true);
                if(controle.getSexe() == 1){
                    rbtnHomme.setChecked(true);
                }
                //simule le clic sur le bouton
                ((Button)findViewById(R.id.btnCalculer)).performClick();
            }
            }catch (NullPointerException e){
            e.printStackTrace();
        }

    }



}