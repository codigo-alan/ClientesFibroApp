package com.example.conversorunidadesfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    Spinner con_1;                                  //los spinner
    Spinner con_2;
    Spinner sp_cable;
    Spinner sp_long;
    ImageView img_con1, img_con2, img_cable;    //imagenes
    Integer item_img, item_img2, item_img_ca; //enteros para obtener k,v de myMap2 (objeto tipo clasImagenes)
    String item_sel, item_sel2, item_sel_ca; //String para saber qué seleccionó usuario

    TextView txt_precio;
    CheckBox cb;
    TextView txtPrecio1,txtPrecio2,txtPrecio3;
    FirebaseFirestore mRoot;
    claseRegistros mReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Agrego flecha hacia atras en ACTION BAR
         */

        ActionBar barra_accion = getSupportActionBar(); //Hago que aparezca la
        barra_accion.setDisplayHomeAsUpEnabled(true);   //flecha en la barra de accion

        /*
        Declaro las variables del xml y las referencio

         */
        Button btn_cotizar = (Button) findViewById(R.id.btn_cotizar);
        txt_precio = (TextView) findViewById(R.id.txt_precio);

        cb = (CheckBox) findViewById(R.id.checkBox); //Simple o doble
        final EditText edit_formato = (EditText) findViewById(R.id.edit_formato); //1 o 2 (simple o doble)

        con_1 = (Spinner) findViewById(R.id.spinner); //lista opciones conector 1
        con_2 = (Spinner) findViewById(R.id.spinner2); //lista opciones conector 2
        sp_cable = (Spinner) findViewById(R.id.spinner3); //lista de opciones de cable
        sp_long = (Spinner) findViewById(R.id.spinner4); //lista de opciones largo de cable

        img_con1 = (ImageView) findViewById(R.id.img_con1);
        img_con2 = (ImageView) findViewById(R.id.img_con2);
        img_cable = (ImageView) findViewById(R.id.img_cable);

        txtPrecio1 = (TextView) findViewById(R.id.txtPrecio1);
        txtPrecio2 = (TextView) findViewById(R.id.txtPrecio2);
        txtPrecio3 = (TextView) findViewById(R.id.txtPrecio3);

        /*/RECIBO LOS INTENTS y ASIGNO VALOR A MI OBJETO
        Bundle recBundle = getIntent().getExtras(); //recibo los extras en un Bundle
        final claseRegistros recReg = (claseRegistros) recBundle.getSerializable("objetoPrecios"); //a mi objeto creado le asigno el valor del Bundle recibido
        */


        mRoot = FirebaseFirestore.getInstance();
        mReg = new claseRegistros();
        //obtengo valores para SM
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

        /*  (precio_conector1 + precio_conector2 + (precio_cable*longitud)) * formato = precioTotal
         *                                                    =                   =
         *                                            (precio_cableFinal)      (1 ó 2)
         *
         *
         */




        /*
        Creo lista con opciones de selección
        Creo array para mostrar en el Spinner longitud
        Los demás spinner se rellenan en el método condicional_tipo (de mas abajo)
         */

        //Lista, Array y colocación para la longitud del cable
        String [] opc_long = {"0.5","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"}; //lista de una dimensión para longitud
        ArrayAdapter<String> adapter_long = new ArrayAdapter<String> (this, R.layout.spinner_item_1, opc_long);
        sp_long.setAdapter(adapter_long);//coloco lista en spinner longitud


        /*
        Creo instancia de mi cloud firestore
         */




        /*
        verifico si es doble o simple el patchcord
        ***********************************************************************
        */

        edit_formato.setText("1");  //inicializo el edit text de formato en 1, si lo clickeo cambiará.
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    edit_formato.setText("2");
                    txt_precio.setText(""); //borro el precio de la anterior cotización

                }else{
                    edit_formato.setText("1");
                    txt_precio.setText(""); //borro el precio de la anterior cotización
                }
            }
        });


        /*   -Creo el objeto myMap, del tipo claseHashMap.
             para que cuando de click en cotizar pueda obtener el valor de la clave con getValue(clave seleccionada)
        *******************************************************************************************************
             -Creo el objeto myMapImage, del tipo claseImagenes.
             para que cuando haga click en cotizar pueda obtener el iconito de la clave con getValue(clave seleccionada)

        */

        //claseRegistros myMap = new claseRegistros(); //creacion del objeto del tipo de mi claseRegistros
        claseImagenes myMap2 = new claseImagenes(); //Creo objeto del tipo mi claseImagenes
        Toast.makeText(getApplicationContext(),"Creada clase imagenes", Toast.LENGTH_SHORT);

        /*
        ITEM SELECTED DE LOS SPINNER
         */

        //Para el conector 2
        con_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_sel2 = parent.getItemAtPosition(position).toString();
                item_img2 = myMap2.getValue(item_sel2);

                String con2 = mReg.getValue(item_sel2);
                txtPrecio2.setText(con2);

                txt_precio.setText(""); //borro el precio de la anterior cotización

                if (item_sel2 == "Sin conector"){
                    Toast.makeText(getApplicationContext(), "No habrá conector en un extremo.", Toast.LENGTH_SHORT).show();
                    img_con2.setVisibility(View.GONE);
                }else {
                    img_con2.setVisibility(View.VISIBLE);
                    img_con2.setImageResource(item_img2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //para el conector 1
        con_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_sel = parent.getItemAtPosition(position).toString();
                item_img = myMap2.getValue(item_sel);
                img_con1.setVisibility(View.VISIBLE);
                img_con1.setImageResource(item_img);
                String con1 = mReg.getValue(item_sel);
                txtPrecio1.setText(con1);
                txt_precio.setText(""); //borro el precio de la anterior cotización

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //para el cable
        sp_cable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_sel_ca = parent.getItemAtPosition(position).toString();
                item_img_ca = myMap2.getValue(item_sel_ca);
                img_cable.setVisibility(View.VISIBLE);
                img_cable.setImageResource(item_img_ca);
                String cable = mReg.getValue(item_sel_ca);
                txtPrecio3.setText(cable);
                txt_precio.setText(""); //borro el precio de la anterior cotización

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //para la longitud
        sp_long.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt_precio.setText(""); //borro el precio de la anterior cotización
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });






        /*configuro el boton cotizar. Al clickarlo hace la cuenta necesaria
         *******************************************************************  */
        btn_cotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try {
                    Double precioTotal = null; //declaro la variable precio total, necesario declararla aqui dentro

                    //codigo para conector 1
                    String sV_Conector1 = txtPrecio1.getText().toString();
                    Double precio_conector1 = Double.parseDouble(sV_Conector1);


                    //codigo para conector 2
                    String sV_Conector2 = txtPrecio2.getText().toString();
                    Double precio_conector2 = Double.parseDouble(sV_Conector2);


                    //codigo para cable y longitud
                    String longitud = sp_long.getSelectedItem().toString(); //obtengo el item seleccionado en spinner
                    Double d_longitud = Double.parseDouble(longitud);       //convierto el item de String a Double para poder operar

                    String sV_cable = txtPrecio3.getText().toString();
                    Double precio_cable = Double.parseDouble(sV_cable);
                    Double precio_cableFinal = precio_cable * d_longitud;   //multiplico el precio del cable * la longitud que seleccionó el usuario


                    //codigo para formato (doble o simple). quizas haya una manera más elegante
                    String s_tipo = edit_formato.getText().toString(); //almaceno en variable lo que haya seleccionado el usuario en el checkbox
                    Double tipo = Double.parseDouble(s_tipo);


                    //código para precio total
                    // -fórmula para obtener el precio total
                    // -colocación del texto en el edit text de precio total
                    precioTotal = (precio_conector1 + precio_conector2 + (precio_cableFinal)) * tipo;

                    String s_precioTotal = String.format("%f", precioTotal);
                    txt_precio.setText("usd " + s_precioTotal);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Asegúrese de completar bien todos los campos",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //condicionales según si seleccionó SM o MM. Se cargaran distintas listas según la selección previa. Método
    //que se ejecutará cada vez q haga click en algún radio button
    public void condicional_tipo(View view) {

        Boolean checked = ((RadioButton) view).isChecked();


        switch (view.getId()){
            case R.id.radioButton_sm:
                if (checked){
                    txt_precio.setText(""); //borro el precio de la anterior cotización
                    //Lista, Array para el conector 1
                    String [] opc_conector1_SM = {"FC UPC SM", "SC UPC SM", "LC UPC SM", "ST UPC SM", "FC APC SM", "SC APC SM", "LC APC SM", "E2000 UPC SM", "E2000 APC SM" }; //lista de una dimensión para SM
                    ArrayAdapter<String> adapterSM = new ArrayAdapter<String> (this, R.layout.spinner_item_1, opc_conector1_SM); //Array para  luego agregar lista de SM en el spinner
                    con_1.setAdapter(adapterSM);//coloco lista SM en spinner con1

                    //Lista, Array para el conector 2
                    String [] opc_conector2_SM = {"Sin conector", "FC UPC SM", "SC UPC SM", "LC UPC SM", "ST UPC SM", "FC APC SM", "SC APC SM", "LC APC SM", "E2000 UPC SM", "E2000 APC SM" }; //lista de una dimensión
                    ArrayAdapter<String> adapter2SM = new ArrayAdapter<String> (this, R.layout.spinner_item_1, opc_conector2_SM);
                    con_2.setAdapter(adapter2SM); //coloco lista SM en spinner con2

                    //Lista, Array para el cable
                    String [] opc_cable_SM = {"PVC 3mm SM"}; //lista de una dimensión con las opciones de cable SM
                    ArrayAdapter<String> adapter_cableSM = new ArrayAdapter<String> (this, R.layout.spinner_item_1, opc_cable_SM); //Array para  luego agregar lista de SM en el spinner de cable
                    sp_cable.setAdapter(adapter_cableSM); //coloco lista SM en spinner cable


                }
                break;
            case R.id.radioButton_mm:
                if (checked){
                    txt_precio.setText(""); //borro el precio de la anterior cotización
                    String [] opc_conector1_MM = {"FC UPC MM", "SC UPC MM", "LC UPC MM", "ST UPC MM"}; //lista de una dimensión para MM
                    ArrayAdapter<String> adapterMM = new ArrayAdapter<String>(this, R.layout.spinner_item_1, opc_conector1_MM); //Array para  luego agregar lista de MM en el spinner
                    con_1.setAdapter(adapterMM);//coloco lista MM en spinner con1

                    String [] opc_conector2_MM = {"Sin conector", "FC UPC MM", "SC UPC MM", "LC UPC MM", "ST UPC MM"};
                    ArrayAdapter<String> adapter2MM = new ArrayAdapter<String>(this, R.layout.spinner_item_1, opc_conector2_MM);
                    con_2.setAdapter(adapter2MM); //coloco lista MM en spinner con2

                    String [] opc_cable_MM = {"PVC 3mm MM OM1", "PVC 3mm MM OM2", "PVC 3mm MM OM3"}; //lista de una dimensión con las opciones de cable MM
                    ArrayAdapter<String> adapter_cableMM = new ArrayAdapter<String> (this, R.layout.spinner_item_1, opc_cable_MM); //
                    sp_cable.setAdapter(adapter_cableMM); //coloco lista MM en spinner cable


                }
                break;

        }
    }

    public void cot_formal(View view) {

        try {
            String conector1 = con_1.getSelectedItem().toString();
            String conector2 = con_2.getSelectedItem().toString();
            String cable = sp_cable.getSelectedItem().toString();
            String longitud = sp_long.getSelectedItem().toString();
            String formato;

            if (cb.isChecked()){
                formato = "DOBLE";
            }else {
                formato = "SIMPLE";
            }

            String[] destinatario = "alanfmarcos@gmail.com".split(",");
            Intent email = new Intent();
            email.setAction(Intent.ACTION_SEND);
            email.setData(Uri.parse("mailto:"));
            email.setType("text/plain");
            email.putExtra(Intent.EXTRA_EMAIL, destinatario);
            email.putExtra(Intent.EXTRA_SUBJECT,"Cotización de patchcord.");
            email.putExtra(Intent.EXTRA_TEXT,"Estimados, me contacto para solicitar cotización formal de un patchcord con los siguientes requerimientos:" +
                    "\n \n \n 1er extremo: " + conector1 + "\n 2do extremo: " + conector2 + "\n Cable: " + cable + " de " +
                    longitud + " mts"+ "\n Formato: " + formato + "\n \n \nSaludos cordiales.");
            startActivity(Intent.createChooser(email,"Send Email"));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Asegúrese de completar bien todos los campos", Toast.LENGTH_SHORT).show();
        }



    }

    //FUNCION PARA OBTENER LOS DATOS DEL FIRESTORE, LOS OBTENGO UNA VEZ SOLA Y CREO UN HASHMAP
    public void obtenerValor(String documento) {

        mRoot.collection("conectores").document(documento).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String sValor = documentSnapshot.getString("precio");
                    mReg.setValue(documento, sValor);
                }
            }
        });
    }







}