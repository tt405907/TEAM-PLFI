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

import commun.Coup;
import commun.Dessin;
import commun.Forme;
import commun.Identification;
import commun.jeux.ResultCTR;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class gameRTC extends AppCompatActivity {
    //Mes boutons
    Button buttonCarre;
    Button buttonTriangle;
    Button buttonCercle;
<<<<<<< HEAD
    Button buttonValider;
=======
    Button buttonValid;
>>>>>>> 3faeff422b26830ba7d9170686e4878c42aeb23d

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


        //Carre
        buttonCarre = (Button) findViewById(R.id.buttonCarre);
        buttonCarre.setOnClickListener(new ButtonCarreClick());

        //Triangle
        buttonTriangle = (Button) findViewById(R.id.buttonTriangle);
        buttonTriangle.setOnClickListener(new ButtonTriangleClick());

        //Cercle
        buttonCercle = (Button) findViewById(R.id.buttonCercle);
        buttonCercle.setOnClickListener(new ButtonCercleClick());

<<<<<<< HEAD
        //Valider
        buttonValider = (Button) findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(new ButtonValiderClick());
=======
        //valide au serveur
        buttonValid = (Button) findViewById(R.id.buttonValider);
        buttonValid.setOnClickListener(new ButtonValid());
>>>>>>> 3faeff422b26830ba7d9170686e4878c42aeb23d
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
<<<<<<< HEAD
        }
    }
    class ButtonValiderClick implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            //TODO: METTRE L'APPELLE AU SERVEUR ET
=======
            imageId = 4;
        }
    }

    class ButtonValid implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            controleur = new Controleur();
            Connexion connexion = new Connexion("http://127.0.0.1:10101", controleur);
            connexion.seConnecter();
            if(imageId == 2){
                connexion.sendForme(2);
            }
            else if(imageId == 3){
                connexion.sendForme(3);
            }
            else if(imageId == 4){
                connexion.sendForme(4);
            }
            JSONObject r = connexion.getRes();  // JSONOBJECT de la response a changer pour repponse
            int forme;

            try{
                String winner = r.getString("Result");
                String imageserv = r.getString("formeServeur");
                forme = Integer.parseInt(imageserv);
            }
            catch (JSONException e){
                System.out.println(e);
            }
            forme = 2;

            imageInitServeur = (ImageView) findViewById(R.id.imageViewServeur);
            if(forme == 2){
                imageInitServeur.setImageResource(R.drawable.triangle);
            }
            else if(forme == 3){
                imageInitServeur.setImageResource(R.drawable.carre);
            }
            else if(forme == 4){
                imageInitServeur.setImageResource(R.drawable.cercle);
            }
        }
>>>>>>> 3faeff422b26830ba7d9170686e4878c42aeb23d
        }
    }




