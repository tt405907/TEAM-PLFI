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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import commun.Dessin;
import commun.Forme;
import commun.FormeMatcher;
import commun.Point;
import commun.jeux.GameEnigme;

public class TrainingEnigme extends AppCompatActivity {

    // Button
    Button btn_clear;
    Button btn_send;
    GameEnigme gameEnigme;
    //EditText
    TextView message_Serveur_Reponse;
    TextView message_Serveur_Enigme;
    TextView messageTraining;

    FormeMatcher formeMatcher;
    // Notre canvas
    gamePRINT gamePrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_enigme_training);

        gamePrint = (gamePRINT) findViewById(R.id.enigme_drawing);

        message_Serveur_Reponse = (TextView) findViewById(R.id.textview_reponse);
        message_Serveur_Enigme = (TextView) findViewById(R.id.textview_enigme);
        messageTraining = (TextView) findViewById(R.id.training);

        //btn clear
        btn_clear = (Button) findViewById(R.id.enigme_new_btn);
        btn_clear.setOnClickListener(new Button_clear());

        //btn_send
        btn_send = (Button) findViewById(R.id.training_check);
        btn_send.setOnClickListener(new Button_send());

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_enigme);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);

        // Drawing
        gamePrint = (gamePRINT) findViewById(R.id.enigme_drawing);
        gameEnigme = new GameEnigme(new Random());

        setEnigme();

        formeMatcher = new FormeMatcher();

    }

    public void setEnigme(){
        String enigme = gameEnigme.makeEnigme();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                message_Serveur_Enigme.setText(enigme);
            }
        });
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
            case R.id.menu_rtc_online:
                Intent intent = new Intent(TrainingEnigme.this, gameEnigme.class);  //Lancer l'activité DisplayVue
                startActivity(intent);    //Afficher la vue
                break;
            case R.id.menu_rtc_offline:
                break;

        }
        return super.onOptionsItemSelected(item);
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
            List<Point> points = gamePrint.getPoints();
            Dessin dessin = Dessin.fromList(points);
            Forme forme = formeMatcher.identify(dessin);
            boolean b = gameEnigme.estFormeAttendue(forme);
            String out;
            if (b){
                out = " JUSTE";
                message_Serveur_Reponse.setBackgroundColor(Color.GREEN);
            }
            else {
                out = "  FAUX";
                message_Serveur_Reponse.setBackgroundColor(Color.RED);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    message_Serveur_Reponse.setText(out);
                    if(b)
                    {
                        setEnigme();
                    }
                    gamePrint.reset();

                }
            });


        }
    }

}
