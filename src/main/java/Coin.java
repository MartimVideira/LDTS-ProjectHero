import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{
    private String color = "#FFFFFF";
    private String representation ="*";
    public Coin(int x, int y) {super(x,y);}

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BLINK);
        graphics.putString(new TerminalPosition(this.position.getX(),this.position.getY()),this.representation);

    }


}
