package com.revision.ctci.gobjectorienteddesign;

public class JMinesweeper {
    public static void main(String[] args) {
        MBattlefield minesweeper = new MBattlefield("Alec Baldwin", 7, 3, new int[][]{{1, 2}, {3, 2}, {6, 4}});
        boolean result = minesweeper.start();
        System.out.println(result ? "You have won..." : "Bomb Exploded");
        if (!result) {
            System.out.println("   GAME OVER   ");
            System.out.println("    _|||||_    ");
            System.out.println("    | X X |    ");
            System.out.println("    |  -  |    ");
            System.out.println("    ^^^^^^^    ");
        } else {
            System.out.println("   YOUR SCORE  ");
            System.out.println("   $100000000  ");
            System.out.println("    _|||||_    ");
            System.out.println("    | $ $ |    ");
            System.out.println("    |  O  |    ");
            System.out.println("    ^^^^^^^    ");
        }
    }
}

class MBattlefield {
    private MPlayer player;
    private int size;
    private int bombs;
    private MSquare[][] squares;
    private int[][] surroundingBlocks = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };
    private int squaresLeft;

    MBattlefield(String player, int size, int bombs, int[][] location) {
        this.player = new MPlayer(player);
        this.size = size;
        this.bombs = bombs;
        this.squares = new MSquare[ size ][ size ];
        initializeSquares();
        for (int[] coordinate : location) {
            this.squares[ coordinate[ 0 ] ][ coordinate[ 1 ] ].minesNearby = -1;
            for (int[] surrounding : surroundingBlocks) {
                if (notOutOfBound(coordinate[ 0 ] + surrounding[ 0 ], coordinate[ 1 ] + surrounding[ 1 ])) {
                    this.squares[ coordinate[ 0 ] + surrounding[ 0 ] ][ coordinate[ 1 ] + surrounding[ 1 ] ].minesNearby++;
                }
            }
        }
        this.squaresLeft = size * size;
        /*show();*/
    }

    private void initializeSquares() {
        for (int row = 0; row < this.squares.length; row++) {
            for (int column = 0; column < this.squares[ 0 ].length; column++) {
                this.squares[ row ][ column ] = new MSquare(row, column);
            }
        }
    }

    private boolean notOutOfBound(int x, int y) {
        return !(x < 0 || x >= this.squares.length || y < 0 || y >= this.squares[ 0 ].length);
    }

    private void show() {
        for (MSquare[] row : this.squares) {
            for (MSquare column : row) {
                System.out.print((column.minesNearby != -1 ? column.minesNearby : "X") + " ");
            }
            System.out.println();
        }
    }

    public boolean start() {
        boolean exit = false;
        while (!exit) {
            int x = 0;
            int y = 0;
            /* STEP #1: Get input from the user */
            this.squares[ x ][ y ].visited = true;
            /* STEP #2a: Check if we stepped on a bomb. if so return false */
            if (steppedOnBomb(x, y)) return false;
            /* STEP #2b: Check if we stepped on a empty. if so then start a queue
             * and open all the places until we reach at least a positive number */
            if (steppedOnEmpty(x, y)) keepExploring();
            /* STEP #3: Check if we have completed all the squares.
             * if so exit the game and return true
             * else continue with the game and ask user input */
            if (squaresLeft == 0) exit = true;
        }
        return true;
    }

    private boolean steppedOnBomb(int x, int y) {
        return this.squares[ x ][ y ].minesNearby == -1;
    }

    private boolean steppedOnEmpty(int x, int y) {
        return this.squares[ x ][ y ].minesNearby == 0;
    }

    private void keepExploring() {

    }

    /* Getters */
    public MPlayer getPlayer() {
        return player;
    }

    public int getSize() {
        return size;
    }

    public int getBombs() {
        return bombs;
    }

    public MSquare[][] getSquares() {
        return squares;
    }

    public int[][] getSurroundingBlocks() {
        return surroundingBlocks;
    }

    public int getSquaresLeft() {
        return squaresLeft;
    }
}

class MPlayer {
    private String name;
    int score;

    public MPlayer(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }
}

class MSquare {
    /* -1 means it is a mine
     * 0 means no mine around
     * 1 means one mine in the eight square surrounding it
     * 2... a square can have the value between -1 to 8 */
    int minesNearby;
    private int x;
    private int y;
    boolean visited;

    MSquare(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}