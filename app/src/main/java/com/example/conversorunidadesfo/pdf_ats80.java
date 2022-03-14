package com.example.conversorunidadesfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class pdf_ats80 extends AppCompatActivity {

    PDFView pdfATS80;
    ProgressBar progressBar;
    private long downloadID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_ats80);

        //ponemos flecha hacia una parent activity en la action bar
        ActionBar barra_accion = getSupportActionBar();
        barra_accion.setDisplayHomeAsUpEnabled(true);

        pdfATS80 = findViewById(R.id.pdf_viewer_ats); //elemento del tipo pdf viewer (fue necesario cargarlo en las dependencias gradle:Build:App)
        progressBar = findViewById(R.id.progressBar);

        String miUrl = "https://si.ua.es/es/documentos/documentacion/pdf-s/mozilla12-pdf.pdf";
        new RecibirPDF(pdfATS80, progressBar).execute(miUrl);

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
                request.setTitle("ATS80 A");
                request.setDescription("Downloading file");
                request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "ATS80a.pdf");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                downloadID = downloadManager.enqueue(request);
                Toast.makeText(this, "Comenzó la descarga.", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}