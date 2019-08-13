package com.revision.ctci.ktesting;

public class CChessTest {
    /* METHOD: boolean canMoveTo(int x, int y) - is a move valid
     * This is method under a Piece class which every different piece has access to
     * Each Piece in turn has different set of rules for its move
     * #1: +ve -ve Cases:
     * $ +ve - Test for each piece with the normal inputs and check if they
     *         give necessary output as true in all the cases
     * $ -ve - Ask them to move to locations that they specifically cannot but
     *         other pieces can. This is just to test if the pieces rules are
     *         obtained properly
     * for example: lets consider a pawn
     *    - it can move one step forward/ two steps only on the first move (i.e. within its armies area half the board)
     *    - it can cut the pieces only in cross not in straight
     *    - there is a concept called en-passe which is a corner case and we need to validate that as well
     * Now we know the rules we can try +ve and check if expected outcome is obtained or an
     * illegal move (like asking it to move backwards) and check if it is handled properly
     *
     * #2: invalid inputs
     * - negative number
     * - like all other pieces each piece cannot step into another piece of same color
     * - asking the piece to move to the same place it already is
     *
     * #3: point the Piece class to a null and call the function. even though technically this is out of scope as
     * it is not related to the method as such and related to a class level test case
     *
     * #4: Illegal Move
     * - King moved to a place were it faces a check
     * */
}