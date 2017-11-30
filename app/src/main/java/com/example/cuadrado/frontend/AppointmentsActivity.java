package com.example.cuadrado.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.cuadrado.frontend.api.a.prefs.SessionPrefs;
import com.example.cuadrado.frontend.api.a.model.LoginBody;

/**
 * Created by cuadrado on 08/11/2017.
 */

public class AppointmentsActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Toolbar appbar;
    EditText et1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Redirección al Login
        if (!SessionPrefs.get(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        Bundle bundle=getIntent().getExtras();
        et1=(EditText)findViewById(R.id.usuario);

       // String usuario = bundle.getString("Hola");
        setContentView(R.layout.activity_main2);
        Main2Activity Home = new Main2Activity();
        Intent intentHome = new Intent(AppointmentsActivity.this, Main2Activity.class);
        //intentHome.putExtra("Hola", et1.getText().toString());
        startActivity(intentHome);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Aqui mandamos a pedir el menu
        menuizquierdo();*/

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabMiFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void menuizquierdo(){
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navView = (NavigationView)findViewById(R.id.navview);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                fragment = new Fragment1();
                                fragmentTransaction = true;
                                election("op1");
                                break;
                            case R.id.menu_seccion_2:
                                fragment = new Fragment2();
                                fragmentTransaction = true;
                                election("op2");
                                break;
                            case R.id.menu_seccion_3:
                                // fragment = new Fragment3();
                                // fragmentTransaction = true;
                                election("op3");
                                break;
                            case R.id.menu_opcion_1:
                                setContentView(R.layout.calendar_viewer);
                                WebView webView = (WebView)findViewById(R.id.webview);
                                webView.setWebViewClient(new WebViewClient());
                                webView.getSettings().setJavaScriptEnabled(true);
                                webView.loadUrl("https://www.upiita.ipn.mx/proyectos-terminales ");


                                //Log.i("NavigationView", "Pulsada opción 1");
                                break;
                            case R.id.menu_opcion_2:
                                Log.i("NavigationView", "Pulsada opción 2");
                                election("op5");
                                break;
                            case R.id.notificacion:
                                election("op6");
                                break;
                            case R.id.configuracion:
                                election("op7");
                                break;

                        }

                        if(fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.frame_layout, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });
    }

    private void election(String opcion){
        switch (opcion){
            case "op1":
                ProtocoloActivity Protocolo = new ProtocoloActivity();
                Intent intent = new Intent(AppointmentsActivity.this, ProtocoloActivity.class);
                startActivity(intent);
                break;
            case "op2":
                setContentView(R.layout.activity_pt1);
                PT1Activity PT1 = new PT1Activity();
                Intent intentPT1 = new Intent(AppointmentsActivity.this, PT1Activity.class);
                startActivity(intentPT1);
                break;
            case "op3":
                setContentView(R.layout.activity_pt2);
                appbar = (Toolbar)findViewById(R.id.appbar);
                setSupportActionBar(appbar);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
            case "op5":
                setContentView(R.layout.activity_calendar);
                appbar = (Toolbar)findViewById(R.id.appbar);
                setSupportActionBar(appbar);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
            case "op6":
                Intent intent6 = new Intent(AppointmentsActivity.this, NotificacionesActivity.class);
                startActivity(intent6);
                break;
            case "op7":
                Intent intent7 = new Intent(AppointmentsActivity.this, UserActivity.class);
                startActivity(intent7);
                break;
        }
    }
}
