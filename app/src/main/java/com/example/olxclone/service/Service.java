package com.example.olxclone.service;

import com.example.olxclone.entity.CityZone;
import com.example.olxclone.entity.Location;
import com.example.olxclone.entity.Region;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Region> getRegions(int position){

        List<Region> regions = new ArrayList<>();

        switch (position){

            case 1:{}
            break;
            case 2:{}
            break;
            case 3:{}
            break;
            case 4:{}
            break;
            case 5:{}
            break;
            case 6:{}
            break;
            case 7:{

                Region region0 = new Region(0, "Todas as regiões");
                Region region1 = new Region(1, "DDD 81 - Grande Recife");
                Region region2 = new Region(2, "DDD 87 - Petrolina, Garanhuns e região");

                Region[] arrayRegions = {region0, region1, region2};
                regions = new ArrayList<>(Arrays.asList(arrayRegions));
            }
            break;
        }

        return regions;
    }

    public List<CityZone> getCityZone(int id_regions){

        List<CityZone> cityZone = new ArrayList<>();

        //Switch simulando servidor:
        switch (id_regions){

            case 1:{

                CityZone cityZone0 = new CityZone(0, "Todas as cidades/zonas");
                CityZone cityZone1 = new CityZone(1, "Grande Recife");
                CityZone cityZone2 = new CityZone(2, "Outras cidades");
                CityZone cityZone3 = new CityZone(3, "Recife");

                CityZone[] cityZones = {cityZone0, cityZone1, cityZone2, cityZone3};
                cityZone = new ArrayList<>(Arrays.asList(cityZones));
            }
            break;
            case 2:{

                CityZone cityZone0 = new CityZone(0, "Todas as cidades/zonas");
                CityZone cityZone1 = new CityZone(1, "Região de São Francisco Pernambucano");
                CityZone cityZone2 = new CityZone(2, "Região do Agreste Pernambucano");
                CityZone cityZone3 = new CityZone(3, "Região do Sertão Pernambucano");

                CityZone[] cityZones = {cityZone0, cityZone1, cityZone2, cityZone3};
                cityZone = new ArrayList<>(Arrays.asList(cityZones));
            }
            break;
        }

        return cityZone;
    }

    public List<Location> getLocation(int id_cityZone){

        List<Location> locations = new ArrayList<>();

        return locations;
    }
}
