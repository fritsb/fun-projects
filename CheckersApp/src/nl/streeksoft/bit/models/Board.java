package nl.streeksoft.bit.models;

/**
 *  The Board class represents the Checker Board.
 *  It contains an array of tiles, an update method, a print method and most important
 *  the getBoard-method which returns a String containing the board, tiles and pieces.
 */
public class Board {
    private Tile[] tiles;

    /**
     * The board only needs to update tiles to be updated
     */
    public void updateBoard(Tile[] tiles) {
        this.setTiles(tiles);
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    /**
     * This method returns the board to show in a console application. It loops trough all tiles and
     * add pieces to a tile.
     *
     * @return a String that represents the board.
     */
    private String getBoard() {
        int counter = 0;
        StringBuilder str = new StringBuilder();
        if(tiles != null & tiles.length == 100) {
            str.append("    ");
            for(int cols = 0; cols < 10;cols++) {
                str.append("    " + (cols+1) + ":  ");
            }
            str.append("\n");

            for (int rows = 0; rows < 10; rows++) {
                str.append((rows+1) + ": ");
                if(rows != 9) str.append(" ");
                str.append("| ");
                for(int cols = 0; cols < 10; cols++) {
                    if((cols + rows) % 2 != 0) {
                        str.append("[ ");
                        if(tiles[counter] != null && tiles[counter].getPiece() != null) {
                            str.append(tiles[counter].getPiece().getCode());
                        }
                        else { str.append(" "); }
                        str.append(" ]");
                    }
                    else {
                        str.append("     ");
                    }
                    str.append(" | ");
                    counter++;
                }
                str.append("\n");
            }
        }
        return str.toString();
    }

    /**
     * printBoard() prints the output of the getBoard-method
     */
    public void printBoard() {
        System.out.println(this.getBoard());
    }

}
