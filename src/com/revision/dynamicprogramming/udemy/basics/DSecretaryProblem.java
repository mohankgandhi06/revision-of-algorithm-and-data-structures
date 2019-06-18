package com.revision.dynamicprogramming.udemy.basics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DSecretaryProblem {
    public static void main(String[] args) {
        System.out.println("How many have applied for the job?: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int applicants = 0;
        String rating = null;
        String[] individual;
        Integer[] idealExpection = new Integer[ 3 ];
        Arrays.fill(idealExpection, 0);
        try {
            applicants = Integer.valueOf(reader.readLine());
            /* Logic is as follows */
            /* Reject first n/e applicants where e = 2.718 while taking the maximum of their values */
            /* Now look for the next candidate who is better than all these */
            /* ****** IMPORTANT ASSUMPTION is that value will be entered without error ****** */
            int rejectionCount = (int) Math.round(applicants / 2.718);

            for (int i = 0; i < rejectionCount; i++) {
                System.out.println("How did this applicant performed out of 10? \nSpoken Skills | General Knowledge | Leadership \n(please enter each category rating with space): ");
                reader = new BufferedReader(new InputStreamReader(System.in));
                rating = reader.readLine();
                individual = rating.split(" ");
                idealExpection[ 0 ] = Math.max(idealExpection[ 0 ], Integer.valueOf(individual[ 0 ]));
                idealExpection[ 1 ] = Math.max(idealExpection[ 1 ], Integer.valueOf(individual[ 1 ]));
                idealExpection[ 2 ] = Math.max(idealExpection[ 2 ], Integer.valueOf(individual[ 2 ]));
            }

            Integer[] best = new Integer[ 3 ];
            String bestCandidate = null;
            for (int j = rejectionCount; j < applicants; j++) {
                System.out.println("Name: ");
                reader = new BufferedReader(new InputStreamReader(System.in));
                String name = reader.readLine();
                System.out.println("How did this applicant performed out of 10? \nSpoken Skills | General Knowledge | Leadership \n(please enter each category rating with space): ");
                reader = new BufferedReader(new InputStreamReader(System.in));
                rating = reader.readLine();
                individual = rating.split(" ");
                if (idealExpection[ 0 ] < Integer.valueOf(individual[ 0 ]) && idealExpection[ 1 ] < Integer.valueOf(individual[ 1 ]) && idealExpection[ 2 ] < Integer.valueOf(individual[ 2 ])) {
                    best[ 0 ] = Integer.valueOf(individual[ 0 ]);
                    best[ 1 ] = Integer.valueOf(individual[ 1 ]);
                    best[ 2 ] = Integer.valueOf(individual[ 2 ]);
                    bestCandidate = name;
                    break;
                }
                idealExpection[ 0 ] = Math.max(idealExpection[ 0 ], Integer.valueOf(individual[ 0 ]));
                idealExpection[ 1 ] = Math.max(idealExpection[ 1 ], Integer.valueOf(individual[ 1 ]));
                idealExpection[ 2 ] = Math.max(idealExpection[ 2 ], Integer.valueOf(individual[ 2 ]));
            }
            if (bestCandidate != null) {
                System.out.println("Best Candidate: " + bestCandidate);
                System.out.println("Spoken Skills: " + best[ 0 ] + " General Knowledge: " + best[ 1 ] + " Leadership: " + best[ 2 ]);
            } else {
                System.out.println("No Ideal Candidate found... ");
            }
        } catch (IOException e) {
            System.out.println("Exception occured while reading please restart");
        }
    }
}