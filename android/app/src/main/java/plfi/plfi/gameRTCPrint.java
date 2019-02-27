package plfi.plfi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class gameRTCPrint extends AppCompatActivity {

    // Mes boutons
    Button new_content;
    Button save_btn;
    Button buttonCarre;
    Button buttonTriangle;
    Button buttonCercle;

    // Notre canvas
    gamePRINT gamePrint;

    // temporaire pour tester notre truck
    int imageId;

    // Image à afficher pour le serveur
    ImageView imageViewServeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.print);

        //image du serveur
        imageViewServeur = (ImageView) findViewById(R.id.imageServeur);

        // Clear notre Canvas
        new_content = (Button) findViewById(R.id.new_btn);
        new_content.setOnClickListener(new ButtonNewContent());

        //Carre
        buttonCarre = (Button) findViewById(R.id.buttonCarre);
        buttonCarre.setOnClickListener(new ButtonCarreClick());

        //Triangle
        buttonTriangle = (Button) findViewById(R.id.buttonTriangle);
        buttonTriangle.setOnClickListener(new ButtonTriangleClick());

        //Cercle
        buttonCercle = (Button) findViewById(R.id.buttonCercle);
        buttonCercle.setOnClickListener(new ButtonCercleClick());

        // Envoit au serveur
        save_btn = (Button) findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new ButtonSave_Btn());


    }

    // Nouvelle page
    class ButtonNewContent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            gamePrint = (gamePRINT) findViewById(R.id.drawing);
            gamePrint.reset();
        }
    }



    //TODO: REFAIRE IMAGE CARRE
    class ButtonCarreClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            imageId = 3;
        }
    }

    //TODO: REFAIRE L'IMAGE TRIANGLE
    class ButtonTriangleClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imageId = 2;
        }
    }

    //TODO: REFAIRE L'IMAGE CERCLE
    class ButtonCercleClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imageId = 4;

        }
    }

    // TODO: A finir ! !! TAOOOOOOOOOOOOOOO C'EST ICI POUR LE SERVEUR ! (remplace le bouton VALIDER) gros copie colle )
    class ButtonSave_Btn implements View.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }


}

