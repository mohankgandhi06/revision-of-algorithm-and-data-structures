package com.revision.ctci.aarraysandstrings;

public class GRotateMatrix {
    public static void main(String[] args) {
        GRotateMatrix game = new GRotateMatrix();
        char[][] matrix = new char[][]{
                {'a', 'b', 'c', 'd', 'e', 'f'},
                {'g', 'h', 'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p', 'q', 'r'},
                {'s', 't', 'u', 'v', 'w', 'x'},
                {'y', 'z', 'A', 'B', 'C', 'D'},
                {'E', 'F', 'G', 'H', 'I', 'J'}
        };
        game.solve(matrix);

        matrix = new char[][]{
                {'a', 'b', 'c', 'd', 'e'},
                {'g', 'h', 'i', 'j', 'k'},
                {'m', 'n', 'o', 'p', 'q'},
                {'s', 't', 'u', 'v', 'w'},
                {'y', 'z', 'A', 'B', 'C'}
        };
        System.out.println();
        game.solve(matrix);

        matrix = new char[][]{
                {'a', 'b', 'c', 'd', 'e', 'f', '1', '2'},
                {'g', 'h', 'i', 'j', 'k', 'l', '3', '4'},
                {'m', 'n', 'o', 'p', 'q', 'r', '5', '6'},
                {'s', 't', 'u', 'v', 'w', 'x', '7', '8'},
                {'y', 'z', 'A', 'B', 'C', 'D', '9', '0'},
                {'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'},
                {'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'},
                {'U', 'V', 'W', 'X', 'Y', 'Z', '-', '*'}
        };
        System.out.println();
        game.solve(matrix);

        matrix = new char[][]{
                {'a', 'b', 'c', 'd', 'e', 'f', '1'},
                {'g', 'h', 'i', 'j', 'k', 'l', '3'},
                {'m', 'n', 'o', 'p', 'q', 'r', '5'},
                {'s', 't', 'u', 'v', 'w', 'x', '7'},
                {'y', 'z', 'A', 'B', 'C', 'D', '9'},
                {'E', 'F', 'G', 'H', 'I', 'J', 'K'},
                {'M', 'N', 'O', 'P', 'Q', 'R', 'S'}
        };
        System.out.println();
        game.solve(matrix);
    }

    private void solve(char[][] matrix) {
        System.out.println("Actual Matrix");
        show(matrix);
        rotate90Degree(matrix);
        System.out.println("\nRotated Matrix");
        show(matrix);
    }

    private void rotate90Degree(char[][] matrix) {
        int start = 0;
        int end = matrix.length - 1;
        while (start < end) {
            for (int i = start; i < end; i++) {
                char temp = matrix[ start ][ i ];
                matrix[ start ][ i ] = matrix[ end - i + start ][ start ];
                matrix[ end - i + start ][ start ] = matrix[ end ][ end - i + start ];
                matrix[ end ][ end - i + start ] = matrix[ i ][ end ];
                matrix[ i ][ end ] = temp;
            }
            start++;
            end--;
        }
    }

    private void show(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[ 0 ].length; col++) {
                System.out.print(matrix[ row ][ col ] + " ");
            }
            System.out.println();
        }
    }
}

/*
{
        char temp = matrix[ start ][ i ];
        matrix[ start ][ i ] = matrix[ end - i ][ start ];

        matrix[ i ][ start ] = matrix[ end ][ i ];
        matrix[ end ][ i ] = matrix[ end - i ][ end ];

        matrix[ i ][ end ] = temp;
        }*/
