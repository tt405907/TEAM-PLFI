package plfi.plfi;

import android.content.Intent;
import android.graphics.Color;
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

    TextView messageRegle;

    TextView triangle;
    TextView carre;
    TextView rond;
    TextView victoires;
    TextView defaites;
    TextView egalites;
    TextView total;
    // temporaire pour tester notre truck
    int imageId;

    // Image à afficher pour le serveur
    ImageView imageViewServeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Test de connexion avec le serveur
        controleur = new Controleur(gameRTCPrint.this);
        Connexion connexion = new Connexion(getString(R.string.ipConnexion), getString(R.string.portConnexion), controleur);

        connexion.seConnecter();
        this.connexion = connexion;


        setContentView(R.layout.activity_game_print);

        messageServeur = (TextView) findViewById(R.id.textServeur);
        messageRegle =(TextView) findViewById(R.id.textRegle);

        triangle = (TextView) findViewById(R.id.triangleView) ;
        carre = (TextView) findViewById(R.id.carreView) ;
        rond = (TextView) findViewById(R.id.rondView) ;
        victoires = (TextView) findViewById(R.id.victoireView) ;
        defaites = (TextView) findViewById(R.id.defaiteView) ;
        egalites = (TextView) findViewById(R.id.egaliteView) ;
        total = (TextView) findViewById(R.id.totalView) ;

        Toolbar toolbar = findViewById(R.id.toolbar_rtc_print);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);

        // image du serveur
        imageViewServeur = (ImageView) findViewById(R.id.imageServeur);

        // Clear notre Canvas
        new_content = (Button) findViewById(R.id.new_btn);
        new_content.setOnClickListener(new ButtonNewContent());


        // Envoi au serveur
        save_btn = (Button) findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new ButtonSave_Btn());

        gamePrint = (gamePRINT) findViewById(R.id.drawing);







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
    public void updateStats(final String tr,final String car,final String ro,final String vic,final String def,final String eg,final String tot){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                triangle.setVisibility(View.VISIBLE);
                carre.setVisibility(View.VISIBLE);
                rond.setVisibility(View.VISIBLE);
                victoires.setVisibility(View.VISIBLE);
                defaites.setVisibility(View.VISIBLE);
                egalites.setVisibility(View.VISIBLE);
                total.setVisibility(View.VISIBLE);

                triangle.setText("Total des triangles : "+tr);
                carre.setText("Total des carre : "+car);
                rond.setText("Total des rond : "+ro);
                victoires.setText("Total des victoires : "+vic);
                defaites.setText("Total des defaites : "+def);
                egalites.setText("Total des égalités : "+eg);
                total.setText("Total : "+tot);

            }
        });
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


                if(winner.equals("client gagne")){

                    messageServeur.setBackgroundColor(Color.GREEN);
                }
                else if(winner.equals("egalite")){

                    messageServeur.setBackgroundColor(Color.YELLOW);
                }
                else{
                    messageServeur.setBackgroundColor(Color.RED);
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
            controleur.getStats();
        }
    }

}
