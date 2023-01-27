package com.example.appopendomotics.ui.menu;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.appopendomotics.R;
import com.example.appopendomotics.databinding.ActivityMenuBinding;
import com.google.android.material.navigation.NavigationView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;

public class MenuActivity extends AppCompatActivity {

    // Declara variable para la configuración de la barra de aplicaciones
    private AppBarConfiguration mAppBarConfiguration;
    // Declara variable para el enlace de datos de la actividad
    private ActivityMenuBinding binding;
    //Declarar los textview para pasarlos a string
    TextView dele,direc,tele,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Infla el layout de la actividad usando enlace de datos
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //guardar los textView en variables
        dele = (TextView) findViewById(R.id.TVmostrarDele);
        direc = (TextView) findViewById(R.id.TVmostrarDirec);
        tele = (TextView) findViewById(R.id.TVmostrarTele);
        email = (TextView) findViewById(R.id.TVmostrarEmail);

        // Establece la barra de herramientas como la barra de aplicaciones de la actividad
        setSupportActionBar(binding.appBarMain.barraHerramientas);
        // Obtiene la instancia del DrawerLayout del layout
        DrawerLayout drawer = binding.drawerLayout;
        // Obtiene la instancia del NavigationView del layout
        NavigationView navigationView = binding.navView;
        // Crea una instancia de AppBarConfiguration con los ID de menú como conjunto de ID porque cada menú debe considerarse como destinos de nivel superior
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home,
                R.id.nav_calendario, R.id.nav_partners, R.id.nav_pedidos, R.id.nav_envios)
                .setOpenableLayout(drawer)
                .build();
        // Obtiene la instancia del NavController del Fragmento del huésped
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        // Configura la barra de aplicaciones y el NavigationView con el NavController
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Obtener los datos del xml
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("Delegacion.xml");
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStream, "UTF-8");
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("delegacion")) {
                    dele.setText(parser.nextText());
                } else if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("direccion")) {
                    direc.setText(parser.nextText());
                } else if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("telefono")) {
                    tele.setText(parser.nextText());
                } else if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("email")) {
                    email.setText(parser.nextText());
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla el menú; esto agrega elementos a la barra de acciones si está presente
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Obtiene la instancia del NavController del Fragmento del huésped
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        // Navega hacia atrás en el Navigation graph utilizando la configuración de la barra de aplicaciones o muestra el botón Up
        // ************************************************************************
        // El Navigation graph es un archivo XML que contiene todas las pantallas de una aplicación y sus relaciones de navegación.
        // Cada pantalla es un fragmento y el Navigation graph describe cómo los fragmentos están conectados y cómo se deben mostrar en la aplicación.
        // ************************************************************************
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}
