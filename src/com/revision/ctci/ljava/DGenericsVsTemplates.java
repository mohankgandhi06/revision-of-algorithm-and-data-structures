package com.revision.ctci.ljava;

public class DGenericsVsTemplates {
    /* Generics */
    /* Type erasure is one of the main variation where the type safety check is performed
     * at compile time in Generics and not in the Template
     * In Generics we use it when we want our program to accept different type of data
     * at runtime and then perform the functions accordingly and we could ensure type safety
     * at compile time when we use generics and then when we instantiate the object we
     * can give the type and then if any other type is given in between other than the
     * one given initially then compile time error is thrown
     * In the Template the code is converted again into template format and created again
     * In Generics it is not like that. */
}