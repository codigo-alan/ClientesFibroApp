package com.example.conversorunidadesfo;

import java.io.Serializable;
import java.util.HashMap;

public class claseRegistros implements Serializable { //Tiene que ser Serializable para poder transferirlo entre activities

    HashMap<String,String> myHashMapRegisters = new HashMap<String, String>();

    //constructor
    public claseRegistros() {

    }

    //obtengo los valores
    public String getValue(String seleccion) {
        return myHashMapRegisters.get(seleccion);
    }

    //coloco los valores
    public void setValue(String clave, String valor) {
        myHashMapRegisters.put(clave, valor);
    }
}
