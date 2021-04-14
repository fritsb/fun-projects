package nl.streeksoft.bit.models;

/**
 * The CheckerPiece class represent the piece which is used during the game.
 *
 * Only pieceColor is used during game; type and status are ideas for future improvements
 */
public class CheckerPiece {
    private CheckColor pieceColor;
    private PieceType type = PieceType.NORMAL;
    private PieceStatus status = PieceStatus.INGAME;

    public CheckerPiece(CheckColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public CheckColor getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(CheckColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public String getCode() {
        return pieceColor == CheckColor.BLACK ? "z" : "w";
    }

    @Override
    public String toString() {
        return "CheckerPiece{" +
                "pieceColor=" + pieceColor +
                ", type=" + type +
                '}';
    }
}
