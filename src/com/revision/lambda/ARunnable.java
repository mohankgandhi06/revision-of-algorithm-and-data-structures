package com.revision.lambda;

public class ARunnable {
    public static void main(String[] args) {
        Music musicPlayer = new Music();
        musicPlayer.run();

        Runnable music = () -> {
            System.out.println("\nInside Music Lambda function");
        };
        music.run();

        System.out.println("\nDirect Thread Run: ");
        new Thread(new Music()).start();

        new Thread(() -> {
            System.out.println("\nThread without anonymous class....");
        }).start();


    }
}

class Music implements Runnable {
    public void run() {
        System.out.println("Playing Music");
        System.out.println("...................");
        System.out.println("...................");
        System.out.println("Music Ended");
    }
}