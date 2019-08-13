package com.revision.ctci.ktesting;

public class AMistake {
    public static void main(String[] args) {
        /* Find mistakes in this program */
        /*unsigned int i;
        for (i=100;i>=0;i--){
            printf("%d/n",i);
        }*/

        /* #1: unsigned int will never be less than zero and so the loop will be run infinite times */
        /* #2: %d is for integer and unsigned integer will be %u */
    }
}