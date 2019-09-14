package com.revision.ctci.hrecursionanddynamicprogramming;

public class NBooleanEvaluation {
    public static void main(String[] args) {
        NBooleanEvaluation game = new NBooleanEvaluation();
        String string = "1^0|0|1";
        boolean outcome = false;
        game.solve(string, outcome);

        string = "0&0&0&1^1|0";
        outcome = true;
        game.solve(string, outcome);
    }

    private void solve(String string, boolean outcome) {
        System.out.println("string: [" + string + "] outcome: [" + outcome + "]");
        System.out.println("Combinations: " + evaluate(string, outcome));
    }

    private int evaluate(String string, boolean outcome) {
        if (string.length() == 0) return 0;
        if (string.length() == 1) return string.equalsIgnoreCase("1") == outcome ? 1 : 0;
        int ways = 0;
        for (int i = 1; i < string.length(); i += 2) {//Split the expression into left and right
            String left = string.substring(0, i);
            String right = string.substring(i + 1, string.length());

            int leftTrue = evaluate(left, true);
            int leftFalse = evaluate(left, false);
            int rightTrue = evaluate(right, true);
            int rightFalse = evaluate(right, false);
            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);
            //LEFT(true + false) * RIGHT(true + false)
            //Now for the different condition & | ^ have to find out the true conditions alone
            int trueCondition = 0;
            switch (string.charAt(i)) {
                case '&'://leftTrue * rightTrue
                    trueCondition = leftTrue * rightTrue;
                    break;
                case '|'://leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse
                    trueCondition = leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
                    break;
                case '^'://leftTrue * rightFalse + leftFalse * rightTrue
                    trueCondition = leftTrue * rightFalse + leftFalse * rightTrue;
                    break;
            }
            /* The logic behind the above is that the value or and and exor is based on the following logic
             * and
             * true & true = true
             * false & true = false
             * true & false = false
             * false & false = false
             *
             * or
             * true | true = true
             * false | true = true
             * true | false = true
             * false | false = false
             *
             * exor
             * true ^ true = false
             * false ^ true = true
             * true ^ false = true
             * false ^ false = false
             * */
            int count = outcome ? trueCondition : total - trueCondition;
            ways += count;
        }
        return ways;
    }

    /*
    NOT WORKING - Tried to do a recursive approach
    private void solve(String string, boolean outcome) {
        System.out.println("Evaluate: " + evaluate(string, outcome, 1));
    }

    private int evaluate(String string, boolean outcome, int splitIndex) {
        if (splitIndex >= string.length() && string.length() != 1) return 0;
        if (string.length() == 1 || string.length() == 0) return string.equalsIgnoreCase("1") ? 1 : 0;
        *//* INCLUDE *//*
        String left = string.substring(0, splitIndex);
        String right = string.substring(splitIndex + 1, string.length());
        char operator = string.charAt(splitIndex);
        int count = verify(left, right, operator, outcome) ? 1 : 0;
        int result = evaluate(string.substring(splitIndex + 1), outcome, 1);
        int include = count * result == 0 ? count : count * result;
        *//* EXCLUDE *//*
        int exclude = evaluate(string, outcome, splitIndex + 2);
        return include + exclude;
    }

    private boolean verify(String left, String right, char operator, boolean outcome) {
        boolean leftExpression = expression(left);
        boolean rightExpression = expression(right);
        boolean result = false;
        switch (operator) {
            case '&':
                result = leftExpression & rightExpression;
                break;
            case '|':
                result = leftExpression | rightExpression;
                break;
            case '^':
                result = leftExpression ^ rightExpression;
                break;
            default:
                break;
        }
        return result == outcome;
    }

    private boolean expression(String string) {
        if (string.length() == 1) return 1 == Integer.parseInt(string);
        int result = Integer.parseInt(String.valueOf(string.charAt(0)));
        char operation = string.charAt(1);
        for (int i = 2; i < string.length(); i++) {
            if (i % 2 == 1) {//operator
                operation = string.charAt(i);
            } else {
                switch (operation) {
                    case '&':
                        result = result & Integer.parseInt(String.valueOf(string.charAt(i)));
                        break;
                    case '|':
                        result = result | Integer.parseInt(String.valueOf(string.charAt(i)));
                        break;
                    case '^':
                        result = result ^ Integer.parseInt(String.valueOf(string.charAt(i)));
                        break;
                    default:
                        break;
                }
            }
        }
        return result == 1;
    }

    private int evaluate(String left, String right, char operator, boolean outcome, int splitIndex) {
        if (left.length() == 1) return -1;
        return -1;
    }*/
}