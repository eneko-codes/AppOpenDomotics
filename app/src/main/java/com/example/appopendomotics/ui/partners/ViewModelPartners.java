package com.example.appopendomotics.ui.partners;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelPartners extends ViewModel {

    // Declara un MutableLiveData para almacenar el texto que se mostrará en la vista
    private final MutableLiveData<String> mText;

    public ViewModelPartners() {
        // Inicializa el MutableLiveData con un valor por defecto
        mText = new MutableLiveData<>();
        mText.setValue("Esta es la vista de gestión de partners");
    }

    // Devuelve el MutableLiveData como un LiveData
    public LiveData<String> getText() {
        return mText;
    }
}