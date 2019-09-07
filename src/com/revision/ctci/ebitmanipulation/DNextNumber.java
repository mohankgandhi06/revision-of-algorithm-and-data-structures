package com.revision.ctci.ebitmanipulation;

public class DNextNumber {
    public static void main(String[] args) {
        DNextNumber game = new DNextNumber();
        int number = 3;
        game.solve(number);
        number = 6;
        game.solve(number);
        number = 11;
        game.solve(number);
        number = 23;
        game.solve(number);
        number = 24;
        game.solve(number);
        number = 44;
        game.solve(number);
        number = 46;
        game.solve(number);
    }

    private void solve(int number) {
        System.out.println("\n" + number);
        /* Next Biggest */
        int biggest = number;
        int index = findFirstNonTrailingZeroIndex(biggest);
        if (index < 0) {
            System.out.println("Not Possible");
            return;
        }
        int mask = 1 << index;
        biggest |= mask;//Flipped a non trailing zero to one
        int invert = mask - 1;
        int extract = biggest & invert;//Extract of the bits right of flipped bit
        biggest &= ~invert;//Clearing the bits before adding with the moved bits
        boolean flag = false;
        while (!flag) {
            if ((extract & 1) == 1) {
                flag = true;
                extract >>= 1;
                continue;
            }
            extract >>= 1;
        }
        biggest |= extract;
        System.out.println("Next biggest: " + biggest);

        /* Next Smallest */
        int smallest = number;
        int[] smallestIndex = findIndexOfFirstOneAfterAZero(smallest);
        if (smallestIndex[ 0 ] < 0) {
            System.out.println("Not possible");
            return;
        }
        int smallestMask = ~(1 << smallestIndex[ 0 ]);
        smallest &= smallestMask;//Flipped a non trailing one to zero
        int extractMask = (1 << (smallestIndex[ 0 ])) - 1;
        int c1 = smallest & extractMask;
        int clearMask = ~(extractMask);
        smallest &= clearMask;//Reset the right side
        c1 = (c1 << 1) + 1;
        c1 <<= ((smallestIndex[ 0 ] - smallestIndex[ 2 ]) - 1);
        smallest |= c1;
        System.out.println("Next smallest: " + smallest);
    }

    private int findFirstNonTrailingZeroIndex(int number) {
        boolean flag = false;
        int index = 0;
        while (number > 0) {
            if ((number & 1) == 1 && !flag) {
                flag = true;
            } else if (flag && (number & 1) == 0) {
                return index;
            }
            index++;
            number >>= 1;
        }
        return index++;
    }

    private int[] findIndexOfFirstOneAfterAZero(int number) {
        int[] result = new int[ 3 ];//[0] - Index, [1] - Zeros, [2] - Ones
        int mask = 1;
        boolean flag = false;
        int index = 0;
        while (number > 0) {
            if ((number & mask) == 1) {
                if (flag) {
                    result[ 0 ] = index;
                    return result;
                }
                result[ 2 ]++;
            } else if ((number & mask) == 0) {
                if (!flag) {
                    flag = true;//first 0
                }
                result[ 1 ]++;
            }
            index++;
            number >>= 1;
        }
        result[ 0 ] = -1;
        return result;
    }
}