package com.revision.ctci.ebitmanipulation;

public class BBinaryToString {
    public static void main(String[] args) {
        BBinaryToString game = new BBinaryToString();
        double d = 0.625;
        game.solve(d);

        d = 0.76;
        game.solve(d);
    }

    private void solve(double d) {
        System.out.println("Input: " + d);
        if (d < 0 || d > 1) {
            System.out.println("out of range (0-1) e.g: 0.56 ");
            return;
        }
        double temp = d;
        StringBuilder binary = new StringBuilder(32);
        int i = 0;
        while (temp > 0 && i <= 31) {
            temp = temp * 2;
            if (temp >= 1) {
                binary.append(1);
                temp = temp - 1;
            } else {
                binary.append(0);
            }
            i++;
        }
        if (i > 31) {
            System.out.println("Error\n");
            return;
        }
        System.out.println("Binary String: " + String.valueOf(binary) + "\n");
    }
}