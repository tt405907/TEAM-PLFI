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

public class gameRTC extends AppCompatActivity implements DisplayRTC {
    // Mes boutons
    Button buttonCarre;
    Button buttonTriangle;
    Button buttonCercle;

    Button buttonValider;

    Button buttonValid;

    Connexion connexion;
    Controleur controleur;
    int imageId;
    // Mes Images à afficher pour le serveur et le client
    ImageView imageInitClient; // Image de depart du cleint
    ImageView imageInitServeur; // Image de depart a voir si on l'a met pas apres le resultat pour temp, a voir
                                // avec le groupe
    ImageView imageViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rtc);

        Toolbar toolbar = findViewById(R.id.toolbar_rtc);
        setSupportActionBar(toolbar);
        getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        // Image Client
        imageViewClient = (ImageView) findViewById(R.id.imageViewClient);
        imageInitServeur = (ImageView) findViewById(R.id.imageViewServeur);

        // Carre
        buttonCarre = (Button) findViewById(R.id.buttonCarre);
        buttonCarre.setOnClickListener(new ButtonCarreClick());

        // Triangle
        buttonTriangle = (Button) findViewById(R.id.buttonTriangle);
        buttonTriangle.setOnClickListener(new ButtonTriangleClick());

        // Cercle
        buttonCercle = (Button) findViewById(R.id.buttonCercle);
        buttonCercle.setOnClickListener(new ButtonCercleClick());

        // Valider
        buttonValider = (Button) findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(new ButtonValiderClick());

        // valide au serveur
        buttonValid = (Button) findViewById(R.id.buttonValider);
        buttonValid.setOnClickListener(new ButtonValid());

        controleur = new Controleur(gameRTC.this);
        Connexion connexion = new Connexion("192.168.0.101", "10101", controleur);
        connexion.seConnecter();
        this.connexion = connexion;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rtc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
            case R.id.menu_rtc_online:
                //TODO: TAO ICI
                return true;
            case R.id.menu_rtc_offline:
                //TODO : TAO ICI
                return true;
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
                    imageInitServeur.setImageResource(R.drawable.triangle);
                } else if (img.equals("CARRE")) {
                    imageInitServeur.setImageResource(R.drawable.carre);
                } else if (img.equals("ROND")) {
                    imageInitServeur.setImageResource(R.drawable.cercle);
                } else {
                    imageInitServeur.setImageResource(R.drawable.carre);
                }
            }
        });
    }

    // TODO: REFAIRE IMAGE CARRE
    class ButtonCarreClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            imageViewClient.setImageResource(R.drawable.carre);
            imageId = 3;
        }
    }

    // TODO: REFAIRE L'IMAGE TRIANGLE
    class ButtonTriangleClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imageViewClient.setImageResource(R.drawable.triangle);
            imageId = 2;
        }
    }

    // TODO: REFAIRE L'IMAGE CERCLE
    class ButtonCercleClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            imageViewClient.setImageResource(R.drawable.cercle);

        }
    }

    class ButtonValiderClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO: METTRE L'APPELLE AU SERVEUR ET

            imageId = 4;
        }
    }

    class ButtonValid implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (imageId == 2) {
                connexion.sendForme(Forme.TRIANGLE.ordinal());
            } else if (imageId == 3) {
                connexion.sendForme(Forme.CARRE.ordinal());
            } else if (imageId == 4) {
                connexion.sendForme(Forme.ROND.ordinal());
            } else {
                connexion.sendForme(Forme.TRIANGLE.ordinal());
            } // JSONOBJECT de la response a changer pour repponse

        }
    }
}
