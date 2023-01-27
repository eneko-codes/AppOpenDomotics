package com.example.appopendomotics.ui.envios;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appopendomotics.R;
import com.example.appopendomotics.databinding.FragmentEnviosBinding;
import com.example.appopendomotics.ui.partners.Crear_Partners;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

// Fragmento que muestra la vista de envíos
public class FragmentEnvios extends Fragment {

    // Atributo que contiene la vista del fragmento
    private FragmentEnviosBinding binding;
    // Se inicializa el boton para crear el email
    Button crear,llamar;
    String tele,email;

    // Método que se ejecuta al crear la vista del fragmento
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        // Obtiene una instancia del ViewModel de envíos
        ViewModelEnvios enviosViewModel =
                new ViewModelProvider(this).get(ViewModelEnvios.class);

        // Obtiene la vista del fragmento y la asigna al atributo binding
        binding = FragmentEnviosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtiene el TextView del fragmento y le asigna el texto del ViewModel
        final TextView textView = binding.textEnvios;
        enviosViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Se coje el valor del activity
        crear = (Button) view.findViewById(R.id.BTNenviarEmail);
        llamar = (Button) view.findViewById(R.id.BTNllamar);
        //damos valores a tele y email con la informacion del XML
        leerXMl();

        // Se establece el listener del botón crear, que al ser pulsado abre la actividad Crear_Partners
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Nuevos datos");
                        intent.putExtra(Intent.EXTRA_TEXT, "Aqui te adjunto los nuevos datos.");
                        startActivity(Intent.createChooser(intent, ""));
                    }
                });
            }
        });
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(getContext() != null) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:"+tele));
                            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        } else {
                            Log.e("Error", "No se ha podido obtener el contexto del fragmento.");
                        }
                    }
                    });
            }
        });
    }
    public void leerXMl(){
        //Obtener los datos del xml
        int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
        try {
            AssetManager assetManager = getContext().getAssets();
            InputStream inputStream = assetManager.open("Delegacion.xml");
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inputStream, "UTF-8");
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("telefono")) {
                    tele = parser.nextText();
                    Log.e(tele,"Guarda el telefono");
                } else if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("email")) {
                    email =parser.nextText();
                    Log.e(email,"Guarda el email");
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    // Método que se ejecuta al destruir la vista del fragmento
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}