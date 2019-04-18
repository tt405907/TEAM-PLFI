package plfi.plfi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button btn_togamertc = (Button) findViewById(R.id.Button_main_togame_rtc);
        btn_togamertc.setOnClickListener(new Button_togame_rtc());

        Button btn_togame_enigme = (Button) findViewById(R.id.Button_main_togame_enigme);
        btn_togame_enigme.setOnClickListener(new Button_togame_enigme());

        Button btn_togame_reflex = (Button) findViewById(R.id.Button_main_reflex);
        btn_togame_reflex.setOnClickListener(new Button_togame_reflex());

    }

    // Action button content main to enigme
    class Button_togame_enigme implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent intent = new Intent( MainActivity.this , gameEnigme.class);
            startActivity(intent);
        }
    }

    // Action button content main to reflex
    class Button_togame_reflex implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, gameReflex.class); // Lancer l'activité DisplayVue
            startActivity(intent); // Afficher la vue
        }
    }

    // Action button content main to rtc
    class Button_togame_rtc implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, gameRTCPrint.class); // Lancer l'activité DisplayVue
            startActivity(intent); // Afficher la vue

        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_view) {

        } else if (id == R.id.bouton1_main_menu) {
            Intent intent = new Intent(MainActivity.this, gameRTCPrint.class); // Lancer l'activité DisplayVue
            startActivity(intent); // Afficher la vue

        } else if (id == R.id.bouton2_main_menu) {
            Intent intent = new Intent(MainActivity.this, gameEnigme.class); // Lancer l'activité DisplayVue
            startActivity(intent); // Afficher la vue

        } else if (id == R.id.bouton3_main_menu) {
            Intent intent = new Intent(MainActivity.this, gameReflex.class);
            startActivity(intent);

        } else if (id == R.id.bouton4_main_menu) {
            // iteration 4
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
