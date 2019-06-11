package com.revision.khanacademy;

public class ERecursivePowers {
    public static void main(String[] args) {
        ERecursivePowers game = new ERecursivePowers();
        int number = 50;
        int power = 3;
        System.out.println(number + " power " + power + " is: " + game.solve(number, power));
    }

    private float solve(int number, int power) {
        if (power >= 0) return powerOf(number, power);
        return 1 / (powerOf(number, Math.abs(power)));
    }

    private float powerOf(int number, int power) {
        if (power == 0) return 1;
        if (power == 1) return number;
        if (power % 2 == 0) {
            float temp = powerOf(number, power / 2);
            return temp * temp;
        } else {
            return powerOf(number, power - 1) * number;
        }
    }
}