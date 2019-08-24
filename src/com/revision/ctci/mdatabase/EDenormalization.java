package com.revision.ctci.mdatabase;

public class EDenormalization {
    /* Normalization: It is the process of splitting up of the tables into more
     * meaningful separations as to reduce the redundant data | create a relation
     * between them and separate into a new table [HERE WE CAN HAVE 3 STAGES]
     * example: State Name and Country Name mostly might be same if an application
     * gathers list of details of some specific region, and in that case rather than
     * repeating the country name everywhere and waste more space we can create a
     * relation country_id and refer this country_id in all the places it is needed
     * and when someone needs to know the country name then we go to the table where
     * country_id and country_name is stored and retrieve the same. Also if there is a
     * update in the country name then probably it will be very expensive to affect
     * all the locations which will be easier if normalized
     *
     * Denormalization (Optimization): It is opposite of de-normalization but here the necessity is
     * that when the complex joins becomes expensive as the record is requested more
     * number of times leading to join each and every time and it can be optimized by
     * replacing the id with the name directly or adding the name additionally and use
     * id as well as the situation demands */
}