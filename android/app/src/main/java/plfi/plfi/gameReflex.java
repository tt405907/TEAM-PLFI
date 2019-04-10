package plfi.plfi;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class gameReflex extends AppCompatActivity implements DisplayReflex {

    private TextView score;
    private TextView scoreMax;
    private TextView timer;
    private TextView forme;
    private Button btn_start;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private gamePRINT gamePRINT;
    private Connexion connexion;
    private Controleur controleur;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_reflex);

        // Nos initialisations
        score = (TextView) findViewById(R.id.textview_score_reflex);
        scoreMax = (TextView) findViewById(R.id.textview_score_max_reflex);
        btn_start = (Button) findViewById(R.id.start_reflex);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_reflex);
        gamePRINT = (gamePRINT) findViewById(R.id.reflex_drawing);
        timer = (TextView) findViewById(R.id.textView_timer);
        forme = (TextView) findViewById(R.id.forme_reflex);

        //Toolbar setup
        Toolbar toolbar = findViewById(R.id.toobar_reflex);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);

        //Action
        btn_start.setOnClickListener(new button_start());


        controleur = new Controleur(gameReflex.this);
        Connexion connexion = new Connexion(getString(R.string.ipConnexion), getString(R.string.portConnexion), controleur);
        connexion.seConnecter();
        this.connexion = connexion;


    }

    @Override
    public void updateGameReflex(final String newforme) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                forme.setText(newforme);
            }
        });
    }

    @Override
    public void updateGameReponseReflex(final String reponse) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(reponse.contains("JUSTE"))
                {
                    timer.setBackgroundColor(Color.GREEN);

                    controleur.apresConnexionEnigme();
                    score.setText( Integer.parseInt((String) score.getText()) + 1 );
                }
                else{
                    timer.setBackgroundColor(Color.RED);
                }
                gamePRINT.reset();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    class button_start implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                public void run() {
                    while (progressStatus < 100) {
                        progressStatus += 1;
                        // Update the progress bar and display the
                        //current value in the text view
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressStatus);
                                double realt = Float.parseFloat( (String)timer.getText()) - 0.05;
                                double arrondi = (double)Math.round(realt * 1000) / 1000;
                                timer.setText(Double.toString(arrondi));
                            }
                        });
                        try {
                            // 50 milli = 0.05 s à voir si on le met aléatoire ou pas
                            Thread.sleep(50);
                            connexion.sendForme(gamePRINT.getPoints(),"formereflex");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            progressStatus = 0;
            timer.setText("5");
        }
    }

}
