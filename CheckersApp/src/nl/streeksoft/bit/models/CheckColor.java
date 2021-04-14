package nl.streeksoft.bit.models;

/**
 * enumerator for color white and black (used for pieces and player)
 */
public enum CheckColor {
    WHITE ("wit"),
    BLACK ("zwart");

    private String colorName;

    public String getColorName() {
        return this.colorName;
    }

    private CheckColor(String color) {
        this.colorName = color;
    }
}
