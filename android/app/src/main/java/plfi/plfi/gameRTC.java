package plfi.plfi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class gameRTC extends AppCompatActivity {
    //Mes boutons
    Button buttonCarre;
    Button buttonTriangle;
    Button buttonCercle;

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
    }

    //TODO: REFAIRE IMAGE CARRE
    class ButtonCarreClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            imageViewClient.setImageResource(R.drawable.carre);
        }
    }
    //TODO: REFAIRE L'IMAGE TRIANGLE
    class ButtonTriangleClick implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            imageViewClient.setImageResource(R.drawable.triangle);
        }
    }
    //TODO: REFAIRE L'IMAGE CERCLE
    class ButtonCercleClick implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            imageInitClient.setImageResource(R.drawable.cercle);
        }
    }
}



