import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    private Position position;
    private String representation = "#";
    private String color = "#000000";
    public Wall(int x, int y){this.position = new Position(x,y);}

    public Wall(Position pos){ this.position = pos;}

    public void setPosition(Position pos) { this.position = pos;}

    public Position getPosition() {return this.position;}
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()),this.representation);


    }

}
