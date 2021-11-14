import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {

    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;

    public Arena(int width ,int height){
        this.width=width; this.height = height;
        this.hero = new Hero(10,10);
        this.walls = createWalls();
        this.coins = createCoins();
    }
    private boolean canHeroMove(Position pos){
        //Verifying if first character of player colides with a wall or the last colides with the wall
        //This won´t work if theres walls  inside the arena
        for(Wall wall : walls)
            if(pos.equals(wall.getPosition()) || new Position(pos.getX() +this.hero.getRepresentation().length()-1, pos.getY()).equals(wall.getPosition()))
                return false;
        return true;
    }
    public void moveHero(Position pos) {
        if(canHeroMove(pos)){
                this.hero.setPosition(pos);
                retrieveCoins();
        }

        else
            System.out.println("Couldn't move there!!");
    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        //TOP and BOTTOM
        for (int c= 0; c< this.width ; c++){
            walls.add(new Wall(c,0));
            walls.add(new Wall(c,height));
        }
        for(int r = 0 ;r < this.height ; r++){
            walls.add(new Wall(0,r));
            walls.add(new Wall(this.width-1,r));
        }

        return walls;
    }

    private List<Coin> createCoins(){
        List<Coin> coins = new ArrayList<>();
        List<Position> positions = new ArrayList<>();
        for(int i = 0 ;i < hero.getRepresentation().length(); i++)
            positions.add(new Position(hero.getPosition().getX() +i,hero.getPosition().getY()));
        Random random = new Random();
        int numberOfCoins = 20;
        int counter = 0;
        while(counter < numberOfCoins){
            Position p  = new Position(random.nextInt(width-2)+1, random.nextInt(height-2)+1 );
            if(positions.contains(p))
                continue;
            coins.add(new Coin(p.getX(),p.getY()));
            counter++;
        }
        return coins;
    }
    private void retrieveCoins(){
        Position p = this.hero.getPosition();
        for(Coin coin : coins)
            if(coin.getPosition().equals(p)){
                coins.remove(coin);
                break;
            }


    }

    public void draw(TextGraphics graphics ) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');
        this.hero.draw(graphics);
        for(Wall wall : walls)
            wall.draw(graphics);
        for(Coin coin : coins)
            coin.draw(graphics);
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
