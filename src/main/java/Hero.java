import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;


public class Hero extends Element {

    private String color = "#00FF00";
    private int lives = 3;


    public Hero(int x, int y){super(x,y);this.representation = "(｡◕‿◕｡)";}



    public Position moveUp() { return new Position(position.getX(), position.getY() -1);}

    public Position  moveDown(){return new Position(position.getX(),position.getY()+1);}

    public Position moveLeft() {return new Position(position.getX()-1, position.getY());}

    public Position moveRight() {return new Position(position.getX() +1, position.getY());}


    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.position.getX(),this.position.getY()),this.representation);
    }
    public void gotHit(){
        this.lives--;

        if(this.lives == 2){
            this.color = "#FFFF00";
            this.representation = "(0_0)";
        }
        else if(this.lives ==1){
            this.color = "#FF0000";
            this.representation ="(._.)";
        }
        else if(this.lives ==0){
            this.color = "#000000";
            this.representation ="(×_×)";

        }
    }
    public boolean isDead(){return this.lives ==0;}
}
