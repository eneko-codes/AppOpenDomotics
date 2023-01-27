package com.example.appopendomotics.ui.calendario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.appopendomotics.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Ver_citas extends AppCompatActivity {
    // Constante para ubicacion de esta clase
    private final String UBICATION = "Ver_Citas";
    // Listview para mostrar la lista de socios
    ListView lstCitas;
    // Lista para almacenar los objetos de tipo Cita
    private ArrayList<Cita> citas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_citas);

        // Adaptador para el ListView
        ArrayAdapter citaadapter;
        lstCitas = findViewById(R.id.LVCitas);

        //Leer los datos de los socios del archivo xml
        leer();

        //Lista para almacenar los datos de nombre y telefono de los socios
        ArrayList<String> textcita = new ArrayList<>();

        //iterar sobre la lista de citas y obtener el nombre y el telefono
        for(Cita p: citas){
            textcita.add(p.getFecha());
            textcita.add(p.getTitulo());
        }

        // Inicializar el adaptador y asignarlo al ListView
        citaadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, textcita);
        lstCitas.setAdapter(citaadapter);
    }

    /**
     * Metodo para leer los datos de los socios del archivo xml
     */
    public void leer() {

        try{
            AssetManager assetMan = getAssets();
            InputStream is = assetMan.open("Citas.xml");

            // Crear un objeto DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Crear un objeto DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Parsing el archivo xml y obtener un objeto Document

            Document doc = builder.parse(is);

            //A partir de aquí se trataría el árbol DOM como siempre.
            //Por ejemplo:
            //Aviso de lectura de documento
            Log.i(UBICATION, "Archivo abierto | root: "+doc.getDocumentElement().getTagName());

            // Obtener una lista de elementos 'cita' del archivo xml
            NodeList listacitas = doc.getElementsByTagName("cita");

            // Iterar sobre cada elemento 'cita' de la lista
            for (int i = 0; i < listacitas.getLength(); i++) {
                Node node = listacitas.item(i);

                // Verificar si el nodo es un elemento
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Variables para almacenar los datos de un socio
                    Cita cita;
                    String titulo, fecha,hora,asunto;

                    // Obtener los datos de un socio del archivo xml
                    titulo = element.getElementsByTagName("titulo").item(0).getTextContent();
                    fecha = element.getElementsByTagName("fecha").item(0).getTextContent();
                    hora = element.getElementsByTagName("hora").item(0).getTextContent();
                    asunto = element.getElementsByTagName("asunto").item(0).getTextContent();

                    // Crear un objeto de tipo cita y asignarle los datos obtenidos
                    cita = new Cita(titulo,fecha,hora,asunto);
                    //Añadir el objeto cita al ArrayList cita
                    citas.add(cita);
                }
            }
        } catch (ParserConfigurationException ep) {
            Log.e(UBICATION,"ERROR. Configuración de parse incorrecta.");
        } catch (IOException eio) {
            Log.e(UBICATION,"ERROR. Fallo en lectura de archivo.");
        } catch (SAXException esax) {
            Log.e(UBICATION,"ERROR. SAX exception.");
        } catch (NullPointerException en) {
            Log.e(UBICATION,"ERROR. XML mal formado o etiqueta buscada inexistente.");
        }
    }
    }
