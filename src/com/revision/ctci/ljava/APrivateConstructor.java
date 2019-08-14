package com.revision.ctci.ljava;

public class APrivateConstructor {
    /* Access Specifier                                                   (Can't use in class)                  */
    /*                                              Private      Default        Protected        Public         */
    /* Within class and same package                   Y            Y               Y               Y
     * Within sub-class and same package               N            Y               Y               Y
     * Within sub-class but outside package            N            N               Y               Y
     * Outside class and outside package               N            N               N               Y           */

    /* Private keyword is usually for preventing unwanted access from outside which can be used when sensitive data
     * is needed. Also when we require the user to be not worried about the implementation as such but only needs
     * some process to be taken care like a internal working of a gear box is not required for a person who drives
     * the car. Rather he can know about the working alone comes in the form of encapsulation and data hiding.
     * Although both the terms seems to be used interchangeably there is a subtle difference
     * Encapsulation: Hiding the complexity of the implementation from the user so that they can worry about
     * only their implementation with that.
     * Data Hiding: We are literally hiding the state (data) and prevent it from access outside for security
     * reasons mainly
     *
     * In our scenario we are achieving encapsulation by creating the constructor as private. we can also use it for
     * creating Singleton pattern for creating only one instance of a class. As long as the Constructor is private
     * and the class is not static we can be sure that we have achieved encapsulation */
}