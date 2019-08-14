package com.revision.ctci.ljava;

public class BReturnFromFinally {
    /* Finally */
    /* What ever the case may be the finally block will always be executed before the return to the called function is made */
    public static void main(String[] args) {
        System.out.println("Without Runtime Error:");
        arithmeticWithoutRuntimeError(5, 0);
        System.out.println("\nReturn: ");
        arithmeticWithReturn(5, 6);
        System.out.println("\nWith Runtime Error:");
        arithmeticWithRuntimeError(5, 0);
    }

    private static void arithmeticWithoutRuntimeError(int a, int b) {
        try {
            int c = a / b;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception");
        } finally {
            System.out.println("Finally Block");
        }
    }

    private static void arithmeticWithRuntimeError(int a, int b) {
        try {
            int c = a / b;
        } catch (AbstractMethodError e) {
            System.out.println("Abstract Method Error");
        } finally {
            System.out.println("Finally Block");
        }
    }

    private static void arithmeticWithReturn(int a, int b) {
        try {
            int c = a / b;
            return;
        } catch (AbstractMethodError e) {
            System.out.println("Abstract Method Error");
        } finally {
            System.out.println("Finally Block");
        }
    }
}