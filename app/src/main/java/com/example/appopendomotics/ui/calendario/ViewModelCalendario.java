package com.example.appopendomotics.ui.calendario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelCalendario extends ViewModel {

    // Declara un MutableLiveData para almacenar el texto que se mostrará en la vista
    private final MutableLiveData<String> mText;

    public ViewModelCalendario() {
        // Inicializa el MutableLiveData con un valor por defecto
        mText = new MutableLiveData<>();
        mText.setValue("Esto es la vista de calendario de citas");
    }

    // Devuelve el MutableLiveData como un LiveData
    public LiveData<String> getText() {
        return mText;
    }
}
