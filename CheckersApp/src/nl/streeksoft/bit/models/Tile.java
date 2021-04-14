package nl.streeksoft.bit.models;

/**
 * The tile class represents the tile, which contains a piece or not (when piece has the value null).
 */
public class Tile {
    private int x;
    private int y;
    private CheckerPiece piece = null;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getX1() {
        return (x+1);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getY1() {
        return (y+1);
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean correctValues() {
        if(this.x >= 0 && this.x < 10 && this.y >= 0 && this.y < 10) return true;
        return false;
    }

    public CheckerPiece getPiece() {
        return piece;
    }

    public void setPiece(CheckerPiece piece) {
        this.piece = piece;
    }

    public void resetTile() {
        this.piece = null;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", piece=" + piece +
                '}';
    }
}
