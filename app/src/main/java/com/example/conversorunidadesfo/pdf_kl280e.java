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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class pdf_kl280e extends AppCompatActivity {

    private long downloadID;
    PDFView pdfKL280;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_kl280e);

        //ponemos flecha hacia una parent activity en la action bar
        ActionBar barra_accion = getSupportActionBar();
        barra_accion.setDisplayHomeAsUpEnabled(true);

        pdfKL280 = findViewById(R.id.pdf_viewer_kl280e); //elemento del tipo pdf viewer (fue necesario cargarlo en las dependencias gradle:Build:App)
        progressBar = findViewById(R.id.pb_kl280);

        String miUrl = "https://si.ua.es/es/documentos/documentacion/pdf-s/mozilla12-pdf.pdf";
        new RecibirPDF(pdfKL280, progressBar).execute(miUrl);

    }

    @Override   //En esta funcion hago que aparezca la action bar creada res/menu/menu_barra
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_barra_instructivos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Aca le asigno una accion a cada boton de la ACTION BAR
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
                request.setTitle("KL280E");
                request.setDescription("Downloading file");
                request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "KL280E.pdf");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                downloadID = downloadManager.enqueue(request);

                Toast.makeText(this, "Comenzó la descarga.", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}