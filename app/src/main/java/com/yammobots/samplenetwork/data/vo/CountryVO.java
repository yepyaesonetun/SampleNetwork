package com.yammobots.samplenetwork.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public class CountryVO {

    @SerializedName("currencies")
    private ArrayList<CurrencyVO> currencies;

    @SerializedName("flag")
    private String flag;

    @SerializedName("name")
    private String name;

    @SerializedName("capital")
    private String capital;

    @SerializedName("population")
    private String population;

    public ArrayList<CurrencyVO> getCurrencies() {
        if (currencies == null) {
            return new ArrayList<>();
        }
        return currencies;
    }

    public void setCurrencies(ArrayList<CurrencyVO> currencies) {
        this.currencies = currencies;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
