package plfi.plfi;

import android.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    // Mes Boutons
    Button ButtonStartRTC;

    // Ma Toolbar de genie
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.accueil);

        // Button Game RTC
        ButtonStartRTC = (Button)findViewById(R.id.buttonStartRTC);
        ButtonStartRTC.setOnClickListener(new ButtonGameRTC());

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarmain);
        setSupportActionBar(toolbar);


    }

    // Action de ButtonGameRTC
    class ButtonGameRTC implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, gameRTC.class);  //Lancer l'activité DisplayVue
            startActivity(intent);    //Afficher la vue
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }



    }

