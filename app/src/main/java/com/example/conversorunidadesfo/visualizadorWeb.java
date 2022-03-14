package com.example.conversorunidadesfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class visualizadorWeb extends AppCompatActivity {

    private WebView minavegador;
    FirebaseFirestore mRootWeb;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizador_web);

        // obtainRegisters miClaseRegistros = new obtainRegisters();
        //String enlace = miClaseRegistros.get_mValue("links", "webFibro", "valor");

        ActionBar barra_accion = getSupportActionBar();
        barra_accion.setDisplayHomeAsUpEnabled(true);

        mRootWeb = FirebaseFirestore.getInstance();
        mRootWeb.collection("links").document("webFibro").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String enlace = documentSnapshot.getString("valor");


                minavegador = (WebView)findViewById(R.id.navegador);

                //ESTO ES LA PRUEBA webChromeClient
                minavegador.setWebChromeClient(new WebChromeClient());
                minavegador.getSettings().setJavaScriptEnabled(true);
                minavegador.loadUrl(enlace);



                //ESTO FUNCIONA PERO NO DESCARGA

                //minavegador.getSettings().setJavaScriptEnabled(true);
                //minavegador.getSettings().setBuiltInZoomControls(true);

                //minavegador.loadUrl("https://fmweb.viterlabs.com.ar/"); //CAMBIADO PROVISORIO

                //"https://fmweb.viterlabs.com.ar/"


                minavegador.setWebViewClient(new WebViewClient(){
                    public boolean shouldOverrideUrlLoading(WebView view, String url){
                        return false;
                    }
                });



            }
        });






    }

    @Override   //En esta funcion hago que aparezca la action bar creada res/menu/menu_barra
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_barra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent home = new Intent(this, inicio.class);
                startActivity(home);
                return true;

            case R.id.productos:
                Intent productos = new Intent(this, visualizadorWeb.class);
                startActivity(productos);
                return true;
            case R.id.contactos:
                Intent contactos = new Intent(this, contactos.class); //va a la activity de CONTACTOS
                startActivity(contactos);
                return true;
            case R.id.ubicacion:
                Intent ubicacion = new Intent(this, MapsActivity.class); //va a la activity de UBICACION
                startActivity(ubicacion);
                return true;

            case R.id.descargas:
                Intent descargas = new Intent(this, instructivos.class); //va a la activity de INSTRUCTIVOS
                startActivity(descargas);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}