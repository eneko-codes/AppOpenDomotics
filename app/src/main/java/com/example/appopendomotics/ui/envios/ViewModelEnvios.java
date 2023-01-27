package com.example.appopendomotics.ui.envios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelEnvios extends ViewModel {

    // Declara un MutableLiveData para almacenar el texto que se mostrará en la vista
    private final MutableLiveData<String> mText;

    public ViewModelEnvios() {
        // Inicializa el MutableLiveData con un valor por defecto
        mText = new MutableLiveData<>();
        mText.setValue("Esta es la vista de envíos a delegación");
    }

    // Devuelve el MutableLiveData como un LiveData
    public LiveData<String> getText() {
        return mText;
    }
}