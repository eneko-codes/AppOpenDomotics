package com.example.appopendomotics.ui.partners;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;

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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

public class Crear_Partners extends AppCompatActivity {
    EditText nombre, direccion, poblacion, cif, telefono, email, comercial;
    Button crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_partners);
        final int REQUEST_CODE = 123;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // El permiso no ha sido otorgado anteriormente
            // Solicitar el permiso
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE);
        } else {
            // El permiso ha sido otorgado anteriormente
            // Realizar la acción que requiere el permiso
        }

        nombre = findViewById(R.id.ETnombre);
        direccion = findViewById(R.id.ETdireccion);
        poblacion = findViewById(R.id.ETpoblacion);
        cif = findViewById(R.id.ETcif);
        telefono = findViewById(R.id.ETtelefono);
        email = findViewById(R.id.ETemail);
        comercial = findViewById(R.id.ETcomercial);
        crear = findViewById(R.id.BTNcrearPartner);
    }
        public void saveData(View view) {
                    String nombre2 = nombre.getText().toString();
                    String direccion2 = direccion.getText().toString();
                    String poblacion2 = poblacion.getText().toString();
                    String cif2 = cif.getText().toString();
                    String telefono2 = telefono.getText().toString();
                    String email2 = email.getText().toString();
                    String comercial2 = comercial.getText().toString();

        if( nombre2.isEmpty()) {
            Toast.makeText(this, "Tienes que poner un nombre", Toast.LENGTH_SHORT).show();
        }else if(direccion2.isEmpty()){
                Toast.makeText(this, "Tienes que poner una direccion", Toast.LENGTH_SHORT).show();
        }else if(poblacion2.isEmpty()){
            Toast.makeText(this, "Tienes que poner la poblacion", Toast.LENGTH_SHORT).show();
        }else if(cif2.isEmpty()){
            Toast.makeText(this, "Tienes que poner un cif", Toast.LENGTH_SHORT).show();
        }else if(telefono2.isEmpty()){
            Toast.makeText(this, "Tienes que poner un telefono", Toast.LENGTH_SHORT).show();
        }else if(email2.isEmpty()){
            Toast.makeText(this, "Tienes que poner un email", Toast.LENGTH_SHORT).show();
        }else if(comercial2.isEmpty()){
            Toast.makeText(this, "Tienes que poner un comercial", Toast.LENGTH_SHORT).show();
        }else{
            //Con este codigo de 10 lineas conseguimos que si el archivo partner.xml ya existe
            // y queremos crear un segundo partner se cree como partner(1).xml o partner(2).xml y asi sucesivamente
            int i = 1;
            String fileName = "NuevosPartner.xml";
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);

            while (file.exists()) {
                String newFileName = String.format("NuevosPartner(%d).xml", i);
                file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), newFileName);
                i++;
            }
            FileOutputStream fos = null;
            try {
                Log.e("TAG", file.getAbsolutePath());
                fos = new FileOutputStream(file);
                XmlSerializer xmlSerializer = Xml.newSerializer();
                StringWriter writer = new StringWriter();
                xmlSerializer.setOutput(writer);
                xmlSerializer.startDocument("UTF-8", true);
                xmlSerializer.startTag(null, "partner");
                xmlSerializer.startTag(null, "nombre");
                xmlSerializer.text(nombre2);
                xmlSerializer.endTag(null, "nombre");
                xmlSerializer.startTag(null, "direccion");
                xmlSerializer.text(direccion2);
                xmlSerializer.endTag(null, "direccion");
                xmlSerializer.startTag(null, "poblacion");
                xmlSerializer.text(poblacion2);
                xmlSerializer.endTag(null, "poblacion");
                xmlSerializer.startTag(null, "cif");
                xmlSerializer.text(cif2);
                xmlSerializer.endTag(null, "cif");
                xmlSerializer.startTag(null, "telefono");
                xmlSerializer.text(telefono2);
                xmlSerializer.endTag(null, "telefono");
                xmlSerializer.startTag(null, "email");
                xmlSerializer.text(email2);
                xmlSerializer.endTag(null, "email");
                xmlSerializer.startTag(null, "comercial");
                xmlSerializer.text(comercial2);
                xmlSerializer.endTag(null, "comercial");
                xmlSerializer.endTag(null, "partner");
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
        }}


