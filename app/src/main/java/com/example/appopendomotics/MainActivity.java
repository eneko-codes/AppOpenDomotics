package com.example.appopendomotics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appopendomotics.ui.menu.MenuActivity;

public class MainActivity extends AppCompatActivity {

    // Declara variables para los campos de texto EditText de correo electrónico y contraseña y para el botón de inicio de sesión
    private Button logear;
    private EditText email, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializa variables para el correo electrónico, la contraseña y el botón de inicio de sesión
        email = findViewById(R.id.email);
        contraseña = findViewById(R.id.password);
        logear = findViewById(R.id.login);

        // Habilitar el botón de inicio de sesión. Falta el método de validación (se hará en la segunda fase del reto con la base de datos)
        logear.setEnabled(true);

        logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene el correo electrónico del usuario y lo almacena en una variable
                String usuario = String.valueOf(email.getText());
                String contra = String.valueOf(contraseña.getText());
                if(usuario.equals("javi@gmail.com") && contra.equals("1234")) {
                    // Crea un Intent para iniciar el MenuActivity
                    Intent menu = new Intent(MainActivity.this, MenuActivity.class);
                    // Agrega el correo electrónico del usuario como un extra al Intent
                    menu.putExtra("usuario", usuario);
                    // Inicia el MenuActivity
                    startActivity(menu);
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
