package nl.streeksoft.bit.models;
/**
 * The Player class represents the Player playing the game:
 * the user running the application and the cpu who is playing the game automatically.
 */
public class Player {
    private String name;
    private PlayerType type;
    private CheckColor color;

    public Player(String name, CheckColor color, PlayerType type) {
        this.name = name;
        this.color = color;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CheckColor getColor() {
        return color;
    }

    public void setColor(CheckColor color) {
        this.color = color;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }
}
