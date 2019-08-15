package com.revision.ctci.ljava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HLambdaRandom {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{4, 7, 2, 1, 6, 8, 9, 12, 16, 14, 17, 3};
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));

        /* Normal static method approach */
        List<Integer> subset = getRandomSubset(list);
        System.out.println("Normal: ");
        subset.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        /* Function in Lambda approach */
        Function<List<Integer>, List<Integer>> getRandomSubset = (List<Integer> li) -> {
            Random random = new Random();
            List<Integer> sub = new ArrayList<>();
            for (Integer i : li) {
                if (random.nextBoolean()) {
                    sub.add(i);
                }
            }
            return sub;
        };
        subset = getRandomSubset.apply(list);
        System.out.println("Lambda with Function: ");
        subset.forEach(integer -> System.out.print(integer + " "));
        System.out.println();

        /* Optimizing the Function */
        Random random = new Random();
        Predicate<Integer> equallyLikely = integer -> random.nextBoolean();
        Function<List<Integer>, List<Integer>> getRandomSubsetOptimized =
                (List<Integer> li) -> li.stream().filter(equallyLikely).collect(Collectors.toList());

        subset = getRandomSubsetOptimized.apply(list);
        System.out.println("Lambda with Function: ");
        subset.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }

    private static List<Integer> getRandomSubset(List<Integer> list) {
        Random random = new Random();
        List<Integer> sub = new ArrayList<>();
        for (Integer i : list) {
            if (random.nextBoolean()) {
                sub.add(i);
            }
        }
        return sub;
    }
}