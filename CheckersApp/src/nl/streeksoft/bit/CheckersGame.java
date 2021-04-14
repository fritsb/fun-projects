package nl.streeksoft.bit;

import nl.streeksoft.bit.models.*;
import java.util.ArrayList;
/**
 * In this class the most magic happens. This class can be improved a lot :-)
 * Lot's of loops and if/else statements which can be put in a method or rewritten in a better way.
 *
 * prepareGame sets up players and tiles
 * startGame contains loop which calls makeMove, checkWinner, switchPlayers and printBoard methods
 */
public class CheckersGame {
    private Board brd;
    private ArrayList<Player> players;
    private GameStatus status = GameStatus.NOTSTARTED;
    private CheckerPiece pieces[];
    private Tile tiles[];
    private Player currentPlayer;

    public CheckersGame() {
        brd = new Board();

    }

    /**
     * This method sets up the game;
     * setupPlayer sets up two players
     * setupTile generates all tiles
     * tiles are added to board and the board is printed to show board to user.
     * currentPlayer is defined using the color
     */
    public void prepareGame() {
        status = GameStatus.PREPARING;
        setupPlayers();
        setupTiles();
        //printTiles();

        brd.setTiles(tiles);
        brd.printBoard();

        if(players != null && players.size() > 1) {
            if(players.get(0).getColor() == CheckColor.WHITE) {
                currentPlayer = players.get(0);
            }
            else {
                currentPlayer = players.get(1);
            }
        }
    }

    /**
     * startGame method contains loop which calls makeMove, checkWinner,
     * switchPlayers and printBoard methods while status of game is equal to STARTED
     */
    public void startGame() {
        this.status = GameStatus.STARTED;

        while(this.status == GameStatus.STARTED) {
            makeMove();
            checkWinner();
            switchPlayer();
            brd.printBoard();
        }
    }

    /**
     * this method is still empty; but the idea was to check if there is a winner or remise.
     */
    public void checkWinner() {

    }

    /**
     * Two players are created and added to players list.
     */
    public void setupPlayers() {
        this.players = new ArrayList<Player>();
        Player player1 = new Player(UserInteraction.askPlayerName(), CheckColor.WHITE, PlayerType.NORMAL);
        Player cpu = new Player("CPU", CheckColor.BLACK,PlayerType.CPU);
        players.add(player1);
        players.add(cpu);
    }

