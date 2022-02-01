package com.crewmeister.cmcodingchallenge.controllerReadCSV;

import java.util.HashMap;

public class RatePerDateTable {

    //contains a table of dates and rates for a specific country
    private HashMap<String, String> CountryRatesPerDates = new HashMap<String, String>();

    public void setCountryRatesPerDates(String date, String rate) {
        this.CountryRatesPerDates.put(date, rate);
    }

    public HashMap<String, String> getCountryRatesPerDates() {
        return CountryRatesPerDates;
    }
}
