import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;
    private String representation = "(｡◕‿◕｡)";

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

    public void draw(Screen screen){
        for(int i = 0 ; i < this.representation.length() ; i++)
            screen.setCharacter(position.getX() +i,position.getY(), TextCharacter.fromCharacter(this.representation.charAt(i))[0]);

    }

}
