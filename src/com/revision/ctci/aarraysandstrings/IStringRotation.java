package com.revision.ctci.aarraysandstrings;

public class IStringRotation {
    public static void main(String[] args) {
        IStringRotation game = new IStringRotation();
        String inputOne = "waterbottle";
        String inputTwo = "erbottlewat";
        game.solve(inputOne, inputTwo);
    }

    private void solve(String inputOne, String inputTwo) {
        /* IN THIS METHOD ONLY ONE CALL IS ALLOWED TO A METHOD CALL isSubstring WHICH WILL RETURN IF ONE IS AN SUBSTRING OF ANOTHER
         * USING THIS METHOD CALL WE NEED TO DETERMINE IF inputOne IS A ROTATION OF inputTwo LET'S CONSIDER THAT inputOne iS XY AND
         * THE inputTwo is YX. WE CAN CONCATENATE YXYX AND THAT WILL LEAVE US WITH A XY IN-BETWEEN NOW WE CAN PASS IT TO THE SUBSTRING
         * METHOD AND CHECK FOR THE VALIDITY */
        System.out.println("XY: " + inputOne);
        System.out.println("YX: " + inputTwo);
        inputTwo = inputTwo.concat(inputTwo);
        System.out.println("Y(XY)X: " + inputTwo);
        System.out.println("Is inputOne a rotation of inputTwo? " + isSubstring(inputOne, inputTwo));
    }

    private boolean isSubstring(String inputOne, String inputTwo) {
        return inputTwo.contains(inputOne);
    }
}