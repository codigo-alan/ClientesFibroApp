package com.example.conversorunidadesfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class inicio extends AppCompatActivity {
   // FirebaseFirestore mRootInicial;
    //claseRegistros mReg;
    //claseRegistros mRegContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        //mReg = new claseRegistros();
        //mRegContactos = new claseRegistros();

       // mRootInicial = FirebaseFirestore.getInstance();



        /*//obtengo valores para mails
        obtenerValorContactos("mails", "mailAlan");
        obtenerValorContactos("mails", "mailST");
        obtenerValorContactos("mails", "mailVentasGeneral");

        //obtengo valores para telefonos
        obtenerValorContactos("moviles", "movilAlan");
        obtenerValorContactos("moviles", "movilST");
        obtenerValorContactos("moviles", "movilVentas1");
        obtenerValorContactos("moviles", "movilVentas2");

         */

        /*//obtengo valores para SM
        obtenerValor("FC UPC SM");
        obtenerValor("SC UPC SM");
        obtenerValor("LC UPC SM");
        obtenerValor("ST UPC SM");
        obtenerValor("FC APC SM");
        obtenerValor("SC APC SM");
        obtenerValor("LC APC SM");
        obtenerValor("E2000 UPC SM");
        obtenerValor("E2000 APC SM");

        //obtengo valores para MM
        obtenerValor("FC UPC MM");
        obtenerValor("SC UPC MM");
        obtenerValor("LC UPC MM");
        obtenerValor("ST UPC MM");

        obtenerValor("Sin conector");

        //obtengo valores para cables
        obtenerValor("PVC 3mm SM");
        obtenerValor("PVC 3mm MM OM1");
        obtenerValor("PVC 3mm MM OM2");
        obtenerValor("PVC 3mm MM OM3");

         */
    }

    @Override   //En esta funcion hago que aparezca la action bar creada res/menu/menu_barra
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_barra, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override  //Los distintos case son para los botones que tiene la action bar
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
                Intent contactos = new Intent(this, contactos.class); //falta crear la activity de CONTACTOS
                /*Bundle bundleContactos = new Bundle();
                bundleContactos.putSerializable("objetoContactos", mRegContactos); //le agrego un serializable que va a ser mi objeto, con esa clave
                contactos.putExtras(bundleContactos); // al intent "coti" le envío con el putExtras

                 */
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

    public void conversor (View view){
        Intent conversor = new Intent(this, dbm_mw.class);
        startActivity(conversor);
    }

    public void web (View view){
        Intent web = new Intent(this, visualizadorWeb.class);
        startActivity(web);
    }

    public void coti (View view){
        Intent coti = new Intent(this, MainActivity.class);
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("objetoPrecios", mReg); //le agrego un serializable que va a ser mi objeto, con esa clave
        //coti.putExtras(bundle); // al intent "coti" le envío con el putExtras
        startActivity(coti);
    }

    public void instructivos (View view){
        Intent instru = new Intent(this, instructivos.class);
        startActivity(instru);
    }

    /*/FUNCION PARA OBTENER LOS DATOS DEL FIRESTORE, LOS OBTENGO UNA VEZ SOLA Y CREO UN HASHMAP
    public void obtenerValor(String string) {

        mRootInicial.collection("conectores").document(string).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String sPrecio = documentSnapshot.getString("precio");
                    //mReg.setValue(string, sPrecio);
                }
            }
        });
    }

     */


    /*/FUNCION PARA OBTENER LOS DATOS DEL FIRESTORE, LOS OBTENGO UNA VEZ SOLA Y CREO UN HASHMAP
    public void obtenerValorContactos(String coleccion, String documento) {

        mRootInicial.collection(coleccion).document(documento).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String sValor = documentSnapshot.getString("valor");
                    mRegContactos.setValue(documento, sValor);
                }
            }
        });
    }

     */

}