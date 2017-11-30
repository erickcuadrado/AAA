package com.example.cuadrado.frontend;

import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.roughike.bottombar.BottomBar;

/**
 * Created by cuadrado on 06/06/2017.
 */

public class Main2Activity extends AppCompatActivity {
    TextView tv1;
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private TextView textView;
    private BottomBar bottomBar;
    BottomNavigationView bottomNavigationView;
    private static final String LOGTAG = "android-fcm";
     TextView notificacion_text;

    private Button btnToken;
    private Button btnVer;
    private static final String TAG = Main2Activity.class.getSimpleName();
    private PushNotificationsFragment mPushNotificationsFragment;
    private PushNotificationsPresenter mPushNotificationsPresenter;


    private PushNotificationsFragment mNotificationsFragment;
    private PushNotificationsPresenter mNotificationsPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv1=(TextView)findViewById(R.id.Bienvenido);
        Bundle bundle=getIntent().getExtras();
        //tv1.setText("Hola " + bundle.getString("Hola")+"   ");


       // Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
       // FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        //ListView listView =(ListView)findViewById(R.id.list_view);
        //DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        appbar = (Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Aqui mandamos a pedir el menu
        menuizquierdo();


       // textView = (TextView)findViewById(R.id.textView);

      //  bottomBar = (BottomBar) findViewById(R.id.bottomBar);
     /*  bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_calls) {
                    // The tab with id R.id.tab_calls was selected,
                    // change your content accordingly.
                    textView.setText("Your Calls");
                } else if (tabId == R.id.tab_groups) {
                    // The tab with id R.id.tab_groups was selected,
                    // change your content accordingly.
                    textView.setText("Your Groups");
                } else if (tabId == R.id.tab_chats) {
                    // The tab with id R.id.tab_chats was selected,
                    // change your content accordingly.
                    textView.setText("Your Chats");
                }
            }
        });*/
         bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                finish();
                                selectedFragment = ItemOneFragment.newInstance();
                                ProtocoloActivity Protocolo = new ProtocoloActivity();
                                Intent intent = new Intent(Main2Activity.this, ProtocoloActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.action_item2:
                                finish();
                                selectedFragment = ItemTwoFragment.newInstance();
                                PT1Activity PT1 = new PT1Activity();
                                Intent intentPT1 = new Intent(Main2Activity.this, PT1Activity.class);
                                startActivity(intentPT1);

                                break;
                            case R.id.action_item3:
                                finish();
                                selectedFragment = ItemThreeFragment.newInstance();
                                PT1Activity PT2 = new PT1Activity();
                                Intent intentPT2 = new Intent(Main2Activity.this, PT2Activity.class);
                                startActivity(intentPT2);
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.action_item1, ItemOneFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
        btnToken = (Button)findViewById(R.id.btnToken);
        btnToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se obtiene el token actualizado
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                Log.d(LOGTAG, "Token actualizado: " + refreshedToken);
            }
        });
        if (getIntent().getExtras() != null) {
            notificacion_text= (TextView)findViewById(R.id.texto_notification);
            notificacion_text.setText("Usuario: " + getIntent().getExtras().getString("usuario"));

            Log.d(LOGTAG, "DATOS RECIBIDOS (INTENT)");
            Log.d(LOGTAG, "Usuario: " + getIntent().getExtras().getString("usuario"));
            Log.d(LOGTAG, "Estado: " + getIntent().getExtras().getString("estado"));
        }
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //setTitle(getString(R.string.title_activity_notifications));
        /*mNotificationsFragment =
                (PushNotificationsFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.home);
        if (mNotificationsFragment == null) {
            mNotificationsFragment = PushNotificationsFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.home, mNotificationsFragment)
                    .commit();
        }

        mNotificationsPresenter = new PushNotificationsPresenter(
                mNotificationsFragment, FirebaseMessaging.getInstance());
                */

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.icons_container), iconFont);





    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }
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
                Intent intent = new Intent(Main2Activity.this, ProtocoloActivity.class);
                startActivity(intent);
                break;
            case "op2":
                setContentView(R.layout.activity_pt1);
                PT1Activity PT1 = new PT1Activity();
                Intent intentPT1 = new Intent(Main2Activity.this, PT1Activity.class);
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
                Intent intent6 = new Intent(Main2Activity.this, NotificacionesActivity.class);
                startActivity(intent6);
                break;
            case "op7":
                Intent intent7 = new Intent(Main2Activity.this, UserActivity.class);
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

    public void pantalla(View view){
        Intent intentsiguiente = new Intent(Main2Activity.this, RevisionActivity.class);
        startActivity(intentsiguiente);
        /*btnVer = (Button)findViewById(R.id.btnver1);
        btnVer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentsiguiente = new Intent(Main2Activity.this, RevisionActivity.class);
                startActivity(intentsiguiente);
            }
        });*/
    }


}