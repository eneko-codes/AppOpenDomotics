package com.example.appopendomotics.ui.partners;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

/**
 * Clase para la actividad de ver socios
 */
public class Ver_Partners extends AppCompatActivity {
    // Constante para ubicacion de esta clase
    private final String UBICATION = "Ver_Partners";
    // Listview para mostrar la lista de socios
    ListView lstPartners;
    // Lista para almacenar los objetos de tipo Partner
    private ArrayList<Partner> partners = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_partners);

        // Adaptador para el ListView
        ArrayAdapter partneradapter;
        lstPartners = findViewById(R.id.LVpartners);

        //Leer los datos de los socios del archivo xml
        leer();

        //Lista para almacenar los datos de nombre y telefono de los socios
        ArrayList<String> textpartner = new ArrayList<>();

        //iterar sobre la lista de partners y obtener el nombre y el telefono
        for(Partner p: partners){
            textpartner.add(p.getNombre());
            textpartner.add(p.getTelefono());
        }

        // Inicializar el adaptador y asignarlo al ListView
        partneradapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, textpartner);
        lstPartners.setAdapter(partneradapter);
    }

    /**
     * Metodo para leer los datos de los socios del archivo xml
     */
    public void leer() {

        try{
            AssetManager assetMan = getAssets();
            InputStream is = assetMan.open("partners.xml");

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

            // Obtener una lista de elementos 'partner' del archivo xml
            NodeList listapartners = doc.getElementsByTagName("partner");

            // Iterar sobre cada elemento 'partner' de la lista
            for (int i = 0; i < listapartners.getLength(); i++) {
                Node node = listapartners.item(i);

                // Verificar si el nodo es un elemento
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Variables para almacenar los datos de un socio
                    Partner partner;
                    String nombre, direccion,poblacion,cif,telefono,email,comercial;

                    // Obtener los datos de un socio del archivo xml
                    nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    direccion = element.getElementsByTagName("direccion").item(0).getTextContent();
                    poblacion = element.getElementsByTagName("poblacion").item(0).getTextContent();
                    cif = element.getElementsByTagName("CIF").item(0).getTextContent();
                    telefono = element.getElementsByTagName("telefono").item(0).getTextContent();
                    email = element.getElementsByTagName("email").item(0).getTextContent();
                    comercial = element.getElementsByTagName("comercial").item(0).getTextContent();

                    // Crear un objeto de tipo Partner y asignarle los datos obtenidos
                    partner = new Partner(nombre, direccion,poblacion, cif, telefono, email, comercial);
                    //Añadir el objeto Partner al ArrayList partners
                    partners.add(partner);
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
