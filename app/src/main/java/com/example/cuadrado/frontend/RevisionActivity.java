package com.example.cuadrado.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by cuadrado on 22/10/2017.
 */

public class RevisionActivity extends AppCompatActivity{
    Button btnVer;
    EditText et1;
    TextView tv1;
    BottomNavigationView bottomNavigationView;
    private NavigationView navView;
    private Toolbar appbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision);
        appbar = (Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(appbar);
        appbar.setTitle("Protocolo");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        menuizquierdo();
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = ItemOneFragment.newInstance();
                                ProtocoloActivity Protocolo = new ProtocoloActivity();
                                Intent intent = new Intent(RevisionActivity.this, ProtocoloActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.action_item2:
                                selectedFragment = ItemTwoFragment.newInstance();
                                PT1Activity PT1 = new PT1Activity();
                                Intent intentPT1 = new Intent(RevisionActivity.this, PT1Activity.class);
                                startActivity(intentPT1);
                                break;
                            case R.id.action_item3:
                                selectedFragment = ItemThreeFragment.newInstance();
                                PT2Activity PT2 = new PT2Activity();
                                Intent intentPT2 = new Intent(RevisionActivity.this, PT2Activity.class);
                                startActivity(intentPT2);
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

    }
    public void menuizquierdo(){
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout6);
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
                Intent intent = new Intent(RevisionActivity.this, ProtocoloActivity.class);
                startActivity(intent);
                break;
            case "op2":
                PT1Activity PT1 = new PT1Activity();
                Intent intentPT1 = new Intent(RevisionActivity.this, PT1Activity.class);
                startActivity(intentPT1);
                break;
            case "op3":
                PT2Activity PT2 = new PT2Activity();
                Intent intentPT2 = new Intent(RevisionActivity.this, PT2Activity.class);
                startActivity(intentPT2);
                break;
            case "op5":
                setContentView(R.layout.activity_calendar);
                appbar = (Toolbar)findViewById(R.id.appbar);
                setSupportActionBar(appbar);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
            case "op6":
                Intent intent6 = new Intent(RevisionActivity.this, NotificacionesActivity.class);
                startActivity(intent6);
                break;
            case "op7":
                Intent intent7 = new Intent(RevisionActivity.this, UserActivity.class);
                startActivity(intent7);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // COMPLETED (9) Within onCreateOptionsMenu, use getMenuInflater().inflate to inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // COMPLETED (10) Return true to display your menu
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        /*int itemThatWasClickedId= item.getItemId();
        if(itemThatWasClickedId == R.id.action_search){
            Context context=Main2Activity.this;
            String textToShow ="SearchClicked";
            Toast.makeText(context,textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }*/
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
