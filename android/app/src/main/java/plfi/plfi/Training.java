package plfi.plfi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import commun.Dessin;
import commun.Forme;
import commun.FormeMatcher;
import commun.Point;
import commun.jeux.GameCTR;
import commun.jeux.ResultCTR;

public class Training extends AppCompatActivity implements DisplayRTC {

    public Connexion connexion;
    public Controleur controleur;
    // Mes boutons
    Button new_content;
    Button save_btn;

    FormeMatcher formeMatcher;
    //EditText
    TextView messageServeur;

    // Notre canvas
    gamePRINT gamePrint;
    GameCTR gameCTR;

    // temporaire pour tester notre truck
    int imageId;

    // Image à afficher pour le serveur
    ImageView imageViewServeur;

    TextView messageRegle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        messageServeur = (TextView) findViewById(R.id.textServeur);
        messageRegle = (TextView) findViewById(R.id.textRegle);

        Toolbar toolbar = findViewById(R.id.my_toolbar_training_rtc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);

        // image du serveur
        imageViewServeur = (ImageView) findViewById(R.id.imageServeur);

        // Clear notre Canvas
        new_content = (Button) findViewById(R.id.new_btn);
        new_content.setOnClickListener(new Training.ButtonNewContent());

        // Envoi au serveur
        save_btn = (Button) findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new Training.ButtonSave_Btn());

        gamePrint = (gamePRINT) findViewById(R.id.drawing);

        gameCTR = new GameCTR();
        formeMatcher = new FormeMatcher();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_training, menu);
        return true;
    }


    private Forme randomForme() {
        int num = new Random().nextInt(3);
        switch (num) {
            case 0:
                return Forme.CARRE;
            case 1:
                return Forme.TRIANGLE;
            case 2:
            default:
                return Forme.ROND;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

            case R.id.menu_rtc_offline:
                break;
            case R.id.menu_rtc_online:
                Intent intent = new Intent(Training.this, gameRTCPrint.class);  //Lancer l'activité DisplayVue
                startActivity(intent);    //Afficher la vue

        }
        return super.onOptionsItemSelected(item);
    }


    //Carré bat Rond
    //Triangle bat Carré
    //Rond bat Triangle

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateStats(final String triangle, final String carre, final String rond, final String victoires, final String defaites, final String egalites, final String total) {

    }

    @Override
    public void updateGame(final String winner, final String img) {
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
                if (winner.equals("client gagne")) {

                    messageServeur.setBackgroundColor(Color.GREEN);
                } else if (winner.equals("egalite")) {

                    messageServeur.setBackgroundColor(Color.YELLOW);
                } else {
                    messageServeur.setBackgroundColor(Color.RED);
                }
                if (winner.equals(ResultCTR.SERVEUR_GAGNE))
                    messageServeur.setText("programme gagne");
                else
                    messageServeur.setText(winner);


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


    class ButtonSave_Btn implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            List<Point> points = gamePrint.getPoints();
            Dessin dessin = Dessin.fromList(points);
            Forme forme = formeMatcher.identify(dessin);

            ResultCTR resultCTR = gameCTR.play(forme, randomForme());
            updateGame(resultCTR.getResult(), resultCTR.getFormeServeur().toString());
            gamePrint.reset();

        }
    }
}

