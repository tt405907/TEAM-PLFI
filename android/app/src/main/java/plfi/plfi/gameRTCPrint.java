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
    gamePRINT gamePrint;

    // Image à afficher pour le serveur
    ImageView imageViewServeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.print);

        //image du serveur
        imageViewServeur = (ImageView) findViewById(R.id.imageServeur);

        new_content = (Button) findViewById(R.id.new_btn);
        new_content.setOnClickListener(new ButtonNewContent());

        save_btn = (Button) findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new ButtonSave_Btn());



    }
        // Nouvelle page
        class ButtonNewContent implements View.OnClickListener{

            @Override
            public void onClick(View v){
                gamePrint = (gamePRINT) findViewById(R.id.drawing);
                gamePrint.reset();
            }
        }


        // TODO: A finir ! !! TAOOOOOOOOOOOOOOO C'EST ICI POUR LE SERVEUR !!!!!!!!!!!!
        class ButtonSave_Btn implements View.OnClickListener{

            @Override
            public void onClick(View v){

            }
        }

    }

