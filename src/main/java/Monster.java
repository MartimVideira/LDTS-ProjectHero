import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Element {
    private String color = "#FF00F0";
    private boolean dead;
    private boolean disabled;
    private int disabledTurns;
    private List<Position> movements;
    public Monster() {
        super(0, 0);
        this.representation = "ʕ·ᴥ·ʔ";

    }

    public Monster(int x, int y) {
        super(x, y);
        this.representation = "ʕ·ᴥ·ʔ";
        this.dead = false;
        this.movements =new  ArrayList<>();
        for(int i =-1 ; i < 2;i++){
            for(int j=-1; j < 2 ; j++){
                this.movements.add(new Position(i,j));
            }
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.putString(new TerminalPosition(this.position.getX(), this.position.getY()), this.representation);

    }
    public Position  move(){
        if(this.disabled){
            this.disabledTurns--;
            if(this.disabledTurns==0) {
                this.disabled = false;
                this.color = "#FF00F0";
            }
            return this.position;
        }
        else{
        Random random = new Random();
        int movementIndex = random.nextInt(8);
        return this.position.add(this.movements.get(movementIndex));}


    }
    public boolean isDead(){return this.dead;}

    public boolean isDisabled() {return this.disabled;}

    public void die(){
        this.dead =true;
        this.color ="#000000";
        this.representation = "✞";
    }

    public void disable(){
        this.disabled = true;
        this.color = "#FFA500";
        this.disabledTurns =3;
    }
}
