package com.example.appopendomotics.ui.calendario;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.appopendomotics.R;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

public class Crear_Citas extends AppCompatActivity {
    EditText titulo, fecha,hora,asunto;
    Button crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_citas);
        final int REQUEST_CODE = 123;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // El permiso no ha sido otorgado anteriormente
            // Solicitar el permiso
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
        } else {
            // El permiso ha sido otorgado anteriormente
            // Realizar la acción que requiere el permiso
        }

        titulo = findViewById(R.id.ETtitulo);
        fecha = findViewById(R.id.ETfecha);
        hora = findViewById(R.id.EThora);
        asunto = findViewById(R.id.ETasunto);

    }
    public void saveData(View view) {
        String titulo2 = titulo.getText().toString();
        String fecha2 = fecha.getText().toString();
        String hora2 = hora.getText().toString();
        String asunto2 = asunto.getText().toString();
        crear = findViewById(R.id.BTNcrearCitas);


        if( titulo2.isEmpty()) {
            Toast.makeText(this, "Tienes que poner un titulo", Toast.LENGTH_SHORT).show();
        }else if(fecha2.isEmpty()){
            Toast.makeText(this, "Tienes que poner una fecha", Toast.LENGTH_SHORT).show();
        }else if(hora2.isEmpty()){
            Toast.makeText(this, "Tienes que poner la hora", Toast.LENGTH_SHORT).show();
        }else if(asunto2.isEmpty()){
            Toast.makeText(this, "Tienes que poner un asunto", Toast.LENGTH_SHORT).show();
        }else{
            //Con este codigo de 10 lineas conseguimos que si el archivo partner.xml ya existe
            // y queremos crear un segundo partner se cree como partner(1).xml o partner(2).xml y asi sucesivamente
            int i = 1;
            String fileName = "citas.xml";
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
            while (file.exists()) {
                String newFileName = String.format("citas(%d).xml", i);
                file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), newFileName);
                i++;
            }            FileOutputStream fos = null;
            try {
                Log.e("TAG", file.getAbsolutePath());
                fos = new FileOutputStream(file);
                XmlSerializer xmlSerializer = Xml.newSerializer();
                StringWriter writer = new StringWriter();
                xmlSerializer.setOutput(writer);
                xmlSerializer.startDocument("UTF-8", true);
                xmlSerializer.startTag(null, "citas");
                xmlSerializer.startTag(null, "titulo");
                xmlSerializer.text(titulo2);
                xmlSerializer.endTag(null, "titulo");
                xmlSerializer.startTag(null, "fecha");
                xmlSerializer.text(fecha2);
                xmlSerializer.endTag(null, "fecha");
                xmlSerializer.startTag(null, "hora");
                xmlSerializer.text(hora2);
                xmlSerializer.endTag(null, "hora");
                xmlSerializer.startTag(null, "asunto");
                xmlSerializer.text(asunto2);
                xmlSerializer.endTag(null, "asunto");
                xmlSerializer.endTag(null, "citas");
                xmlSerializer.endDocument();
                xmlSerializer.flush();
                String dataWrite = writer.toString();
                fos.write(dataWrite.getBytes());
                fos.close();
                Toast.makeText(this, "XML creado con exito", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}