package com.example.appopendomotics.ui.partners;

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
import com.example.appopendomotics.databinding.FragmentPartnersBinding;

// Fragmento que muestra la vista de partners
public class FragmentPartners extends Fragment {

    // Se inicializa el objeto de enlace a la vista de este fragmento
    private FragmentPartnersBinding binding;
    // Se inicializan los botones para crear y ver socios
    Button crear,ver;

    // Al crearse la vista del fragmento, se inicializan los botones y se establecen sus listeners
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Se inicializan los botones
        crear = (Button) view.findViewById(R.id.BTNcrear);
        ver = (Button) view.findViewById(R.id.BTNvisualizar);

        // Se establece el listener del botón crear, que al ser pulsado abre la actividad Crear_Partners
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Crear_Partners.class);
                startActivity(intent);
            }
        });
        // Se establece el listener del botón ver, que al ser pulsado abre la actividad Ver_Partners
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Ver_Partners.class);
                startActivity(intent);
            }
        });
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Se obtiene el viewmodel asociado al fragmento
        ViewModelPartners partnersViewModel =
                new ViewModelProvider(this).get(ViewModelPartners.class);
        // Se obtiene binding asociado al fragmento
        binding = FragmentPartnersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Se obtiene el TextView para mostrar datos
        final TextView textView = binding.textPartners;
        partnersViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Establece el objeto de enlace de datos en null cuando la vista del fragmento es destruida
        // para evitar fugas de memoria
        binding = null;
    }
}