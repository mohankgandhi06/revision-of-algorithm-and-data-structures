package com.revision.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FLambdaChallenge {
    public static void main(String[] args) {
        /* CHALLENGE #1 */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this up into an array";
                String[] parts = myString.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };

        Runnable runnableLambda = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };

        /* CHALLENGE #2 */
        Function<String, String> alternate = (String s) -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };

        StringHelper stringHelper = new StringHelper();
        String result = stringHelper.everySecondCharacter("Selena Gomez");
        System.out.println(result);

        /* CHALLENGE #3 */
        /* Implement the challenge #2 using the input 1234567890 */
        System.out.println("\nChallenge #3 " + alternate.apply("1234567890"));

        /* CHALLENGE #4 and #5 */
        System.out.println("\nChallenge #4 and #5: " + everySecondCharacter(alternate, "1234567890"));

        /* CHALLENGE #6 */
        Supplier<String> iLoveJava = () -> "I Love Java";

        /* CHALLENGE #7 */
        String supplierResult = iLoveJava.get();
        System.out.println("\nChallenge #7: " + supplierResult);

        /* CHALLENGE #8 */
        /* IF java.util.concurrent.Callable AND java.util.Comparator CAN BE USED IN LAMBDA EXPRESSION */

        /* CHALLENGE #9, #10 & #11*/
        List<String> topNames2019 = Arrays.asList(
                "Amelia", "Olivia", "emily", "Isla", "Ava", "oliver", "Jack", "Charlie", "harry", "Jacob"
        );

        Function<String, String> camelCase = (String word) -> Character.toUpperCase(word.charAt(0)) + word.substring(1);
        List<String> sortedNames = topNames2019.stream().map(camelCase).sorted().collect(Collectors.toList());
        System.out.println("\nChallenge #9, #10 & #11");
        for (String name : sortedNames) {
            System.out.println(name);
        }

        /* CHALLENGE #12 */
        Predicate<String> namesStartingWithA = (String name) -> name.toUpperCase().startsWith("A");
        System.out.println("\nChallenge #12: Names starting with the letter A: " + topNames2019.stream().filter(namesStartingWithA).count());

        System.out.println("\nChallenge #13: Names starting with the letter A: ");
        System.out.println(topNames2019.stream().filter(namesStartingWithA).peek(System.out::println).count());
    }

    private static String everySecondCharacter(Function<String, String> function, String source) {
        return function.apply(source);
    }
}

class StringHelper implements StringManipulation {
    @Override
    public String everySecondCharacter(String source) {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }
        return returnVal.toString();
    }
}

@FunctionalInterface
interface StringManipulation {
    public String everySecondCharacter(String source);
}