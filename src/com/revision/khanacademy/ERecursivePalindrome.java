package com.revision.khanacademy;

public class ERecursivePalindrome {
    public static void main(String[] args) {
        ERecursivePalindrome game = new ERecursivePalindrome();
        String input = new String("monam");
        System.out.println("Is " + input + " a palindrome: " + game.isPalindrome(input.toLowerCase()));
    }

    private boolean isPalindrome(String input) {
        if (input.length() == 0 || input.length() == 1) return true;
        return (input.charAt(0) == input.charAt(input.length() - 1)) ? isPalindrome(input.substring(1, input.length()-1)) : false;
    }
}