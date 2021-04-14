package nl.streeksoft.bit;


import nl.streeksoft.bit.models.*;
import java.util.Scanner;
/**
 * This class handles the user interaction with a few static methods
 */
public class UserInteraction {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Method to ask player name from user
     * @return a String containing the user his player name
     */
    public static String askPlayerName() {
        String playerName = "";
        System.out.println("Geef gebruikersnaam op: ");
        playerName = sc.nextLine();

        return playerName;
    }

    /**
     * Method to ask user for row and column of tile containing the piece which the user wants to move
     *
     * @param c counter, extra text is displayed when the method is called more than once
     * @return when valid values are given by an user, a new Tile containing the row and
     * column is returned. When invalid (not between 0 and 10), null is returned.
     */
    public static Tile askOldTile(int c) {
        if(c > 0) {
            System.out.println("Ongeldige invoer. Probeer opnieuw.");
            sc = new Scanner(System.in);
        }
        int y = -1;
        int x = -1;

        try {
            System.out.println("Geef rij op van dam die je wilt verplaatsen (invoer moet een getal tussen 1 en 10 zijn): ");
            if (sc.hasNextInt())
                y = sc.nextInt();
            System.out.println("Geef kolom op van dam die je wilt verplaatsen (invoer moet een getal tussen 1 en 10 zijn): ");
            if (sc.hasNextInt())
                x = sc.nextInt();
        }
        catch(Exception ex)
        {
            System.out.println("Helaas is er iets misgegaan.");
        }

        Tile oldTile = new Tile((x-1),(y-1));
        if(oldTile.correctValues()) return oldTile;
        return null;
    }
    /**
     * Method to ask user for row and column of tile for new position of a piece.
     *
     * @param c counter, extra text is displayed when the method is called more than once
     * @return when valid values are given by an user, a new Tile containing the row and
     * column is returned. When invalid (not between 0 and 10), null is returned.
     */
    public static Tile askNewTile (int c) {
        if(c > 0) {
            System.out.println("Ongeldige invoer. Probeer opnieuw.");
            sc = new Scanner(System.in);
        }
        int y = -1;
        int x = -1;
        try {
            System.out.println("Geef rij op van de nieuwe positie (invoer moet een getal tussen 1 en 10 zijn): ");
            if (sc.hasNextInt())
                y = sc.nextInt();
            System.out.println("Geef kolom op van de nieuwe positie (invoer moet een getal tussen 1 en 10 zijn): ");
            if (sc.hasNextInt())
                x = sc.nextInt();
        }
        catch(Exception ex) {
            System.out.println("Helaas is er iets misgegaan.");
        }

        Tile newTile = new Tile((x-1),(y-1));
        if(newTile.correctValues()) return newTile;
        return null;
    }
}
