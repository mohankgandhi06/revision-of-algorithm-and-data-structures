package com.revision.ctci.gobjectorienteddesign;

import java.util.ArrayList;
import java.util.HashMap;

public class FJigSaw {
    public static void main(String[] args) {
        Answer answer = new Answer(new Piece[][]{
                {new Piece(1, 0, 1, 0, -1), new Piece(2, -1, 1, 0, -1),
                        new Piece(3, -1, -1, 0, -1), new Piece(4, 1, 0, 0, 1)},
                {new Piece(5, 0, 1, 1, 1), new Piece(6, -1, -1, 1, 1),
                        new Piece(7, 1, -1, 1, 1), new Piece(8, 1, 0, -1, 1)},
                {new Piece(9, 0, -1, -1, 1), new Piece(10, 1, -1, -1, 1),
                        new Piece(11, 1, -1, 1, -1), new Piece(12, -1, 0, -1, 1)},
                {new Piece(13, 0, -1, -1, 0), new Piece(14, 1, 1, -1, 0),
                        new Piece(15, -1, -1, 1, 0), new Piece(16, 1, -1, 0, 0)}
        });

        Puzzle puzzle = new Puzzle(answer);
        puzzle.solve();
    }
}

class Puzzle {
    private Answer answer;
    private Board board;
    private ArrayList<Piece> pieces;
    /* We can have 2 | 3 | 4 irregular sided pieces in a normal puzzle */
    private ArrayList<HashMap<String, ArrayList<Piece>>> bucket;

    Puzzle(Answer answer) {
        this.answer = answer;
        this.board = new Board(answer.getPieces().length);
        this.pieces = addAndShufflePieces(answer);
    }

    private ArrayList<Piece> addAndShufflePieces(Answer answer) {
        ArrayList<Piece> pieces = new ArrayList<>(answer.getPieces().length * answer.getPieces().length);
        for (int row = 0; row < answer.getPieces().length; row++) {
            for (int col = 0; col < answer.getPieces()[ 0 ].length; col++) {
                pieces.add(answer.getPieces()[ row ][ col ]);
            }
        }
        shuffle(pieces);
        return pieces;
    }

    private void shuffle(ArrayList<Piece> pieces) {
        /* Logic to shuffle the pieces */
    }

    /* ASSUMPTION #1: Lets just assume that in only one way we can solve the puzzle
     * and not any other positioning is possible of the pieces
     * ASSUMPTION #2: We can only check for the validity of the puzzle only after
     * all the pieces are placed */
    public void solve() {
        firstPass();
        secondPass();
    }

    private void firstPass() {
        /* Place the pieces in the buckets based on the +1, 0 ,-1 of the edges
         * for example:
         *  top left possible piece types (0,1,0,-1), (0,1,0,1), (0,-1,0,-1), (0,-1,0,1)
         *  top right possible piece types (-1,0,0,-1), (1,0,0,1), (-1,0,0,1), (1,0,0,-1)
         *  Likewise we have to create for all the possible 2, 3, 4 sided pieces. Even though
         *  this process may seem unnecessary for smaller dimensions it will help in higher dimension */
    }

    private void secondPass() {
        /* APPROACH #1: Recursive Back Tracking */
        if (!checkAnswer()) {
            /* Back Track are go for other scenarios */
        }
        System.out.println("Puzzle Solved");

        /* APPROACH #2: Solve the outer edges first then come to the inner square and repeat until the inner most is complete */
    }

    private boolean checkAnswer() {
        for (int row = 0; row < answer.getPieces().length; row++) {
            for (int col = 0; col < answer.getPieces()[ 0 ].length; col++) {
                if (this.getAnswer().getPieces()[ row ][ col ] != this.getBoard().getPieces()[ row ][ col ]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean fitsWith(Piece pieceOne, Piece pieceTwo) {
        /* Do we need to look at all possible position for a possible fit */
        /* This is the edge piece it will not have anything
         * either to the left | right | top | bottom */

        return false;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Board getBoard() {
        return board;
    }
}

class Board {
    private Piece[][] pieces;

    Board(int size) {
        this.pieces = new Piece[ size ][ size ];
    }

    Piece[][] getPieces() {
        return pieces;
    }
}

class Piece {
    private int id;
    /* +1(Protruding), 0, -1(Intruding) */
    private int left;
    private int right;
    private int top;
    private int bottom;

    Piece(int id, int left, int right, int top, int bottom) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public int getId() {
        return id;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }
}

class Answer {
    private Piece[][] pieces;

    Answer(Piece[][] pieces) {
        this.pieces = pieces;
    }

    Piece[][] getPieces() {
        return pieces;
    }
}