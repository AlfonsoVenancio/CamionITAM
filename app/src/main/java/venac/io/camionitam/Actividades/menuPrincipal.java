package venac.io.camionitam.Actividades;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import venac.io.camionitam.Fragmentos.*;
import venac.io.camionitam.R;

public class menuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean transaccionSeleccionada = false;
        Fragment fragmentoSeleccionado = null;
        if (id == R.id.nav_horariosMetro) {
            fragmentoSeleccionado = new horarioMetro();
            transaccionSeleccionada = true;
        } else if (id == R.id.nav_quevedo) {
            fragmentoSeleccionado = new metroQuevedo();
            transaccionSeleccionada = true;
        } else if (id == R.id.nav_muerto) {
            fragmentoSeleccionado = new metroBarranca();
            transaccionSeleccionada = true;
        } else if (id == R.id.nav_horariosTeresa) {
            fragmentoSeleccionado = new horarioTeresa();
            transaccionSeleccionada = true;
        } else if (id == R.id.nav_teresa) {
            fragmentoSeleccionado = new campusTeresa();
            transaccionSeleccionada = true;
        } else if (id == R.id.nav_notificaciones){
            fragmentoSeleccionado = new creacionNotificacion();
            transaccionSeleccionada = true;
        }
        if(transaccionSeleccionada){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment,fragmentoSeleccionado).commit();
            getSupportActionBar().setTitle(item.getTitle());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
