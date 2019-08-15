package com.revision.ctci.ljava;

import java.util.ArrayList;
import java.util.List;

public class GLambdaExpressions {
    public static void main(String[] args) {
        Planet earth = new Planet("Earth");

        earth.countries.add(new Country("India", "Asia", 2823450));
        earth.countries.add(new Country("Pakistan", "Asia", 88450));
        earth.countries.add(new Country("Sri Lanka", "Asia", 93450));
        earth.countries.add(new Country("China", "Asia", 1623450));
        earth.countries.add(new Country("Myanmar", "Asia", 78480));

        earth.countries.add(new Country("Russia", "Europe", 10340));
        earth.countries.add(new Country("Poland", "Europe", 87340));
        earth.countries.add(new Country("Norway", "Europe", 56930));
        earth.countries.add(new Country("Sweden", "Europe", 45506));
        earth.countries.add(new Country("Finland", "Europe", 85950));
        earth.countries.add(new Country("Denmark", "Europe", 55850));
        earth.countries.add(new Country("Italy", "Europe", 10250));
        earth.countries.add(new Country("Germany", "Europe", 35450));
        earth.countries.add(new Country("France", "Europe", 98505));
        earth.countries.add(new Country("Portugal", "Europe", 56715));

        earth.countries.add(new Country("Congo", "Africa", 85950));
        earth.countries.add(new Country("Tangier", "Africa", 10340));
        earth.countries.add(new Country("Somalia", "Africa", 93450));
        earth.countries.add(new Country("South Africa", "Africa", 98505));
        earth.countries.add(new Country("Tanzania", "Africa", 65672));
        earth.countries.add(new Country("Zimbabwe", "Africa", 78480));

        earth.countries.add(new Country("USA", "North America", 93450));
        earth.countries.add(new Country("Canada", "North America", 78480));
        earth.countries.add(new Country("Mexico", "North America", 35450));
        earth.countries.add(new Country("Cuba", "North America", 23890));
        earth.countries.add(new Country("West Indies", "North America", 88663));

        earth.countries.add(new Country("Argentina", "South America", 85950));
        earth.countries.add(new Country("Brazil", "South America", 93450));
        earth.countries.add(new Country("Peru", "South America", 23890));
        earth.countries.add(new Country("Chile", "South America", 65672));
        earth.countries.add(new Country("Uruguay", "South America", 35450));
        earth.countries.add(new Country("Paraguay", "South America", 88463));

        earth.countries.add(new Country("Australia", "Oceania", 98762));
        earth.countries.add(new Country("New Zealand", "Oceania", 14850));
        earth.countries.add(new Country("Tasmania", "Oceania", 1450));

        earth.countries.add(new Country("No Man's Land", "Antartica", 10));

        String continent = "Oceania";
        System.out.println("Population of " + continent + " is: " + getPopulation(earth.countries, continent));

        System.out.println();
        continent = "North America";
        System.out.println("Population of " + continent + " is: " + getPopulation(earth.countries, continent));

        System.out.println();
        continent = "Asia";
        System.out.println("Population of " + continent + " is: " + getPopulation(earth.countries, continent));

        System.out.println();
        continent = "Europe";
        System.out.println("Population of " + continent + " is: " + getPopulation(earth.countries, continent));
    }

    private static long getPopulation(List<Country> countries, String continent) {
        /* This is for debugging */
        countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .forEach(country -> System.out.println(country.getName() + "        " + country.getPopulation()));

        long population = countries.stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .mapToLong(Country::getPopulation).sum();

        return population;
    }
}

class Planet {
    String name;
    List<Country> countries;

    public Planet(String name) {
        this.name = name;
        this.countries = new ArrayList<>();
    }

    public Planet(String name, List<Country> countries) {
        this.name = name;
        this.countries = countries;
    }
}

class Country {
    private String name;
    private String continent;
    private int population;

    public Country(String name, String continent, int population) {
        this.name = name;
        this.continent = continent;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public int getPopulation() {
        return population;
    }
}