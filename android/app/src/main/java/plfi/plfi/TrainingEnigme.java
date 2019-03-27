package plfi.plfi;

import android.content.Intent;
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

import commun.Dessin;
import commun.Point;


public class TrainingEnigme extends AppCompatActivity {

    // Button
    Button btn_clear;
    Button btn_send;

    //EditText
    TextView message_Serveur_Reponse;
    TextView message_Serveur_Enigme;
    // Notre canvas
    gamePRINT gamePrint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_enigme);

        gamePrint = (gamePRINT) findViewById(R.id.enigme_drawing);

        message_Serveur_Reponse = (TextView) findViewById(R.id.textview_reponse);
        message_Serveur_Enigme = (TextView) findViewById(R.id.textview_enigme);

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


        }
    }

}
