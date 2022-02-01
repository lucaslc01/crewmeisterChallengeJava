package com.crewmeister.cmcodingchallenge.generateRates.countries;

import com.crewmeister.cmcodingchallenge.generateRates.countries.dates.Year;

import java.util.ArrayList;
import java.util.HashMap;

public class Countries {

    //generating a hashmap of a country associated with a year
    private HashMap<String, ArrayList<Year>> countries = new HashMap<String, ArrayList<Year>>();

    //declaring one year
    private ArrayList<Year> years;

    private ArrayList<String> countryList;

    public Countries(){
        this.generateCountries();
    }

    //generating countries' current tax rate according to year
    private void generateCountries(){
        this.years = new ArrayList<Year>();
        this.countries.put("Brazil", this.years);
        this.years = new ArrayList<Year>();
        this.countries.put("Australia", this.years);
        this.years = new ArrayList<Year>();
        this.countries.put("japan", this.years);
        this.years = new ArrayList<Year>();
        this.countries.put("Russia", this.years);
        this.years = new ArrayList<Year>();
        this.countries.put("Greece", this.years);
        this.years = new ArrayList<Year>();
        this.countries.put("Israel", this.years);
        this.years = new ArrayList<Year>();
        this.countries.put("United States", this.years);
        this.years = new ArrayList<Year>();
        this.countries.put("India", this.years);
        this.years = new ArrayList<Year>();
        this.countries.put("Poland", this.years);
        this.years = new ArrayList<Year>();
        this.countries.put("South Africa", this.years);

        this.countryList = new ArrayList<String>(countries.keySet());
    }

    //return list of all available currencies
    public ArrayList<String> getCountryList(){
        return this.countryList;
    }

    public HashMap<String, ArrayList<Year>> getCountries() {
        return countries;
    }

    public ArrayList<Year> getSpecificCountry(String selectedCountry){
        for(int i = 0; i < countries.size(); i++){
            if(countries.containsKey(selectedCountry)){
                return countries.get(selectedCountry);
            }
        }
        //no available country
        return null;
    }


}
