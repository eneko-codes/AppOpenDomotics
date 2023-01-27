package com.example.appopendomotics.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.appopendomotics.databinding.FragmentHomeBinding;

// Fragmento que muestra la vista de información
public class FragmentHome extends Fragment {

    // Declara un objeto de enlace de datos para el fragmento
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Obtiene una instancia del ViewModelHome para este fragmento
        ViewModelHome homeViewModel =
                new ViewModelProvider(this).get(ViewModelHome.class);

        // Infla el layout del fragmento y crea un objeto de enlace de datos
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        // Obtiene la raíz de la vista del fragmento
        View root = binding.getRoot();

        // Obtiene la instancia del TextView del layout
        final TextView textView = binding.textHome;
        // Establece el texto del TextView al texto del ViewModelHome
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
