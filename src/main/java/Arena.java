import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Arena {

    private int width;
    private int height;
    private Hero hero;

    public Arena(int width ,int height){
        this.width=width; this.height = height;
        this.hero = new Hero(10,10);
    }
    private void moveHero(Position pos) {

        this.hero.setPosition(pos);}

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
