package com.example.conversorunidadesfo;
//FALTA AGREGAR LOS VALORES CON LOS BUNDLE PARA CADA SOLICITUD DE ENVIO DE WSPP Y MAIL, EXCEPTO DEL WSPP DE ST
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class contactos extends AppCompatActivity {



    FirebaseFirestore mRoot;
    claseRegistros mReg;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contactos); //muestra la activity activity_contactos

        ActionBar barra_accion = getSupportActionBar(); //muestra action bar con flecha hacia un PARENT ACTIVITY
        barra_accion.setDisplayHomeAsUpEnabled(true);

        mRoot = FirebaseFirestore.getInstance();
        mReg = new claseRegistros();

        //obtengo valores para mails
        obtenerValor("mails", "mailAlan");
        obtenerValor("mails", "mailST");
        obtenerValor("mails", "mailVentasGeneral");

        //obtengo valores para telefonos
        obtenerValor("moviles", "movilAlan");
        obtenerValor("moviles", "movilST");
        obtenerValor("moviles", "movilVentas1");
        obtenerValor("moviles", "movilVentas2");

        Toast.makeText(getApplicationContext(), "Finalizada la carga", Toast.LENGTH_SHORT).show();


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

    //envía un mensaje por wspp
    public void sendWspp (View view) {
        String phone = mReg.getValue("movilVentas1");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        String send_phoneTxt = "whatsapp://send?phone="+phone+"&text="+"Estimados, me comunico para: ";
        sendIntent.setData(Uri.parse(send_phoneTxt));
        startActivity(sendIntent);

    }

    //envía un mensaje por wspp a 2do contacto de ventas
    public void sendWspp2 (View view) {
        String phone = mReg.getValue("movilVentas2");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        String send_phoneTxt = "whatsapp://send?phone="+phone+"&text="+"Estimados, me comunico para: ";
        sendIntent.setData(Uri.parse(send_phoneTxt));
        startActivity(sendIntent);
    }

    //envía un mensaje por wspp a Servicio técnico
    public void sendWsppSt (View view) {
        String phone = mReg.getValue("movilST");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        String send_phoneTxt = "whatsapp://send?phone="+phone+"&text="+"Estimados, me comunico para: ";
        sendIntent.setData(Uri.parse(send_phoneTxt));
        startActivity(sendIntent);
    }

    //envía un mail a ventas
    public void sendMail (View view) {
        String casilla = mReg.getValue("mailVentasGeneral");
        String[] destinatario = casilla.split(",");
        Intent email = new Intent();
        email.setAction(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL, destinatario);
        email.putExtra(Intent.EXTRA_SUBJECT,"Consulta desde App.");
        email.putExtra(Intent.EXTRA_TEXT,"Estimados, me contacto para ");
        startActivity(Intent.createChooser(email,"Send Email"));
    }


    //envía un mail a servicio tecnico
    public void sendMailst (View view) {
        String casilla = mReg.getValue("mailST");
        String[] destinatario = casilla.split(",");
        Intent email = new Intent();
        email.setAction(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL, destinatario);
        email.putExtra(Intent.EXTRA_SUBJECT,"Consulta desde App.");
        email.putExtra(Intent.EXTRA_TEXT,"Estimados, me contacto para ");
        startActivity(Intent.createChooser(email,"Send Email"));
    }

    //FUNCION PARA OBTENER LOS DATOS DEL FIRESTORE, LOS OBTENGO UNA VEZ SOLA Y CREO UN HASHMAP
    public void obtenerValor(String coleccion, String documento) {

        mRoot.collection(coleccion).document(documento).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String sValor = documentSnapshot.getString("valor");
                    mReg.setValue(documento, sValor);
                }
            }
        });
    }

}