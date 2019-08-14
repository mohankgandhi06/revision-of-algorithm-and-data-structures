package com.revision.ctci.ljava;

public class CFinalEtc {
    /* final | finally | finalize */
    /* final: keyword
     * final can be used for a variable, method and a class
     * variable: will become unmodifiable and final when it is declared as final.
     * global constants are mostly created with static and a final keyword
     * method: when a method is declared as a final it means it cannot be overridden by
     * subclass methods
     * class: when we declare class as final we cannot extend it and one classic example
     * is String class which is a final class
     *
     * finally: block or method
     * it is a method which is used together with the try catch block. Mainly we use the
     * finally method to make it execute no matter what happens within both try and catch
     * before it returns to the called function it is supposed to execute the finally method
     * Even when there is an unchecked exception (runtime) it will throw the exception into
     * the console only after it has executed the finally block before
     * mainly the finally keyword is used to resource de allocation and freeing up
     *
     * finalize: called by a Garbage Collector. Not a reserved keyword
     * This is a method in the Object class and used by the Garbage Collector to perform clean
     * up activity before the collection. It has not immediately garbage collected. just that
     * it is made ready for the process.
     * */
}