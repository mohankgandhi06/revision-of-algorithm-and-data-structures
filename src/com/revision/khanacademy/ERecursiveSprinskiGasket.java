package com.revision.khanacademy;

import javax.swing.*;
import java.awt.*;

public class ERecursiveSprinskiGasket {
    public static void main(String[] args) {
        ERecursiveSprinskiGasket game = new ERecursiveSprinskiGasket();
        int size = 640;
        game.draw(size);
    }

    private void draw(int size) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Sprinski Gasket \n-drawn by Mohan Krishna Gandhi S");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.yellow);
        frame.setSize(size + 20, size + 20);
        DrawLine panel = new DrawLine(size, 20);
        frame.add(panel);
        frame.setVisible(true);
    }
}

class DrawLine extends JPanel {
    private int side;
    private int border;

    public DrawLine(int size, int border) {
        this.side = size;
        this.border = border;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.blue);
        this.paintTheWorld(g, border / 2, border / 2, side + (border / 2), side + (border / 2));
    }

    private void paintTheWorld(Graphics g, int x1, int y1, int x2, int y2) {
        /* BASE CASE */
        if (Math.abs(x1 - x2) == 10 && Math.abs(y1 - y2) == 10) {
            this.paintSquare(g, x1, y1, x2, y2);
            return;
        }
        /* REASSIGNMENT */
        int topLeftX2 = (x1 + x2) / 2, topRightX1 = (x1 + x2) / 2, bottomRightX1 = (x1 + x2) / 2;
        int topLeftY2 = (y1 + y2) / 2, topRightY2 = (y1 + y2) / 2, bottomRightY1 = (y1 + y2) / 2;
        paintTheWorld(g, x1, y1, topLeftX2, topLeftY2);
        paintTheWorld(g, topRightX1, y1, x2, topRightY2);
        paintTheWorld(g, bottomRightX1, bottomRightY1, x2, y2);
    }

    private void paintSquare(Graphics g, int x1, int y1, int x2, int y2) {
        for (int i = y1; i <= y2; i++) {
            g.drawLine(x1, i, x2, i);
        }
    }
}