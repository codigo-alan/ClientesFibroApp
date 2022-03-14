package com.example.conversorunidadesfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.net.URI;

public class ato_350 extends AppCompatActivity {

    private long downloadID;
    PDFView pdfATO350;
    ProgressBar progressBar_ato350;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ato_350);

        //ponemos flecha hacia una parent activity en la action bar
        ActionBar barra_accion = getSupportActionBar();
        barra_accion.setDisplayHomeAsUpEnabled(true);

        pdfATO350 = findViewById(R.id.pdf_viewer_ato350); //elemento del tipo pdf viewer (fue necesario cargarlo en las dependencias gradle:Build:App)
        progressBar_ato350 = findViewById(R.id.progressBar_ato350);

        String miUrl = "https://si.ua.es/es/documentos/documentacion/pdf-s/mozilla12-pdf.pdf";
        new RecibirPDF(pdfATO350, progressBar_ato350).execute(miUrl);
    }

    @Override   //En esta funcion hago que aparezca la action bar creada res/menu/menu_barra
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_barra_instructivos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent home = new Intent(this, inicio.class);
                startActivity(home);
                return true;
            case R.id.dwnld:
                Uri uri = Uri.parse("https://si.ua.es/es/documentos/documentacion/pdf-s/mozilla12-pdf.pdf");

                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle("Ato350");
                request.setDescription("Downloading file");
                request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "ATO_350.pdf");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                downloadID = downloadManager.enqueue(request);
                Toast.makeText(this, "Comenzó la descarga.", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //Funcion que realiza la descarga
    //ya está de más, porque lo hago desde la action bar
    public void startDownload(View view){
        Uri uri = Uri.parse("https://drive.google.com/file/d/1v4MPHob5g20bUvXQHEiRi6a3ucpRdG0G/view");

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("PRUEBA");
        request.setDescription("Download file de PRUEBA");
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "plcuba.pdf");

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadID = downloadManager.enqueue(request);

    }
}