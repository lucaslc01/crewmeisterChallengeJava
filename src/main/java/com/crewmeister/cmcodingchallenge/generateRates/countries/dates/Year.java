package com.crewmeister.cmcodingchallenge.generateRates.countries.dates;

import java.util.ArrayList;
import java.util.HashMap;


public class Year {

    //generating a hashmap of a year associated with 12 months
    private HashMap<Integer, ArrayList<Month>> year = new HashMap<Integer, ArrayList<Month>>();

    //declaring one month
    private ArrayList<Month> month;

    public Year() {
        this.generateCurrencyYear();
    }

    //filling the year with 12 months containing 30 days with their respective currency rates
    private void generateCurrencyYear(){
        for(int i = 1; i <= 12; i++){
            this.month = new ArrayList<Month>();

        }
        this.year.put(2022, this.month);
    }

    public HashMap<Integer, ArrayList<Month>> getYear(){
        return this.year;
    }

}
