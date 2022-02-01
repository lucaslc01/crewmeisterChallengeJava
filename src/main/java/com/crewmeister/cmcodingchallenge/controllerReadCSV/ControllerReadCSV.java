package com.crewmeister.cmcodingchallenge.controllerReadCSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ControllerReadCSV {

    // stores all .csv paths from available countries containing tax rates
    private HashMap<String, String> countriesPath = new HashMap<String, String>();

    //stores all countries' names
    private ArrayList<String> allCurrencies;

    private BufferedReader br;
    private String line = "";

    // a table containing all dates/rates per country
    private RatePerDateTable ratesPerDatesTable;
    //variables to return all rates of all countries for each date
    private HashMap<String, RatePerDateTable> allCountriesRate = new HashMap<String, RatePerDateTable>();

    //variables to return exchange rate of all countries in a particular date
    private HashMap<String, RatePerDate> rateParticularDatePerCountry = new HashMap<String, RatePerDate>();
    private RatePerDate specificDateRate;

    public ControllerReadCSV() {

        // filling all .csv paths from available countries containing tax rates
        this.countriesPath.put("Australia", "src/main/resources/BBEX3.D.AUD.EUR.BB.AC.000.csv");
        this.countriesPath.put("Bulgaria", "src/main/resources/BBEX3.D.BGN.EUR.BB.AC.000.csv");
        this.countriesPath.put("Brazil", "src/main/resources/BBEX3.D.BRL.EUR.BB.AC.000.csv");
        this.countriesPath.put("Canada", "src/main/resources/BBEX3.D.CAD.EUR.BB.AC.000.csv");
        this.countriesPath.put("Switzerland", "src/main/resources/BBEX3.D.CHF.EUR.BB.AC.000.csv");
        this.countriesPath.put("China", "src/main/resources/BBEX3.D.CNY.EUR.BB.AC.000.csv");
        this.countriesPath.put("Cyprus", "src/main/resources/BBEX3.D.CYP.EUR.BB.AC.000.csv");
        this.countriesPath.put("Czechia", "src/main/resources/BBEX3.D.CZK.EUR.BB.AC.000.csv");
        this.countriesPath.put("Denmark", "src/main/resources/BBEX3.D.DKK.EUR.BB.AC.000.csv");
        this.countriesPath.put("Estonia", "src/main/resources/BBEX3.D.EEK.EUR.BB.AC.000.csv");
        this.countriesPath.put("United Kingdom", "src/main/resources/BBEX3.D.GBP.EUR.BB.AC.000.csv");
        this.countriesPath.put("Greece", "src/main/resources/BBEX3.D.GRD.EUR.BB.AC.000.csv");
        this.countriesPath.put("Hong Kong", "src/main/resources/BBEX3.D.HKD.EUR.BB.AC.000.csv");
        this.countriesPath.put("Hungary", "src/main/resources/BBEX3.D.HUF.EUR.BB.AC.000.csv");
        this.countriesPath.put("Indonesia", "src/main/resources/BBEX3.D.IDR.EUR.BB.AC.000.csv");
        this.countriesPath.put("Israel", "src/main/resources/BBEX3.D.ILS.EUR.BB.AC.000.csv");
        this.countriesPath.put("India", "src/main/resources/BBEX3.D.INR.EUR.BB.AC.000.csv");
        this.countriesPath.put("Iceland", "src/main/resources/BBEX3.D.ISK.EUR.BB.AC.000.csv");
        this.countriesPath.put("Japan", "src/main/resources/BBEX3.D.JPY.EUR.BB.AC.000.csv");
        this.countriesPath.put("Korea", "src/main/resources/BBEX3.D.KRW.EUR.BB.AC.000.csv");
        this.countriesPath.put("Lithuania", "src/main/resources/BBEX3.D.LTL.EUR.BB.AC.000.csv");
        this.countriesPath.put("Malta", "src/main/resources/BBEX3.D.MTL.EUR.BB.AC.000.csv");
        this.countriesPath.put("Mexico", "src/main/resources/BBEX3.D.MXN.EUR.BB.AC.000.csv");
        this.countriesPath.put("Malaysia", "src/main/resources/BBEX3.D.MYR.EUR.BB.AC.000.csv");
        this.countriesPath.put("Norway", "src/main/resources/BBEX3.D.NOK.EUR.BB.AC.000.csv");
        this.countriesPath.put("New Zealand", "src/main/resources/BBEX3.D.NZD.EUR.BB.AC.000.csv");
        this.countriesPath.put("Philippines", "src/main/resources/BBEX3.D.PHP.EUR.BB.AC.000.csv");
        this.countriesPath.put("Poland", "src/main/resources/BBEX3.D.PLN.EUR.BB.AC.000.csv");
        this.countriesPath.put("Romania", "src/main/resources/BBEX3.D.ROL.EUR.BB.AC.000.csv");
        this.countriesPath.put("Russia", "src/main/resources/BBEX3.D.RUB.EUR.BB.AC.000.csv");
        this.countriesPath.put("Sweden", "src/main/resources/BBEX3.D.SEK.EUR.BB.AC.000.csv");
        this.countriesPath.put("Singapore", "src/main/resources/BBEX3.D.SGD.EUR.BB.AC.000.csv");
        this.countriesPath.put("Slovenia", "src/main/resources/BBEX3.D.SIT.EUR.BB.AC.000.csv");
        this.countriesPath.put("Slovakia", "src/main/resources/BBEX3.D.SKK.EUR.BB.AC.000.csv");
        this.countriesPath.put("Thailand", "src/main/resources/BBEX3.D.THB.EUR.BB.AC.000.csv");
        this.countriesPath.put("Turkey", "src/main/resources/BBEX3.D.TRL.EUR.BB.AC.000.csv");
        this.countriesPath.put("South Africa", "src/main/resources/BBEX3.D.ZAR.EUR.BB.AC.000.csv");

        // stores list of all available currencies
        this.allCurrencies = new ArrayList<String>(countriesPath.keySet());

    }

    //returns all available currencies (country name)
    public ArrayList<String> getAvailableCurrencies() {
        return this.allCurrencies;
    }

    //returns all rates of all countries for each date
    public HashMap<String, RatePerDateTable>  getAllRatesEveryCountry (){

        countriesPath.forEach((key, value) -> {
            //System.out.println("Country: " + key);

            try {
                br = new BufferedReader(new FileReader(value));

                //string counter to ignore non data and non exchange rate
                int StringCounter = 0;
                ratesPerDatesTable = new RatePerDateTable();

                while((line = br.readLine()) != null){

                    if(StringCounter < 9) { //ignoring non data and non exchange rate in .csv files
                        StringCounter++;
                        continue;
                    } else {
                        String[] values = line.split(",");
                        //System.out.println("Date: " + values[0] + " Exchange Rate: " + values[1]); //dates

                        //filling a country's table with all rates and respective dates
                        this.ratesPerDatesTable.setCountryRatesPerDates(values[0], values[1]);
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println("\n");
            //storing country's name for its respective rates/dates table
            this.allCountriesRate.put(key, this.ratesPerDatesTable);

        });

        //returning all rates of all countries for each date
        return this.allCountriesRate;
    }

    //returns exchange rate of all countries in a particular date
    public HashMap<String, RatePerDate> getParticularDayAllCountries(String chosenDate){
        countriesPath.forEach((key, value) -> {
            //System.out.println("Country: " + key);

            try {
                br = new BufferedReader(new FileReader(value));

                //exchange rate of all countries in a particular date
                rateParticularDatePerCountry = new HashMap<String, RatePerDate>();

                //string counter to ignore non data and non exchange rate
                int StringCounter = 0;

                while((line = br.readLine()) != null){

                    if(StringCounter < 9) { //ignoring non data and non exchange rate in .csv files
                        StringCounter++;
                        continue;
                    } else {
                        String[] values = line.split(",");

                        //selecting date chose by user
                        if(chosenDate.equals(values[0])){
                            specificDateRate = new RatePerDate();
                            //System.out.println("Date: " + values[0] + " Exchange Rate: " + values[1]); //dates
                            this.specificDateRate.setDate(values[0]);
                            this.specificDateRate.setRate(values[1]);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println("\n");

            //adding country's name containing chose date and respective rate
            this.rateParticularDatePerCountry.put(key, this.specificDateRate);

        });

        //returning rate of all countries in a particular date
        return this.rateParticularDatePerCountry;
    }

    //returns foreign exchange amount for a given currency converted to EUR on a particular day

    public Float getExchangeAmount(Float amountToConvert, String chosenCountry, String chosenDate){
        for(int i = 0; i < countriesPath.size(); i++){

            //checking if matches the country chose by user
            if(chosenCountry.equals(countriesPath.keySet().toArray()[i])){
                try {
                    br = new BufferedReader(new FileReader(countriesPath.get(countriesPath.keySet().toArray()[i])));

                    //string counter to ignore non data and non exchange rate
                    int StringCounter = 0;

                    while((line = br.readLine()) != null){

                        if(StringCounter < 9) { //ignoring non data and non exchange rate in .csv files
                            StringCounter++;
                            continue;
                        } else {
                            String[] values = line.split(",");

                            // checking if is the right date chosen by user
                            if(chosenDate.equals(values[0])){

                                //calculating amount of money in EUR accordingly to amount of foreign money
                                float rate = Float.parseFloat(values[1]);
                                float total = rate * amountToConvert;
                                return total;
                                //System.out.println("Date: " + values[0] + " tax: " + values[1]);
                                //System.out.println(key + " amount " + amount + " converted to EUR is = " + total);
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println("\n");
            }
        }
        return null;
    }


}
