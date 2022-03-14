package com.example.conversorunidadesfo;



import java.util.HashMap;

//Clase para asignar íconos a cada conector en un hashmap

public class claseImagenes {

    HashMap<String, Integer> miMapa2 = new HashMap<String, Integer>();

    public claseImagenes() {
        miMapa2.put("FC UPC SM", R.drawable.fcupc2ok);
        miMapa2.put("SC UPC SM", R.drawable.scupcok);
        miMapa2.put("LC UPC SM", R.drawable.lcupcok);
        miMapa2.put("ST UPC SM", R.drawable.stupcok);
        miMapa2.put("FC APC SM", R.drawable.fcapcok);
        miMapa2.put("SC APC SM", R.drawable.scapcok);
        miMapa2.put("LC APC SM", R.drawable.lcapc2);
        miMapa2.put("E2000 UPC SM", R.drawable.e2000upcok);
        miMapa2.put("E2000 APC SM", R.drawable.e2000apcok);

        miMapa2.put("FC UPC MM", R.drawable.fcupc2ok);
        miMapa2.put("SC UPC MM", R.drawable.scupcok);
        miMapa2.put("LC UPC MM", R.drawable.lcupcok);
        miMapa2.put("ST UPC MM", R.drawable.stupcok);

        miMapa2.put("Sin conector", null);

        miMapa2.put("PVC 3mm SM", R.drawable.cablesm);
        miMapa2.put("PVC 3mm MM OM1", R.drawable.cablemm);
        miMapa2.put("PVC 3mm MM OM2", R.drawable.cablemm);
        miMapa2.put("PVC 3mm MM OM3", R.drawable.cablemm);
    }

    //Método para acceder al valor de la clave. clave=seleccion
    public Integer getValue (String seleccion) {
        return miMapa2.get(seleccion);

    }

}

