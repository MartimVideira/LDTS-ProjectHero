import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x;
    private int y;
    private String representation = "(｡◕‿◕｡)";

    public Hero(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setX(int x){this.x = x;}

    public void setY(int y){this.y = y;}

    public int getX (){return this.x;}

    public int getY(){return this.y;}

    public void moveUP() { this.y--;}

    public void moveDown(){this.y++;}

    public void moveLeft() {this.x--;}

    public void moveRight() {this.x++;}

    public void draw(Screen screen){
        for(int i = 0 ; i < this.representation.length() ; i++)
            screen.setCharacter(this.x,this.y, TextCharacter.fromCharacter(this.representation.charAt(i))[0]);

    }

}
