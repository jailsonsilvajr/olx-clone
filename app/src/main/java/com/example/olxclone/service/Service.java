package com.example.olxclone.service;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private List<String> categories;

    public List<String> getListCategories(){

        createCategories();
        return this.categories;
    }

    private void createCategories(){

        this.categories = new ArrayList<>();
        String[] array = {"Todas as categorias", "Auto e peças", "Imóveis", "Eletrônicos e celulares",
        "Para a sua casa", "Moda e beleza", "Esportes e lazer", "Música e hobbies",
        "Artigos infantis", "Animais de estimação", "Agro e indústria", "Comécio e escritório",
        "Serviçoes", "Vagas de emprego"};

        for(int i = 0; i < array.length; i++){

            this.categories.add(array[i]);
        }
    }
}
