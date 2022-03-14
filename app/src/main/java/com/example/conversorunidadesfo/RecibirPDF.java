package com.example.conversorunidadesfo;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.common.util.Strings;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecibirPDF extends AsyncTask <String, Void, InputStream> {

    PDFView pdfATS80;
    ProgressBar progressBar;



    //Esto es un constructor, click derecho(Generate-->constructor-->y seleccionar parámetros que quiera)
    public RecibirPDF(PDFView pdfATS80, ProgressBar progressBar) {
        this.pdfATS80 = pdfATS80;
        this.progressBar = progressBar;
    }




    //Esta funcion la realiza en el background (o algo asi)
    @Override
    protected InputStream doInBackground(String... strings) {
        InputStream inputStream = null; //Inicializo variable del tipo InputStream
        try {
            URL url = new URL(strings[0]); //URL de mi documento
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); //Establezco una conexión del tipo HttpUrlConnection

            if (urlConnection.getResponseCode() == 200){ //si la respuesta de la página es buena
                inputStream = new BufferedInputStream(urlConnection.getInputStream()); //obtengo el InputStream de la conexión que
            }                                                                          //se estableció y se lo asigno a mi variable

        } catch (IOException e){
            return null;
        }

        return inputStream; //devuelve mi variable inputStream
    }

    @Override
    protected void onPostExecute(InputStream inputStream){
        pdfATS80.fromStream(inputStream).load();
        progressBar.setVisibility(View.GONE);
    }


}
