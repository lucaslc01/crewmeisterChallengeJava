package com.crewmeister.cmcodingchallenge.APIController;

import com.crewmeister.cmcodingchallenge.controllerReadCSV.ControllerReadCSV;
import com.crewmeister.cmcodingchallenge.controllerReadCSV.RatePerDate;
import com.crewmeister.cmcodingchallenge.controllerReadCSV.RatePerDateTable;
import com.crewmeister.cmcodingchallenge.generateRates.countries.Countries;
import com.crewmeister.cmcodingchallenge.generateRates.countries.dates.Year;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController()
@RequestMapping("/api")
public class APIController {

    //generating random date/rate per country table (folder generatesRates.countries)
    private final Countries countries = new Countries();

    //instantiating csv reader
    private ControllerReadCSV CSVreader = new ControllerReadCSV();

    //returns all available currencies (countries) compared to EUR-FX
    @GetMapping("/allCurrencies")
    public ResponseEntity<ArrayList<String>> getAvailableCurrencies(){
        return new ResponseEntity<ArrayList<String>>(this.CSVreader.getAvailableCurrencies(), HttpStatus.OK);
    }

    //returns all rates per date of all countries
    @GetMapping("/allCountriesAllDates")
    public ResponseEntity<HashMap<String, RatePerDateTable>> getAllCountriesAllDates(){
        return new ResponseEntity<HashMap<String, RatePerDateTable>>(this.CSVreader.getAllRatesEveryCountry(), HttpStatus.OK);
    }

    //returns all countries' rate for a specific date
    @GetMapping("/countriesDate/{chosenDate}")
    public ResponseEntity<HashMap<String, RatePerDate>> getParticularDayAllCountries(@PathVariable("chosenDate") String chosenDate){
        return new ResponseEntity<HashMap<String, RatePerDate>>(this.CSVreader.getParticularDayAllCountries(chosenDate), HttpStatus.OK);
    }

    //returns an exchange amount of EUR considering an informed a custom amount on foreign currency trading
    @GetMapping("/exchangeAmount")
    public ResponseEntity<Float> getExchangeAmount (float amount, String chosenCountry, String chosenDate){
        return new ResponseEntity<Float>(this.CSVreader.getExchangeAmount(amount, chosenCountry, chosenDate), HttpStatus.OK);
    }

    //returns a list of all available currencies for a random generated exchange rate table (folder generatesRates.countries)
    @GetMapping("/RandomRatesCurrencies")
    public ResponseEntity<ArrayList<String>> getRandomAvailableCurrencies(){
        return new ResponseEntity<ArrayList<String>>(this.countries.getCountryList(), HttpStatus.OK) ;
    }

    //returns all rates of a specific country compared to EUR-FX in a random generated exchange rate table (folder generatesRates.countries)
    @GetMapping("/RandomRatesOneCountryRates")
    public ResponseEntity<ArrayList<Year>> getRandomAllRatesPerDate(String selectedCountry){
        for(int i = 0; i < countries.getCountries().size(); i++){
            if(countries.getCountries().containsKey(selectedCountry)){
                return new ResponseEntity<ArrayList<Year>>(countries.getSpecificCountry(selectedCountry), HttpStatus.OK) ;
            }
        }
        return null;
    }


}
