package plfi.plfi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import commun.Forme;

public class gameRTCPrint extends AppCompatActivity implements DisplayRTC {

    // Mes boutons
    Button new_content;
    Button save_btn;

    //EditText
    TextView messageServeur;
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

        messageServeur = (TextView) findViewById(R.id.textServeur);

        Toolbar toolbar = findViewById(R.id.toolbar_rtc_print);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);

        // image du serveur
        imageViewServeur = (ImageView) findViewById(R.id.imageServeur);

        // Clear notre Canvas
        new_content = (Button) findViewById(R.id.new_btn);
        new_content.setOnClickListener(new ButtonNewContent());


        // Envoit au serveur
        save_btn = (Button) findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new ButtonSave_Btn());

        gamePrint = (gamePRINT) findViewById(R.id.drawing);

        controleur = new Controleur(gameRTCPrint.this);
        Connexion connexion = new Connexion(getString(R.string.ipConnexion), getString(R.string.portConnexion), controleur);
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
                break;
            case R.id.menu_rtc_offline:
                Intent intent = new Intent(gameRTCPrint.this, Training.class);  //Lancer l'activité DisplayVue
                startActivity(intent);    //Afficher la vue
                break;
            case R.id.menu_rtc_online:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();
        Intent intent = new Intent (gameRTCPrint.this,MainActivity.class);
        System.out.println("CHECK LA");
        startActivity(intent);
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

    // bouton VALIDER) gros copie colle )
    class ButtonSave_Btn implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //System.out.println("points " + gamePrint.getPoints());
            connexion.sendForme(gamePrint.getPoints(),"playctr");
        }
    }

}
