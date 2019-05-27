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

    public List<String> getStates(){

        List<String> states = new ArrayList<>();
        String[] array = {"Todos os estados", "São Paulo", "Minas Gerais", "Rio de Janeiro", "Bahia",
        "Rio Grande do Sul", "Paraná", "Pernambuco", "Ceará", "Pará", "Maranhão", "Santa Catarina", "Goiás",
        "Paraíba", "Espírito Santo", "Amazonas", "Alagoas", "Piauí", "Rio Grande do Norte", "Mato Grosso",
        "Distrito Federal", "Mato Grosso do Sul", "Sergipe", "Rondônia", "Tocantins", "Acre", "Amapá", "Roraima"};

        for(int i = 0; i < array.length; i++){

            states.add(array[i]);
        }

        return states;
    }
}
