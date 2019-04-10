package plfi.plfi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gameEnigme extends AppCompatActivity implements DisplayEnigme {

    // Button
    Button btn_clear;
    Button btn_send;

    //EditText
    TextView message_Serveur_Reponse;
    TextView message_Serveur_Enigme;
    // Notre canvas
    gamePRINT gamePrint;
    Connexion connexion;
    Controleur controleur;

    TextView statGoodAnswer;
    TextView statbadAnswer;
    TextView statTotalAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Test de connexion avec le serveur

        setContentView(R.layout.activity_game_enigme);

        gamePrint = (gamePRINT) findViewById(R.id.enigme_drawing);

        message_Serveur_Reponse = (TextView) findViewById(R.id.textview_reponse);
        message_Serveur_Enigme = (TextView) findViewById(R.id.textview_enigme);

        statbadAnswer = (TextView) findViewById(R.id.stat_bad);
        statGoodAnswer = (TextView) findViewById(R.id.stat_good);
        statTotalAnswer = (TextView) findViewById(R.id.stat_total);
        //btn clear
        btn_clear = (Button) findViewById(R.id.enigme_new_btn);
        btn_clear.setOnClickListener(new Button_clear());

        //btn_send
        btn_send = (Button) findViewById(R.id.enigme_save_btn);
        btn_send.setOnClickListener(new Button_send());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_enigme);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);

        // Drawing
        gamePrint = (gamePRINT) findViewById(R.id.enigme_drawing);

        controleur = new Controleur(gameEnigme.this);
        Connexion connexion = new Connexion(getString(R.string.ipConnexion), getString(R.string.portConnexion), controleur);
        connexion.seConnecter();
        controleur.apresConnexionEnigme();
        this.connexion = connexion;


    }



    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
        Intent intent = new Intent (this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_training, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_rtc_offline:
                Intent intent = new Intent(gameEnigme.this, TrainingEnigme.class);  //Lancer l'activité DisplayVue
                startActivity(intent);    //Afficher la vue
                break;
            case R.id.menu_rtc_online:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateGameEnigme(final String enigme) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                message_Serveur_Enigme.setText(enigme);
            }
        });
    }

    @Override
    public void updateStats(final String bonnes,final String mauvaise,final String total){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statbadAnswer.setVisibility(View.VISIBLE);
                statGoodAnswer.setVisibility(View.VISIBLE);
                statTotalAnswer.setVisibility(View.VISIBLE);
                statbadAnswer.setText("Mauvaises réponses : "+mauvaise);
                statGoodAnswer.setText("Bonnes réponses : "+bonnes);
                statTotalAnswer.setText("Total des enigmes reçus : "+total);
            }
        });
    }

    @Override
    public void updateGameReponseEnigme(final String reponse) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                message_Serveur_Reponse.setText(reponse);
                if(reponse.contains("JUSTE"))
                {
                    message_Serveur_Enigme.setBackgroundColor(Color.GREEN);

                    controleur.apresConnexionEnigme();
                }
                else{
                    message_Serveur_Enigme.setBackgroundColor(Color.RED);
                }
                gamePrint.reset();

            }
        });
    }


    // Button clear
    class Button_clear implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            gamePrint.reset();
        }
    }

    // Button send
    class Button_send implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            connexion.sendForme(gamePrint.getPoints(),"reponseenigme");
            controleur.getStats();
        }
    }

}
