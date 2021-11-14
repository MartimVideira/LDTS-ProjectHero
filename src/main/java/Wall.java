import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    private String representation = "#";
    private String color = "#000000";
    public Wall(int x, int y){super(x,y);}

    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()),this.representation);

    }

}
