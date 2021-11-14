import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Element{
    private  String representation = "ʕ·ᴥ·ʔ";
    private String color = "#FF00F0";

    public Monster(int x, int y){super(x,y);}

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.putString(new TerminalPosition(this.position.getX(),this.position.getY()),this.representation);

    }
    public   String getRepresentation(){return representation;}

    public List<Position> ocupiedPositions(){
        List<Position> p = new ArrayList<>();
        for(int i =0 ; i <= this.representation.length();i++)
            p.add(new Position(this.position.getX()+i,this.position.getY()));
        return p;
    }
}
