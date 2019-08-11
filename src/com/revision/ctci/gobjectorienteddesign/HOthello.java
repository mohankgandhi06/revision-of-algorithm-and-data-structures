package com.revision.ctci.gobjectorienteddesign;

public class HOthello {
    public static void main(String[] args) {
        OGame game = new OGame(8, "Matt", "Steve");
        int winner = game.play();
        if (winner == -1) {
            System.out.println("Match Drawn");
        } else {
            System.out.println("Winner is: " + game.getPlayers()[ winner ].getName());
        }
    }
}

class OGame {
    private int size;
    private OPlayer[] players;//0 will be black and 1 will be white
    private OBoard stage;
    private int turn;//0 and 1

    OGame(int size, String black, String white) {
        this.size = size;
        this.players = new OPlayer[]{new OPlayer(size, black), new OPlayer(size, white)};
        this.stage = new OBoard(size);
    }

    /* Returning -1 means it is a draw, 0 means black player won, 1 means white player won */
    public int play() {
        boolean exit = false;
        while (!exit) {
            /* STEP #1: Determine the turn of the player */
            int current = 0;
            /* STEP #2: Ask for the movement, if not able to make move make him pass
             * an update the persons noMoveExists flag and ask the next player */
            this.players[ current ].inHand--;
            /* STEP #3: Once the move is made we have to monitor if it is first a valid move
             * If so then we  check for capturing the pieces */
            /* STEP #4: Capturing the other persons Pieces
             * Go Diagonally and Straight as well and check if there is a
             * first piece if so flip everything in the range */
            this.stage.board[ 0 ][ 0 ].isWhite = !this.stage.board[ 0 ][ 0 ].isWhite;
            this.players[ current ].count++;
            /* STEP #5a: Exit Condition is satisfied go out of the while loop and check the score
             * and determine the winner */
            if ((this.players[ 0 ].inHand == 0 && this.players[ 1 ].inHand == 0) || (this.players[ 0 ].noMoveExists && this.players[ 1 ].noMoveExists)) {
                exit = true;
            }
            /* STEP #5b: Move to next player */
        }
        return this.players[ 0 ].count == this.players[ 1 ].count ? -1 : this.players[ 0 ].count > this.players[ 1 ].count ? 0 : 1;
    }

    /* Getters */
    public int getSize() {
        return size;
    }

    OPlayer[] getPlayers() {
        return players;
    }
}

class OBoard {
    OPiece[][] board;

    OBoard(int size) {
        this.board = new OPiece[ size ][ size ];
    }
}

class OPlayer {
    private String name;
    int inHand;
    int count;
    boolean noMoveExists;

    OPlayer(int size, String name) {
        this.name = name;
        this.inHand = ((size * size) / 2) - 2;
        // -2 is since the initial board two pieces from each person will be kept
        // on the board in the centre
        this.count = 2;
        this.noMoveExists = false;
    }

    public String getName() {
        return name;
    }
}

class OPiece {
    boolean isWhite;
}