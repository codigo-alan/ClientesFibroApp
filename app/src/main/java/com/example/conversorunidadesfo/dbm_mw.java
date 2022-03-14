package com.example.conversorunidadesfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class dbm_mw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbm_mw);

        ActionBar barra_accion = getSupportActionBar(); //Hago que aparezca la
        barra_accion.setDisplayHomeAsUpEnabled(true);   //flecha en la barra de accion

        //DECLARO los botones
        Button btn_a_mW = (Button) findViewById(R.id.btn_a_mW); //variable button es de clase Button
        Button btn_a_dbm = (Button) findViewById(R.id.btn_a_dbm);

        Button btn_a_millas = (Button) findViewById(R.id.btn_a_millas);
        Button btn_a_km = (Button) findViewById(R.id.btn_a_km);

        //DECLARO LOS EditText
        final EditText edit_dbm = (EditText) findViewById(R.id.edit_dbm); // se debe agregar final para declarar que no variaria esa variable y asi poder ingresar al set onclicklistener.
        final EditText edit_mW = (EditText) findViewById(R.id.edit_mW);

        final EditText edit_km = (EditText) findViewById(R.id.edit_km);
        final EditText edit_millas = (EditText) findViewById(R.id.edit_millas);

        //CONVERTIR A mW
        btn_a_mW.setOnClickListener(new View.OnClickListener() {    //Llamo al método setOn.. de la clase Button con mi variable button
            Boolean miv = null;
            @Override
            public void onClick(View v) {
                 Boolean miv = null;
                try {
                    String sdbm = edit_dbm.getText().toString(); // convierto el editable a string
                    float dbm = Float.parseFloat(sdbm); //convierto el string a float
                    Double mW = Math.pow(10, (dbm/10) );
                    String smW = String.format("%f", mW);
                    edit_mW.setText(smW);
                }catch (Exception e) {
                    Toast.makeText(dbm_mw.this, "Complete correctamente el campo dBm", Toast.LENGTH_SHORT).show();
                }
            }

        });


        //CONVERTIR A dBm
        btn_a_dbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String smW = edit_mW.getText().toString();
                    float mW = Float.parseFloat(smW);
                    Double dbm = 10 * Math.log10(mW);    //PdBm = 10 log10 PmW
                    String sdbm = String.format("%f", dbm);
                    edit_dbm.setText(sdbm);
                }catch (Exception e){
                    Toast.makeText(dbm_mw.this, "Complete correctamente el campo mW", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //CONVERTIR A Millas
        btn_a_millas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String skm = edit_km.getText().toString();
                    float km = Float.parseFloat(skm);
                    Double millas = km / 1.609;
                    String smillas = String.format("%f", millas);
                    edit_millas.setText(smillas);
                }catch (Exception e){
                    Toast.makeText(dbm_mw.this, "Complete correctamente el campo km", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //CONVERTIR A Km
        btn_a_km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String smillas = edit_millas.getText().toString();
                    float millas = Float.parseFloat(smillas);
                    Double km = millas * 1.609;
                    String skm = String.format("%f", km);
                    edit_km.setText(skm);
                }catch (Exception e){
                    Toast.makeText(dbm_mw.this, "Complete correctamente el campo Millas", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}