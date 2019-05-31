package com.example.olxclone.util;

import java.io.Serializable;

public class ReturnFilters implements Serializable {

    private int idState;
    private int idRegion;
    private int idCityZone;
    private int idLocation;
    private boolean ordenationDate;
    private boolean ordenationPrice;
    private float priceMin;
    private float priceMax;
    private boolean typeParticular;
    private boolean typeProfissional;

    public static final long  serialVersionUID = 100L;

    public ReturnFilters(int idState, int idRegion, int idCityZone, int idLocation, boolean ordenationDate, boolean ordenationPrice, float priceMin, float priceMax, boolean typeParticular, boolean typeProfissional) {

        this.idState = idState;
        this.idRegion = idRegion;
        this.idCityZone = idCityZone;
        this.idLocation = idLocation;
        this.ordenationDate = ordenationDate;
        this.ordenationPrice = ordenationPrice;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.typeParticular = typeParticular;
        this.typeProfissional = typeProfissional;
    }

    public int getIdState() {
        return idState;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public int getIdCityZone() {
        return idCityZone;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public boolean isOrdenationDate() {
        return ordenationDate;
    }

    public boolean isOrdenationPrice() {
        return ordenationPrice;
    }

    public float getPriceMin() {
        return priceMin;
    }

    public float getPriceMax() {
        return priceMax;
    }

    public boolean isTypeParticular() {
        return typeParticular;
    }

    public boolean isTypeProfissional() {
        return typeProfissional;
    }
}
