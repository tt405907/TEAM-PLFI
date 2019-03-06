package plfi.plfi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import commun.Forme;

public class gameRTCPrint extends AppCompatActivity implements DisplayRTC {

    // Mes boutons
    Button new_content;
    Button save_btn;
    Button buttonCarre;
    Button buttonTriangle;
    Button buttonCercle;

    // Notre canvas
    gamePRINT gamePrint;
    Connexion connexion;
    Controleur controleur;


    // temporaire pour tester notre truck
    int imageId;

    // Image à afficher pour le serveur
    ImageView imageViewServeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_print);

        Toolbar toolbar = findViewById(R.id.toolbar_rtc_print);
        setSupportActionBar(toolbar);
        getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        // image du serveur
        imageViewServeur = (ImageView) findViewById(R.id.imageServeur);

        // Clear notre Canvas
        new_content = (Button) findViewById(R.id.new_btn);
        new_content.setOnClickListener(new ButtonNewContent());

        // Carre
        buttonCarre = (Button) findViewById(R.id.buttonCarre);
        buttonCarre.setOnClickListener(new ButtonCarreClick());

        // Triangle
        buttonTriangle = (Button) findViewById(R.id.buttonTriangle);
        buttonTriangle.setOnClickListener(new ButtonTriangleClick());

        // Cercle
        buttonCercle = (Button) findViewById(R.id.buttonCercle);
        buttonCercle.setOnClickListener(new ButtonCercleClick());

        // Envoit au serveur
        save_btn = (Button) findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new ButtonSave_Btn());

        gamePrint = (gamePRINT) findViewById(R.id.drawing);

        controleur = new Controleur(gameRTCPrint.this);
        Connexion connexion = new Connexion("192.168.0.105", "10101", controleur);
        connexion.seConnecter();
        this.connexion = connexion;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_print, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

            case R.id.menu_rtc_offline:
                //TODO : A FINIR
            case R.id.menu_rtc_online:
                //TODO : A FINIR

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void updateGame(String winner, final String img) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (img.equals("TRIANGLE")) {
                    imageViewServeur.setImageResource(R.drawable.triangle);
                } else if (img.equals("CARRE")) {
                    imageViewServeur.setImageResource(R.drawable.carre);
                } else if (img.equals("ROND")) {
                    imageViewServeur.setImageResource(R.drawable.cercle);
                } else {
                    imageViewServeur.setImageResource(R.drawable.carre);
                }
            }
        });
    }

    // Nouvelle page
    class ButtonNewContent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            gamePrint = (gamePRINT) findViewById(R.id.drawing);
            gamePrint.reset();
        }
    }

    // TODO: REFAIRE IMAGE CARRE
    class ButtonCarreClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            imageId = 3;
        }
    }

    // TODO: REFAIRE L'IMAGE TRIANGLE
    class ButtonTriangleClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imageId = 2;
        }
    }

    // TODO: REFAIRE L'IMAGE CERCLE
    class ButtonCercleClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imageId = 4;

        }
    }

    // TODO: A finir ! !! TAOOOOOOOOOOOOOOO C'EST ICI POUR LE SERVEUR ! (remplace le
    // bouton VALIDER) gros copie colle )
    class ButtonSave_Btn implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //System.out.println("points " + gamePrint.getPoints());
            connexion.sendForme(gamePrint.getPoints());
        }
    }

}
