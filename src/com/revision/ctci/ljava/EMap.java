package com.revision.ctci.ljava;

public class EMap {
    /* HashMap, Linked HashMap, TreeMap */
    /* When we compare the three all of it have the Map as a common which means it uses
     * Key Value pair concept of storing the data. And so let's check how it differs for
     * each of the Implementation
     *
     * HashMap - Hash (Key is hashed for easier retrieval)+ Map. When hashing we will store
     * the values in the form of array of Linked List and the array index is found out using
     * the hashing technique and if collision occur then the linked list will solve the issue
     * Order is not maintained. Easy retrieval in unit time (if collision is kept minimum
     * using good hashing technique)
     * Real time example:
     * HashMap is the best if the below features are not required i.e. natural | insertion order
     * as there is no overhead in the plain HashMap
     *
     * Linked HashMap - Doubly Linked List is used to hold the Keys in order of the insertion
     * in addition to the Hashing technique discussed earlier to find and insert new in unit
     * time.
     * Real time example: In Caching the order is needed to find the Least Recently Used and
     * then decide which one to remove
     *
     * TreeMap - Tree will provide look up time complexity (log n) and the Key order will be
     * the natural order
     * Real time example: when the alphabetical order of preservation is needed then we can
     * implement in the form of tree. We will have some additional functions like next 10
     * which is particular for More... functions in the application which gets the answer
     * only when required and quickly too */
}