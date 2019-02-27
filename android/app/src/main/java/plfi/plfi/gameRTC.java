package plfi.plfi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.util.Log;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import commun.Dessin;
import commun.Forme;
import commun.Identification;
import commun.jeux.ResultCTR;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class gameRTC extends AppCompatActivity  implements DisplayRTC{
    //Mes boutons
    Button buttonCarre;
    Button buttonTriangle;
    Button buttonCercle;
    Button buttonValid;

    Connexion connexion;
    Controleur controleur;
    int imageId;
    // Mes Images à afficher pour le serveur et le client
    ImageView imageInitClient; // Image de depart du cleint
    ImageView imageInitServeur; // Image de depart a voir si on l'a met pas apres le resultat pour temp, a voir avec le groupe
    ImageView imageViewClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trianglecerclecarre);

        //Image Client
        imageViewClient = (ImageView) findViewById(R.id.imageViewClient);
        imageInitServeur = (ImageView) findViewById(R.id.imageViewServeur);


        //Carre
        buttonCarre = (Button) findViewById(R.id.buttonCarre);
        buttonCarre.setOnClickListener(new ButtonCarreClick());

        //Triangle
        buttonTriangle = (Button) findViewById(R.id.buttonTriangle);
        buttonTriangle.setOnClickListener(new ButtonTriangleClick());

        //Cercle
        buttonCercle = (Button) findViewById(R.id.buttonCercle);
        buttonCercle.setOnClickListener(new ButtonCercleClick());

        //valide au serveur
        buttonValid = (Button) findViewById(R.id.buttonValider);
        buttonValid.setOnClickListener(new ButtonValid());

        controleur = new Controleur( gameRTC.this);
        Connexion connexion = new Connexion("192.168.0.101","10101", controleur);
        connexion.seConnecter();
        this.connexion = connexion;
    }

    @Override
    public void updateGame(String winner,final String img) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(img.equals("TRIANGLE")){
                    imageInitServeur.setImageResource(R.drawable.triangle);
                }
                else if(img.equals("CARRE")){
                    imageInitServeur.setImageResource(R.drawable.carre);
                }
                else if(img.equals("ROND")){
                    imageInitServeur.setImageResource(R.drawable.cercle);
                }
                else{
                    imageInitServeur.setImageResource(R.drawable.carre);
                }
            }
        });
    }

    //TODO: REFAIRE IMAGE CARRE
    class ButtonCarreClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            imageViewClient.setImageResource(R.drawable.carre);
            imageId = 3;
        }
    }
    //TODO: REFAIRE L'IMAGE TRIANGLE
    class ButtonTriangleClick implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            imageViewClient.setImageResource(R.drawable.triangle);
            imageId = 2;
        }
    }
    //TODO: REFAIRE L'IMAGE CERCLE
    class ButtonCercleClick implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            imageViewClient.setImageResource(R.drawable.cercle);
            imageId = 4;
        }
    }


    class ButtonValid implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            if(imageId == 2){
                connexion.sendForme(Forme.TRIANGLE.ordinal());
            }
            else if(imageId == 3){
                connexion.sendForme(Forme.CARRE.ordinal());
            }
            else if(imageId == 4){
                connexion.sendForme(Forme.ROND.ordinal());
            }
            else{
                connexion.sendForme(Forme.TRIANGLE.ordinal());
            }  // JSONOBJECT de la response a changer pour repponse




        }
        }
    }




