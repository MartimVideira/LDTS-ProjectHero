import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public abstract class Element {

    protected Position position;
    protected String representation;

    public Element(int x, int y) {this.position = new Position(x,y);}

    public void setPosition(Position pos) {this.position.setX(pos.getX());this.position.setY(pos.getY());}

    public  Position getPosition(){return this.position;}

    public String getRepresentation(){return this.representation;}

    public abstract  void draw(TextGraphics graphics);

    public List<Position> getOcupiedPositions(){
        List<Position> p = new ArrayList<>();
        for(int i =0 ; i < this.representation.length();i++)
            p.add(new Position(this.position.getX()+i,this.position.getY()));
        return p;
    }
    public boolean colides(Element el){
        if(this == el || this.position.getY()!= el.getPosition().getY())
            return false;
        List<Position> p = this.getOcupiedPositions();
        for(Position p1 : el.getOcupiedPositions())
            if(p.contains(p1))
                return true;
        return false;
    }
}
