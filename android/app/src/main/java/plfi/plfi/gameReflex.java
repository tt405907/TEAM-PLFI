package plfi.plfi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class gameReflex extends AppCompatActivity implements DisplayReflex {

    private int scoreVal;
    private TextView score;
    private TextView scoreMax;
    private TextView timer;
    private TextView forme;
    public Button btn_start;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private gamePrintReflex gamePrint;
    private Connexion connexion;
    private Controleur controleur;
    private Handler handler = new Handler();
    private double time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_reflex);

        // Nos initialisations
        scoreVal = 0;
        score = (TextView) findViewById(R.id.textview_score_reflex);
        scoreMax = (TextView) findViewById(R.id.textview_score_max_reflex);
        btn_start = (Button) findViewById(R.id.start_reflex);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_reflex);
        gamePrint = (gamePrintReflex) findViewById(R.id.reflex_drawing);
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
        gamePrint.setConnexion(connexion);
        time = 5.0;
        controleur.getStats();

    }

    @Override
    public void updateGameReflex(final String newforme) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                forme.setText(newforme);
                start();
            }
        });
    }

    @Override
    public void updateGameStats (String toutes_mauvaises_reponses,String score_max,String parties,String toutes_bonnes_réponses) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreMax.setText(score_max);
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
                    scoreVal++;
                    score.setText(Integer.toString(scoreVal));
                }
                else{
                    timer.setBackgroundColor(Color.RED);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        Intent intent = new Intent (gameReflex.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    class button_start implements View.OnClickListener {
        @Override
        public void onClick(View v) {
                controleur.apresConnexionReflex();
                btn_start.setEnabled(false);
        }
    }
    private void start (){
        gamePrint.reset();
        time=5.0;
        new Thread(new Runnable() {
            public void run() {

                gamePrint.setIsStarted(true);
                while (time > 0.001) {
                    progressStatus += 1;
                    time-=0.05;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            timer.setText(String.format("%.2f", (float)time));
                        }
                    });

                    try {
                        // 50 milli = 0.05 s à voir si on le met aléatoire ou pas
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                gamePrint.setIsStarted(false);
                runOnUiThread(() -> btn_start.setEnabled(true));
                controleur.getStats();
            }
        }).start();
        progressStatus = 0;
        score.setText("0");
        scoreVal = 0;
        timer.setText("5");
        gamePrint.setIsStarted(false);
    }
}