package com.example.appopendomotics.ui.calendario;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appopendomotics.R;
import com.example.appopendomotics.databinding.FragmentCalendarioBinding;
import com.example.appopendomotics.ui.partners.Crear_Partners;
import com.example.appopendomotics.ui.partners.Ver_Partners;

// Fragmento que muestra la vista de calendarios
public class FragmentCalendario extends Fragment {

    // Declara un objeto FragmentCalendarioBinding para enlazar el layout del Fragment
    private FragmentCalendarioBinding binding;
    // Se inicializan los botones para crear y ver socios
    Button crear,ver;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Crea una instancia del ViewModelCalendario usando el ViewModelProvider
        ViewModelCalendario calendarioViewModel =
                new ViewModelProvider(this).get(ViewModelCalendario.class);

        // Enlaza el layout del Fragment con el objeto FragmentCalendarioBinding
        binding = FragmentCalendarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtiene el TextView del layout y establece su texto usando el valor del LiveData del ViewModel
        final TextView textView = binding.textCalendario;
        calendarioViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Se inicializan los botones
        crear = (Button) view.findViewById(R.id.BTNcrearCitas);
        ver = (Button) view.findViewById(R.id.BTNverCitas);

        // Se establece el listener del botón crear, que al ser pulsado abre la actividad Crear_Partners
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Crear_Citas.class);
                startActivity(intent);
            }
        });
        // Se establece el listener del botón ver, que al ser pulsado abre la actividad Ver_Partners
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Ver_citas.class);
                startActivity(intent);
            }
        });
    }

    // Establece el objeto FragmentCalendarioBinding a null cuando la vista del Fragment es destruida
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
