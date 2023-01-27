package com.example.appopendomotics.ui.pedidos;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.appopendomotics.R;

import android.content.res.AssetManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class Ver_pedidos extends AppCompatActivity {
    private final String UBICATION = "Ver_pedidos";
    ListView lstPedidos;
    private ArrayList<Pedidos> pedidos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos);

        ArrayAdapter pedidoadapter;
        lstPedidos = findViewById(R.id.LVpedidos);

        leer();

        ArrayList<String> textpedido = new ArrayList<>();

        for(Pedidos p: pedidos){
            textpedido.add(p.getnPedidos());
            textpedido.add(p.getFechaPedido());
        }

        pedidoadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, textpedido);
        lstPedidos.setAdapter(pedidoadapter);
    }
    public void leer() {

        try{
            AssetManager assetMan = getAssets();
            InputStream is = assetMan.open("Pedidos.xml");

            //DOM (Por ejemplo)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(is);

            //A partir de aquí se trataría el árbol DOM como siempre.
            //Por ejemplo:
            //Aviso de lectura de documento
            Log.i(UBICATION, "Archivo abierto | root: "+doc.getDocumentElement().getTagName());

            NodeList listapedidos = doc.getElementsByTagName("pedido");

            for (int i = 0; i < listapedidos.getLength(); i++) {
                Node node = listapedidos.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Pedidos pedido;
                    int  nComercial, codigo, cantidad,precio,imagen;
                    String fechaPedido, fechaPago, fechaEnvio, delegacionProvincial,partner, nPedidos;
                    fechaPedido = element.getElementsByTagName("fecha_pedido").item(0).getTextContent();
                    fechaPago = element.getElementsByTagName("fecha_pago").item(0).getTextContent();
                    fechaEnvio = element.getElementsByTagName("fecha_envio").item(0).getTextContent();
                    delegacionProvincial = element.getElementsByTagName("delegacion_provincial").item(0).getTextContent();
                    partner = element.getElementsByTagName("partner").item(0).getTextContent();
                    nPedidos = element.getElementsByTagName("n_pedido").item(0).getTextContent();
                    try {
                        nComercial = Integer.parseInt(element.getElementsByTagName("n_comercial").item(0).getTextContent());
                    } catch (NumberFormatException enu) {
                        nComercial = 0;
                    }
                    try {
                        codigo = Integer.parseInt(element.getElementsByTagName("codigo").item(0).getTextContent());
                    } catch (NumberFormatException enu) {
                        codigo = 0;
                    }
                    try {
                        cantidad = Integer.parseInt(element.getElementsByTagName("cantidad").item(0).getTextContent());
                    } catch (NumberFormatException enu) {
                        cantidad = 0;
                    }
                    try {
                        precio = Integer.parseInt(element.getElementsByTagName("precio").item(0).getTextContent());
                    } catch (NumberFormatException enu) {
                        precio = 0;
                    }
                    try {
                        imagen = Integer.parseInt(element.getElementsByTagName("imagen").item(0).getTextContent());
                    } catch (NumberFormatException enu) {
                        imagen = 0;
                    }




                    pedido = new Pedidos(nPedidos,fechaPedido,fechaPago, fechaEnvio, nComercial, delegacionProvincial,partner,codigo, cantidad, precio,imagen);
                    pedidos.add(pedido);
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