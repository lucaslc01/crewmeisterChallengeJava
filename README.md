# Crewmeister Test Assignment - Java Backend Developer

## How does it works
 On folder src/main/resources there are a total of 37 .csv archives containing dates per exchange rate for a particular
country in the ratio of 1 EUR per Country's exchange rate. DO NOT EDIT THOSE ARCHIVES or the java buffer reader will not work.

All dates starts at 1999-01-01 and ends at 2022-01-28 for consultation.

## Main project
 On folder src/main/java/com.crewmeister.cmcodingchallenge/APIController we have all URL paths to call the necessary classes
accordingly to user's desire for consulting specific country or rate/date.

 On folder src/main/java/com.crewmeister.cmcodingchallenge/controllerReadCSV we have a class that instantiates a java
buffer reader to access all .csv archives on src/main/resources and them makes specific searches for user.

*Important: when choosing a specific date, type exactly like this format "yyyy-mm-dd" or the search for specific date will not work.
When searching for a specific country, type as shown on URL path ("/allCurrencies") which returns all available countries
on an ArrayList<String>.

## Secondary project (optional)
 The folder src/main/java/com.crewmeister.cmcodingchallenge/generateRates.countries contains java classes capable of 
generating random countries having their own generated dates/rates table (Random numbers).
