package com.example.conversorunidadesfo;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class obtainRegisters {
    FirebaseFirestore mRootObj;
    String mValue;


    //CONSTRUCTOR
    public obtainRegisters() {
        mRootObj = FirebaseFirestore.getInstance(); // al construirlo (instanciarlo) se obtiene la instancia
    }


    //GETTER
    public void get_mValue(String coleccion, String documento, String campo) {
        mRootObj.collection(coleccion).document(documento).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                mValue = documentSnapshot.getString(campo);

            }
        });

    }


}
