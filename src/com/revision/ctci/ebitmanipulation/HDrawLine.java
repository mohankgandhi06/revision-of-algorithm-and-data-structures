package com.revision.ctci.ebitmanipulation;

public class HDrawLine {
    public static void main(String[] args) {
        HDrawLine game = new HDrawLine();
        byte[] tv = new byte[ 16 ];
        int width = 4;
        int x1 = 49;
        int x2 = 63;
        int y = 1;
        game.solve(tv, width, x1, x2, y);
        game.show(tv, width);

        System.out.println();
        tv = new byte[ 15 ];
        width = 3;
        x1 = 49;
        x2 = 63;
        y = 1;
        game.solve(tv, width, x1, x2, y);
        game.show(tv, width);

        System.out.println();
        tv = new byte[ 15 ];
        width = 3;
        x1 = 49;
        x2 = 70;
        y = 1;
        game.solve(tv, width, x1, x2, y);
        game.show(tv, width);

        System.out.println();
        tv = new byte[ 15 ];
        width = 3;
        x1 = 48;
        x2 = 51;
        y = 1;
        game.solve(tv, width, x1, x2, y);
        game.show(tv, width);
    }

    private void solve(byte[] tv, int width, int x1, int x2, int y) {
        /* Assumption is that the valid data only will be provided. If not check has to be made before proceeding */
        int startIndex = (x1 / 8) % width;
        int endIndex = (x2 / 8) % width;
        int startPixelIndex = x1 % 8;
        byte startMask = (byte) (0xFF >> startPixelIndex);
        int endPixelIndex = x2 % 8;
        byte endMask = (byte) ~(0xFF >> (endPixelIndex + 1));
        if (startIndex == endIndex) {
            //pixel should be a mix of both mask
            tv[ (y * width) + startIndex ] = (byte) (startMask & endMask);
            return;
        } else {
            tv[ (y * width) + startIndex ] = startMask;
            tv[ (y * width) + endIndex ] = endMask;
        }
        for (int i = startIndex + 1; i < endIndex; i++) {
            tv[ (y * width) + i ] = (byte) 0xFF;
        }
    }

    private void show(byte[] tv, int width) {
        for (int i = 0; i < tv.length; i++) {
            if (i % width == 0) System.out.println();
            System.out.print(appendZeros(tv[ i ]) + " ");
        }
    }

    private String appendZeros(byte pixel) {
        StringBuilder sb = new StringBuilder();
        while (pixel > 0) {
            sb.append("1");
            pixel >>= 1;
        }
        if (pixel < 0) {
            while (pixel != 0) {
                sb.append("1");
                pixel <<= 1;
            }
            int limit = 8 - sb.length();
            for (int i = 0; i < limit; i++) {
                sb.append('0');
            }
        } else {
            int limit = 8 - sb.length();
            for (int i = 0; i < limit; i++) {
                sb.insert(0, '0');
            }
        }
        return sb.toString();
    }
}