    public Player getCurrentPlayer() {
        if(currentPlayer == null) {
            currentPlayer = players.get(0);
        }
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * This method generates an array of tiles which is used to store game information.
     * The array of tiles is also used by the Board class.
     *
     * The first for rows contains 20 black pieces.
     * The last 4 rows rows contains 20 white pieces.
     */
    public void setupTiles() {
        tiles = new Tile[100];
        int tilesCounter = 0;
        Tile tmpTile = null;

        for(int rows = 0; rows < 10; rows++) {

            for (int cols = 0; cols < 10; cols++) {
                if(cols % 2 != 0 && rows % 2 == 0) {
                    tmpTile = new Tile(cols, rows);
                    if(rows < 4) {
                        CheckerPiece tmpPiece = new CheckerPiece(CheckColor.BLACK);
                        tmpTile.setPiece(tmpPiece);
                    }
                    else if(rows >5) {
                        CheckerPiece tmpPiece = new CheckerPiece(CheckColor.WHITE);
                        tmpTile.setPiece(tmpPiece);
                    }
                }
                else if(cols % 2 == 0 && rows % 2 != 0) {
                    tmpTile = new Tile(cols, rows);
                    if(rows < 4) {
                        CheckerPiece tmpPiece = new CheckerPiece(CheckColor.BLACK);
                        tmpTile.setPiece(tmpPiece);
                    }
                    else if(rows >5) {
                        CheckerPiece tmpPiece = new CheckerPiece(CheckColor.WHITE);
                        tmpTile.setPiece(tmpPiece);
                    }
                }
                else {
                    tmpTile = new Tile(cols, rows);
                }
                tiles[tilesCounter] = tmpTile;
                tilesCounter++;
            }
        }
    }

    /**
     * This method checks if it is possible to hit a piece.
     * It also automatically hits the first piece which is possible to hit.
     * This method was added as last, and can be improved a lot
     * This method contains 3 for-loops which all loop through array with tiles.
     *
     * @return true when hit is found (and pieces are hit), false when there is no hit
     */
    private boolean checkHit() {
        boolean findHit = false;
        for(int i = 0; i < tiles.length;i++) {
            Tile tmp1 = tiles[i];
            if(tmp1 != null && tmp1.getPiece() != null && tmp1.getPiece().getPieceColor() == currentPlayer.getColor()) {
               for(int j = 0; j < tiles.length; j++) {
                    Tile tmp2 = tiles[j];
                    if(tmp2 != null && tmp2.getPiece() != null && tmp2.getPiece().getPieceColor() != currentPlayer.getColor()) {
                        if(currentPlayer.getColor() == CheckColor.WHITE && tmp1.getY() == (tmp2.getY()+1) && tmp1.getX() == (tmp2.getX()+1)) {
                            for(int k = 0; k < tiles.length; k++) {
                                Tile tmp3 = tiles[k];
                                if(tmp3 != null && tmp3.getPiece() == null && tmp1.getY() == (tmp3.getY()+2) && tmp1.getX() == (tmp3.getX()+2) ) {
                                    hitPiece(tmp1,tmp3, tmp2);
                                    printHit(tmp1, tmp3, tmp2);
                                    findHit = true;
                                }
                            }
                        }
                        else if (currentPlayer.getColor() == CheckColor.WHITE && tmp1.getY() == (tmp2.getY()+1) && tmp1.getX() == (tmp2.getX()-1)) {
                            for(int k = 0; k < tiles.length; k++) {
                                Tile tmp3 = tiles[k];
                                if(tmp3 != null && tmp3.getPiece() == null && tmp1.getY() == (tmp3.getY()+2) && tmp1.getX() == (tmp3.getX()-2) ) {
                                    hitPiece(tmp1,tmp3, tmp2);
                                    printHit(tmp1, tmp3, tmp2);
                                    findHit = true;
                                    return findHit;
                                }
                            }
                        }
                        else if(currentPlayer.getColor() == CheckColor.BLACK && tmp1.getY() == (tmp2.getY()-1) && tmp1.getX() == (tmp2.getX()+1)) {
                            for(int k = 0; k < tiles.length; k++) {
                                Tile tmp3 = tiles[k];
                                if(tmp3 != null && tmp3.getPiece() == null && tmp1.getY() == (tmp3.getY()-2) && tmp1.getX() == (tmp3.getX()+2) ) {
                                    hitPiece(tmp1,tmp3, tmp2);
                                    printHit(tmp1, tmp3, tmp2);
                                    findHit = true;
                                    return findHit;
                                }
                            }
                        }
                        else if(currentPlayer.getColor() == CheckColor.BLACK && tmp1.getY() == (tmp2.getY()-1) && tmp1.getX() == (tmp2.getX()-1)) {
                            for(int k = 0; k < tiles.length; k++) {
                                Tile tmp3 = tiles[k];
                                if(tmp3 != null && tmp3.getPiece() == null && tmp1.getY() == (tmp3.getY()-2) && tmp1.getX() == (tmp3.getX()-2) ) {
                                    hitPiece(tmp1,tmp3, tmp2);
                                    printHit(tmp1, tmp3, tmp2);
                                    findHit = true;
                                    return findHit;
                                }
                            }
                        }
                    }
                }
            }
        }
        return findHit;
    }

    /**
     * When a hit is place, this method displays the information in the console.
     *
     * @param oldTile tile containing the piece which is being moved
     * @param newTile tile containing which is the new destination of the piece being moved
     * @param stolenTile tile containing the piece which is stolen/removed.
     */
    private void printHit(Tile oldTile, Tile newTile, Tile stolenTile) {
        System.out.println("Automatisch geslagen, dam van rij " + oldTile.getY1() + ", kolom " +oldTile.getX1()+
                " verplaatst naar rij " + newTile.getY1() + ", kolom "+newTile.getX1() +
                ". Dam van tegenstander op rij "+stolenTile.getY1() + ", kolom " + stolenTile.getX1() + " gestolen.");
    }

    /**
     * This methods moves the piece from the old tile to the new tile, and removes the stolen piece.
     *
     * @param oldTile tile containing the piece which is being moved
     * @param newTile tile containing which is the new destination of the piece being moved
     * @param stolenTile tile containing the piece which is stolen/removed.
     * @return true when piece is succesfully moved and the stolen piece is removed.
     */
    private boolean hitPiece(Tile oldTile, Tile newTile, Tile stolenTile) {
        if(validPosition(oldTile) && checkFreeTile(newTile) ) {
            updateTiles(oldTile,newTile);
            resetTile(stolenTile);
            return true;
        }
        else {
            System.out.println("Ongeldige hit");
        }
        return false;

    }

    /**
     * This method handles the move action from the user player and the cpu player.
     * First automatically is checked if it's possible to hit a piece of the opponent.
     * It will automatically hit the piece to.
     *
     * When no hit is possible: The user is asked to give up information of the piece
     * which he wants to move to a new tile. If this is invalid input or an incorrect move,
     * the user is asked again to give up the information.
     * The CPU player loops trough the tiles and moves the first tile possible.
     */
    public void makeMove (){
        System.out.println(currentPlayer.getName()+" is aan de beurt en speelt met de kleur: " +getCurrentPlayer().getColor().getColorName());
        int c = 0;

        boolean endTurn;
        if(currentPlayer.getType() == PlayerType.NORMAL) {
            endTurn = false;
            while(!endTurn) {
                endTurn = checkHit();
                if(!endTurn) {
                    Tile oldTile = null;
                    do {
                        oldTile = UserInteraction.askOldTile(c);
                        c++;
                    }
                    while (oldTile == null);
                    c = 0;
                    Tile newTile = null;
                    do {
                        newTile = UserInteraction.askNewTile(c);
                        c++;
                    }
                    while (newTile == null);
                    endTurn = movePiece(oldTile, newTile);
                    if (endTurn) {
                        printTurn(oldTile, newTile);
                    }
                }
            }
        }
        else { // CPU
            endTurn = false;
            int counter = 99;
            while(!endTurn) {
                endTurn = checkHit();
                if(!endTurn) {
                    Tile tmpOld = tiles[counter];
                    if (tmpOld != null && tmpOld.getPiece() != null && tmpOld.getPiece().getPieceColor() == currentPlayer.getColor()) {
                        int x = tmpOld.getX() + 1;
                        if (x == 10) {
                            x = 8;
                        }
                        Tile tmpNew = new Tile(x, tmpOld.getY() + 1);
                        endTurn = movePiece(tmpOld, tmpNew);
                        if (endTurn) {
                            printTurn(tmpOld, tmpNew);
                        }
                    }
                    counter--;
                }
            }
        }

        brd.updateBoard(tiles);
    }

    /**
     * This method displays information in console about a piece being moved
     *
     * @param oldTile tile which contains the piece which is being moved
     * @param newTile the new position of the piece being moved
     */
    public void printTurn(Tile oldTile, Tile newTile) {
        System.out.println(currentPlayer.getName() + " verplaatst dam van rij " + oldTile.getY1() +
                ", kolom " + oldTile.getX1() + " naar rij " + newTile.getY1() + ", kolom " + newTile.getX1());

    }

    /**
     * This method checks if it's a valid move. It checks first of the position of
     * the piece is valid and if the new position is empty. With the validMove method it's checked
     * if it's a legal move.
     *
     * @param oldTile tile which contains the piece which is being moved
     * @param newTile the new position of the piece being moved
     * @return
     */
    public boolean movePiece(Tile oldTile, Tile newTile) {
        if(validPosition(oldTile) && checkFreeTile(newTile) && validMove(oldTile,newTile) ) {
            updateTiles(oldTile,newTile);
            return true;
        }
        return false;
    }

    /**
     * This method updates the tiles array with new information.
     *
     * @param oldTile tile which contains the piece which is being moved
     * @param newTile the new position of the piece being moved
     */
    public void updateTiles(Tile oldTile, Tile newTile) {
       // System.out.println("updateTiles" );

        for(int i = 0; i < tiles.length;i++) {
            Tile tmp = tiles[i];
            if(tmp.getX() == oldTile.getX() && tmp.getY() == oldTile.getY() && tmp.getPiece() != null && tmp.getPiece().getPieceColor() == currentPlayer.getColor()) {
                Tile resetTile= new Tile(oldTile.getX(), oldTile.getY());
                tiles[i] = resetTile;
              //  System.out.println("Reset tile" + oldTile );
            }
            if(tmp.getX() == newTile.getX() && tmp.getY() == newTile.getY() ) {
                CheckerPiece piece = new CheckerPiece(currentPlayer.getColor());
                newTile.setPiece(piece);
                tiles[i] = newTile;
             //   System.out.println("Updated tile" + newTile );
            }
        }
    }

    /**
     * This method removes the stolen piece from a tile
     *
     * @param tile tile which contains the piece which is stolen
     */
    public void resetTile(Tile tile) {
        // System.out.println("resetTiles" );

        for(int i = 0; i < tiles.length;i++) {
            Tile tmp = tiles[i];
            if(tmp.getX() == tile.getX() && tmp.getY() == tile.getY() && tmp.getPiece() != null ) {
                Tile resetTile= new Tile(tile.getX(), tile.getY());
                tiles[i] = resetTile;
                //  System.out.println("Reset tile" + tile );
            }
        }
    }

    /**
     * This method checks if the tile is empty (so a new piece can be moved to it).
     * @param freeTile tile that needs to be checked
     * @return true if the tile is empty, false if the tile contains a piece.
     */
    public boolean checkFreeTile(Tile freeTile) {
      //  System.out.println("check free title: " + freeTile);
        boolean free = true;
        for(int i =0; i <tiles.length;i++) {
            Tile tmp = tiles[i];
            if(tmp.getPiece() != null && tmp.getX() == freeTile.getX() && tmp.getY() == freeTile.getY()) free = false;
        }
        return free;
    }

    /**
     * This method checks if the tile is valid and belongs a piece from the current player
     * @param oldTile tile that contains the piece the user wants to move
     * @return true if the tile contains a piece from the current player, false if it's otherwise.
     */
    public boolean validPosition(Tile oldTile) {
     //   System.out.println("validate position: " + oldTile);

        if(oldTile != null && oldTile.correctValues()) {
            for(int i = 0; i < tiles.length;i++) {
                Tile tmp = tiles[i];
                if(tmp != null && tmp.getX() == oldTile.getX() && tmp.getY() == oldTile.getY() && tmp.getPiece() != null && tmp.getPiece().getPieceColor() == currentPlayer.getColor()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks if it's a valid move to move piece from one tile to
     * another tile.
     *
     * @param oldTile tile that contains piece which is being moed
     * @param newTile the new position of the piece being moved
     * @return true when it's a valid move; false when invalid
     */
    public boolean validMove(Tile oldTile, Tile newTile) {
    //    System.out.println("validate move: " + oldTile + newTile);

        if(currentPlayer.getColor() == CheckColor.WHITE && oldTile.getY() == ( newTile.getY() + 1)
                && (oldTile.getX() == (newTile.getX()-1) || oldTile.getX() == (newTile.getX()+1))) {
            return true;
        }
        else if(currentPlayer.getColor() == CheckColor.BLACK && oldTile.getY() == ( newTile.getY() - 1)
                && (oldTile.getX() == (newTile.getX()-1) || oldTile.getX() == (newTile.getX()+1))) {
            return true;
        }
        return false;
    }

    /**
     * Debug method to print all tile information in console
     */
    private void printTiles() {
        for(int i = 0; i < tiles.length; i++) {
            System.out.println(i + ": " + tiles[i]);
        }
    }

    /**
     * Method to switch player after a turn.
     */
    private void switchPlayer() {
        if(this.currentPlayer.equals(this.players.get(0))) {
            this.currentPlayer = this.players.get(1);
        }
        else {
            this.currentPlayer = this.players.get(0);
        }
    }
}
