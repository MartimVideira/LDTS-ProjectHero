import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {

    private int width;
    private int height;
    private Hero hero;

    public Arena(int width ,int height){
        this.width=width; this.height = height;
        this.hero = new Hero(10,10);
    }
    private boolean canHeroMove(Position pos){
        return  (pos.getX()+this.hero.getRepresentation().length()) <this.width && pos.getY() < this.height && pos.getX()> 0 && pos.getY()> 0;
    }
    public void moveHero(Position pos) {
        if(canHeroMove(pos))
                this.hero.setPosition(pos);
        else
            System.out.println("Couldn't move there!!");
    }

    public void draw(TextGraphics graphics ) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        this.hero.draw(graphics);
    }
    public void processKey(KeyStroke key){
        if(key.getKeyType() == KeyType.Character){
            char key_char = key.getCharacter();
            switch(key_char){
                //Left
                case 'h':
                case 'H':
                    moveHero(this.hero.moveLeft());
                    break;
                //Right
                case 'l':
                case 'L':
                    moveHero(this.hero.moveRight());
                    break;
                //Down
                case 'j':
                case 'J':
                    moveHero(this.hero.moveDown());
                    break;
                //UP
                case 'k':
                case 'K':
                    moveHero(this.hero.moveUp());
                    break;
                default:
                    break;
            }
        }
        
    }


}
