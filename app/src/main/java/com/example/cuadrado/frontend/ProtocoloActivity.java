package com.example.cuadrado.frontend;

import android.*;
import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.widget.Toast;


import com.example.cuadrado.frontend.api.a.model.Post;
import com.example.cuadrado.frontend.api.a.remote.APIService;
import com.example.cuadrado.frontend.api.a.remote.ApiUtils;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.ServerResponse;
import net.gotev.uploadservice.UploadInfo;
import net.gotev.uploadservice.UploadStatusDelegate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ProtocoloActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnVer;
    Button btnSubir;
    EditText et1;
    TextView tv1;
    BottomNavigationView bottomNavigationView;
    private NavigationView navView;
    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private static int RESULT_LOAD_IMAGE = 1;
    //para subir imagenes
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    private String SERVER_URL = "http://192.168.100.4/extras/UploadToServer.php";
    ImageView ivAttachment;
    Button bUpload;
    TextView tvFileName;
    ProgressDialog dialog;
    static final int WRITE_EXTERNAL_STORAGE= 99;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 123;
    Context context;
    private APIService mAPIService;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocolo);
        context =this;
        btnVer = (Button)findViewById(R.id.btnver1);
        btnVer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentsiguiente = new Intent(ProtocoloActivity.this, RevisionActivity.class);
                startActivity(intentsiguiente);
            }
        });

                 appbar = (Toolbar)findViewById(R.id.appbar);
                setSupportActionBar(appbar);
                appbar.setTitle("Protocolo");
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
                menuizquierdo();
                btnSubir = (Button)findViewById(R.id.btnsubir1);
                btnSubir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = getApplicationContext();
                boolean result=checkPermission();
                if(result){
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                }
            }
        });




        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = ItemOneFragment.newInstance();
                                ProtocoloActivity Protocolo = new ProtocoloActivity();
                                Intent intent = new Intent(ProtocoloActivity.this, ProtocoloActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.action_item2:
                                selectedFragment = ItemTwoFragment.newInstance();
                                PT1Activity PT1 = new PT1Activity();
                                Intent intentPT1 = new Intent(ProtocoloActivity.this, PT1Activity.class);
                                startActivity(intentPT1);
                                break;
                            case R.id.action_item3:
                                selectedFragment = ItemThreeFragment.newInstance();
                                PT2Activity PT2 = new PT2Activity();
                                Intent intentPT2 = new Intent(ProtocoloActivity.this, PT2Activity.class);
                                startActivity(intentPT2);
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        ivAttachment.setOnClickListener(this);
        bUpload = (Button) findViewById(R.id.btnsubir1);
        bUpload.setOnClickListener(this);


    }
    public void pantalla(View view){
        //btnVer = (Button)findViewById(R.id.btnver1);
        Intent intentsiguiente = new Intent(ProtocoloActivity.this, RevisionActivity.class);
        startActivity(intentsiguiente);
    }
    public void menuizquierdo(){
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout2);
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
                            case R.id.inicio:
                                election("op8");
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
                Intent intent = new Intent(ProtocoloActivity.this, ProtocoloActivity.class);
                startActivity(intent);
                break;
            case "op2":
                PT1Activity PT1 = new PT1Activity();
                Intent intentPT1 = new Intent(ProtocoloActivity.this, PT1Activity.class);
                startActivity(intentPT1);
                break;
            case "op3":
                PT2Activity PT2 = new PT2Activity();
                Intent intentPT2 = new Intent(ProtocoloActivity.this, PT2Activity.class);
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
                Intent intent6 = new Intent(ProtocoloActivity.this, NotificacionesActivity.class);
                startActivity(intent6);
                break;
            case "op7":
                Intent intent7 = new Intent(ProtocoloActivity.this, UserActivity.class);
                startActivity(intent7);
                break;
            case "op8":
                Intent intent8 = new Intent(ProtocoloActivity.this, Main2Activity.class);
                startActivity(intent8);
                break;

        }
    }
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imageHome);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }*/


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
    /*public void uploadFile(String filename){
        try {
            FileInputStream fis = new FileInputStream(filename);
            HttpFileUploader htfu = new HttpFileUploader("http://www.tuservidor.com/upload.php","noparamshere", filename);
            htfu.doStart(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    public void onClick(View v) {
        if(v== ivAttachment){

            //on attachment icon click
            showFileChooser();
        }
        if(v== bUpload){

            //on upload button Click
            if(selectedFilePath != null){
                dialog = ProgressDialog.show(ProtocoloActivity.this,"","Uploading File...",true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //creating new thread to handle Http Operations
                        uploadFile(selectedFilePath);
                        String id ="id";
                        String urli="url";
                        mAPIService = ApiUtils.getAPIService();
                        if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(urli)){
                            sendPost(id, urli);
                        }
                    }
                }).start();
            }else{
                Toast.makeText(ProtocoloActivity.this,"Please choose a File First",Toast.LENGTH_SHORT).show();
            }

        }
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),PICK_FILE_REQUEST);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean result = checkPermission();
        if (result) {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == PICK_FILE_REQUEST) {
                    if (data == null) {
                        //no data present
                        return;
                    }

                    tvFileName = (TextView) (findViewById(R.id.Entrega1_protocolo));

                    Uri selectedFileUri = data.getData();
                    selectedFilePath = FilePath.getPath(this, selectedFileUri);
                    Log.i(TAG, "Selected File Path:" + selectedFilePath);

                    if (selectedFilePath != null && !selectedFilePath.equals("")) {
                        tvFileName.setText(selectedFilePath);
                    } else {
                        Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    //android upload file to server
    public int uploadFile(final String selectedFilePath){

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead,bytesAvailable,bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length-1];

        if (!selectedFile.isFile()){
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvFileName.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        }else{
            try{

                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file",selectedFilePath);
                connection.setRequestProperty("directory", "directorio");





                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer,0,bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0){
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer,0,bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);


                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if(serverResponseCode == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvFileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "http://coderefer.com/extras/uploads/"+ fileName);
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ProtocoloActivity.this,"File Not Found",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(ProtocoloActivity.this, "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(ProtocoloActivity.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    /*public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }*/
    public boolean checkPermission()
    {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Write calendar permission is necessary to write event!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    Toast.makeText(this, "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }
    public void sendPost(String id, String urli) {
        mAPIService.savePost(id, urli, "1", "2","3", "4", "5").enqueue(new Callback<Post>() {
            @Override
            public void onResponse(retrofit2.Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Post> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");

            }


            /*@Override
            public void onResponse(Call<Post> call, Response<Post> response) {


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            }*/
        });
        // RxJava
        /*mAPIService.savePost(id, urli, "nombre", "la", "nomb", "d", "urlss").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {
                        showResponse(post.toString());
                    }
                });*/
    }

    public void showResponse(String response) {
        if(tv1.getVisibility() == View.GONE) {
            tv1.setVisibility(View.VISIBLE);
        }
        tv1.setText(response);
    }



}
