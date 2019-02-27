package plfi.plfi;

import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Mes Boutons
    Button ButtonStartRTC;
    Button ButtonStartPRINT;

    // Ma Toolbar de genie
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.accueil);

        // Button Game RTC
        ButtonStartRTC = (Button)findViewById(R.id.buttonStartRTC);
        ButtonStartRTC.setOnClickListener(new ButtonGameRTC());

        // Button Game PRINT
        ButtonStartPRINT = (Button) findViewById(R.id.buttonStartPrint);
        ButtonStartPRINT.setOnClickListener(new ButtonGamePRINT());

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

    // Action de ButtonGamePRINT
    class ButtonGamePRINT implements View.OnClickListener{
        @Override
        public void onClick(View v ){
           Intent intent = new Intent ( MainActivity.this, gameRTCPrint.class);
           startActivity(intent);

        }
    }

    // TODO: MENU à FAIRE pour + de SWAG
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    }

