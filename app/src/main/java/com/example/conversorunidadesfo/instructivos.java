package com.example.conversorunidadesfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class instructivos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructivos);

        ActionBar barra_accion = getSupportActionBar(); //muestro flecha y retorna a activity padre
        barra_accion.setDisplayHomeAsUpEnabled(true);
    }

    @Override //muestra la action bar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_barra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override  //Los distintos case son para los botones que tiene la action bar, le da una accion a cada boton
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent home = new Intent(this, inicio.class);
                startActivity(home);
                return true;

            case R.id.productos:
                Intent productos = new Intent(this, visualizadorWeb.class);
                startActivity(productos);
                return true;
            case R.id.contactos:
                Intent contactos = new Intent(this, contactos.class); //falta crear la activity de CONTACTOS
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

    // voy a otra clase donde veo el archivo pdf de kl280 y lo puedo descargar desde action bar
    public void kl280e (View view){
        Intent intent = new Intent(this, pdf_kl280e.class);
        startActivity(intent);
    }

    // voy a otra activity donde visualizo el pdf de ATs80App desde una URL
    public void ats80a (View view){
        Intent intent = new Intent(this, pdf_ats80.class);
        startActivity(intent);
    }

    // voy a otra activity donde visualizo el pdf de ATo750App desde una URL
    public void ato750 (View view){
        Intent intent = new Intent(this, ato_750.class);
        startActivity(intent);
    }

    // voy a otra activity donde visualizo el pdf de ATO350App desde una URL
    public void ato350 (View view){
        Intent intent = new Intent(this, ato_350.class);
        startActivity(intent);
    }
}