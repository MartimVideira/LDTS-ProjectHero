import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

















public class Hero {
    private Position position;
    private String representation1 = "(｡◕‿◕｡)";
    private String color = "#FFFF33";


    public Hero(int x, int y){
        this.position = new Position(x,y);
    }

    public void setX(int x){this.position.setX(x);}

    public void setY(int y){this.position.setY(y);}

    public int getX (){return this.position.getX();}

    public int getY(){return this.position.getY();}

    public void  setPosition(int x , int y){this.position.setX(x); this.position.setY(y);}

    public void setPosition(Position pos) {this.position.setX(pos.getX());this.position.setY(pos.getY());}

    public  Position getPosition() {return this.position;}

    public Position moveUp() { return new Position(position.getX(), position.getY() -1);}

    public Position  moveDown(){return new Position(position.getX(),position.getY()+1);}

    public Position moveLeft() {return new Position(position.getX()-1, position.getY());}

    public Position moveRight() {return new Position(position.getX() +1, position.getY());}

    public String getRepresentation() {return this.representation1;}

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.position.getX(),this.position.getY()),this.representation1);
    }
}
