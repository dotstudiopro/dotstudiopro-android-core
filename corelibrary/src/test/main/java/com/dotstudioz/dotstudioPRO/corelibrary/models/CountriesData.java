package com.dotstudioz.dotstudioPRO.corelibrary.models;

public class CountriesData {


    private Countries[] countries;

    public Countries[] getCountries ()
    {
        return countries;
    }

    public void setCountries (Countries[] countries)
    {
        this.countries = countries;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [countries = "+countries+"]";
    }
}

