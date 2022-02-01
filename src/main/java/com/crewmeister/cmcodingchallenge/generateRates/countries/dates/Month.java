package com.crewmeister.cmcodingchallenge.generateRates.countries.dates;

import com.crewmeister.cmcodingchallenge.conversionRates.CurrencyConversionRates;

import java.util.ArrayList;
import java.util.Random;


//generates and return an array list (days) containing a list of all currency rate values for every country/year/month/day
public class Month {

    //generates random currency rates
    private Random randomCurrencyRate = new Random();

    //instantiates a new array of currencyRates in one month (considering 30 days a month)
    private ArrayList<CurrencyConversionRates> month = new ArrayList<CurrencyConversionRates>();

    public Month() {
        this.generateCurrencyMonth();
    }

    //generating random currency rates during a month
    private void generateCurrencyMonth(){
        for(int i = 1; i <= 30; i++){
            int currencyRateDay = randomCurrencyRate.nextInt(1000) + 1;
            this.month.add(new CurrencyConversionRates(currencyRateDay));
        }
    }

    public ArrayList<CurrencyConversionRates> getMonth(){
        return this.month;
    }

}
