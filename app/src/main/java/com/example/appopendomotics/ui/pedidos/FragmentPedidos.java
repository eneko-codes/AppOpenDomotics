package com.example.appopendomotics.ui.pedidos;

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
import com.example.appopendomotics.databinding.FragmentPedidiosBinding;
import com.example.appopendomotics.ui.partners.Crear_Partners;
import com.example.appopendomotics.ui.partners.Ver_Partners;

// Fragmento que muestra la vista de pedidos
public class FragmentPedidos extends Fragment {

    // Se inicializa el objeto de enlace a la vista de este fragmento
    private FragmentPedidiosBinding binding;

    // Se inicializan los botones para crear y ver pedidos
    Button crear,ver;

    // Método que se ejecuta al crear la vista del fragmento
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        // Obtiene una instancia del ViewModel de pedidos
        ViewModelPedidos pedidosViewModel =
                new ViewModelProvider(this).get(ViewModelPedidos.class);

        // Obtiene la vista del fragmento y la asigna al atributo binding
        binding = FragmentPedidiosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtiene el TextView del fragmento y le asigna el texto del ViewModel
        final TextView textView = binding.textPedidos;
        pedidosViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Se inicializan los botones
        crear = (Button) view.findViewById(R.id.BTNcrearpedido);
        ver = (Button) view.findViewById(R.id.BTNver2);

        // Se establece el listener del botón crear, que al ser pulsado abre la actividad Crear_Partners
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Crear_Pedidos.class);
                startActivity(intent);
            }
        });
        // Se establece el listener del botón ver, que al ser pulsado abre la actividad Ver_Partners
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Ver_pedidos.class);
                startActivity(intent);
            }
        });
    }

    // Método que se ejecuta al destruir la vista del fragmento
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}