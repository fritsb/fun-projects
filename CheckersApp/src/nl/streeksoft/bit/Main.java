/**
 * CheckersApp: Dammen tegen de computer (unfinished).
 *
 * Features:
 * * Play checkers against CPU
 * * Move pieces
 * * View board
 * * Automatically hit pieces
 *
 * TODO:
 * * Remove automatic hit and let user decide
 * * Implement "backwards" hit
 * * Add multiple hit
 * * Add "Dam" piece type
 * * Finish checkWinner-method
 *
 * Most magic is in CheckersGame class.
 *
 * @author Frits Bosschert/Streeksoft
 */
package nl.streeksoft.bit;

public class Main {

    public static void main(String[] args) {
        CheckersGame cg = new CheckersGame();
        cg.prepareGame();
        cg.startGame();



    }
}
