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
    private List<Monster> monsters;

    public Arena(int width ,int height){
        this.width=width; this.height = height;
        this.hero = new Hero(10,10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }
    private int canHeroMove(Position pos){
        //Verifying if first character of player colides with a wall or the last colides with the wall
        //This won´t work if theres walls  inside the arena
        for(Wall wall : walls)
            if(pos.equals(wall.getPosition()) || new Position(pos.getX() +this.hero.getRepresentation().length()-1, pos.getY()).equals(wall.getPosition()))
                return 0;
        Hero temp =new Hero(pos.getX(), pos.getY());
        for(Monster monster :monsters){
            if(monster.colides(temp)){
                if(monster.isDead()) return 0;
                else return 2;
            }
        }
            return 1;
    }
    public int moveHero(Position pos) {
        int heromoveResult = canHeroMove(pos);
        //Can Move
        if(heromoveResult == 1){
                this.hero.setPosition(pos);
                retrieveCoins();
        }
        //Detect colision with monster;
        else if(heromoveResult ==2){
            hero.lost();
            return 2;
        }
        moveMonsters();
        updateMonsterState();
        //Check again if a monster killed our hero

        return 1;
    }
    public boolean canMoveMonster(Position pos){
        Monster temp = new Monster(pos.getX(), pos.getY());
        for(Wall w : this.walls)
            if(w.colides(temp))
                return false;

        return true;
    }
    public void  moveMonsters(){
        List<Monster> deadMonsters = new ArrayList<>();
        for(Monster m : this.monsters){
            if(m.isDead())
                continue;
            Position to_move = m.move();
            if(canMoveMonster(to_move))
                m.setPosition(to_move);

        }
    }
    private void updateMonsterState(){
        for(Monster m : this.monsters){
            for(Monster m1 : this.monsters){
                if(m1==m ||m1.getPosition().getY() != m.getPosition().getY())
                    continue;
                if(m1.colides(m)){
                    m.die();
                    m1.die();
                }
            }

        }

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
        int numberOfCoins = 50;
        int counter = 0;
        while(counter < numberOfCoins){
            Position p  = new Position(random.nextInt(width-2)+1, random.nextInt(height-2)+1 );
            if(positions.contains(p))
                continue;
            coins.add(new Coin(p.getX(),p.getY()));
            positions.add(p);
            counter++;
        }
        return coins;
    }
    //ALterar o modo como obtenho a representacao
    public List<Monster> createMonsters(){
        List<Monster> m = new ArrayList<>();

        Random random = new Random();
        int numberOfMonsters = 10;
        int counter = 0;

        while(counter < numberOfMonsters){
            Position p  = new Position(random.nextInt(width-1-new Monster(0,0).getRepresentation().length())+1, random.nextInt(height-2)+1 );
            Monster currentMonster = new Monster(p.getX(),p.getY());
            //Check if candidate monster overlaps with hero or another monster
            boolean overlap = false;
            for(Monster monster : m )
               if(monster.colides(currentMonster)){
                   overlap = true;
                   break;
               }
            if(this.hero.colides(currentMonster))
                overlap = true;
            if(overlap)
                continue;
            m.add(currentMonster);
            counter++;

        }
        return m;

    }
    private void retrieveCoins(){
        List<Coin> coins_to_remove  = new ArrayList<>();
        for(Coin coin : coins)
            if(coin.getPosition().getY() ==this.hero.getPosition().getY()){
                if(coin.colides(this.hero)) {
                    coins_to_remove.add(coin);

                }
            }
        coins.removeAll(coins_to_remove);

    }

    public void draw(TextGraphics graphics ) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(this.width, this.height), ' ');

        for(Wall wall : walls)
            wall.draw(graphics);
        for(Coin coin : coins)
            coin.draw(graphics);
        for(Monster monster : monsters)
            monster.draw(graphics);
        this.hero.draw(graphics);
    }
    public int processKey(KeyStroke key){
        if(key.getKeyType() == KeyType.Character){
            char key_char = key.getCharacter();
            switch(key_char){
                //Left
                case 'h':
                case 'H':
                    return moveHero(this.hero.moveLeft());

                //Right
                case 'l':
                case 'L':
                    return moveHero(this.hero.moveRight());

                //Down
                case 'j':
                case 'J':
                    return moveHero(this.hero.moveDown());

                //UP
                case 'k':
                case 'K':
                    return moveHero(this.hero.moveUp());

                default:
                    break;
            }

        }
        return  1;
    }


}
