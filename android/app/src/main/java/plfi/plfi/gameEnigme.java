package plfi.plfi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_enigme);

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
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
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
    public void updateGameReponseEnigme(final String reponse) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                message_Serveur_Reponse.setText(reponse);
                if(reponse.contains("JUSTE"))
                {
                    controleur.apresConnexionEnigme();
                }

            }
        });
    }


    // Button clear
    class Button_clear implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            gamePrint = (gamePRINT) findViewById(R.id.enigme_drawing);
            gamePrint.reset();
        }
    }

    // Button send
    class Button_send implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            connexion.sendForme(gamePrint.getPoints(),"reponseenigme");
        }
    }

}
