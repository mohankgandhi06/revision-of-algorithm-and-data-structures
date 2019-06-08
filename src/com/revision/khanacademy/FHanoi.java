package com.revision.khanacademy;

public class FHanoi {
    public static void main(String[] args) {
        FHanoi game = new FHanoi();
        game.solve(5, 'a', 'b', 'c');
    }

    /* FIRST: BASE CASE - When there is plate 1 left move it to the destination */
    /* OTHERWISE: Move the fromRod to auxillary rod the n-1 plates that are above it and then move the
     * below plate to the destination. Once this is done again start placing the n-1 back to the toRod */
    private void solve(int plate, char fromRod, char auxillaryRod, char toRod) {
        if (plate == 1) {
            System.out.println("Plate " + plate + " from " + fromRod + " is moved to " + toRod);
            return;
        }
        solve(plate - 1, fromRod, toRod, auxillaryRod);
        System.out.println("Plate " + plate + " from " + fromRod + " is moved to " + toRod);
        solve(plate - 1, auxillaryRod, fromRod, toRod);
    }
}