package com.example.appopendomotics.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelHome extends ViewModel {

    // Declara un objeto MutableLiveData para almacenar el texto a mostrar en la vista
    private final MutableLiveData<String> mText;

    public ViewModelHome() {
        // Inicializa el objeto MutableLiveData con un valor por defecto
        mText = new MutableLiveData<>();
    }

    // Devuelve el objeto MutableLiveData como un LiveData para que pueda ser observado por FragmentHome.java
    public LiveData<String> getText() {
        return mText;
    }
}
