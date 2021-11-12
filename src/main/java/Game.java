import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Hero hero;
    private Terminal terminal;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(200, 200);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);

            screen.startScreen();
            screen.doResizeIfNecessary();

            this.hero = new Hero(10,10);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw_name(final String word,int row, int column, boolean clear) throws IOException {
        if(clear)
            screen.clear();
        for(int i=0 ; i < word.length(); i++){
            screen.setCharacter(column + i,row,TextCharacter.fromCharacter(word.charAt(i))[0]);

        }
        screen.refresh();
    }
    private void draw() throws IOException{
        screen.clear();
        this.hero.draw(this.screen);
        screen.refresh();

    }
    private boolean processKey(KeyStroke key){
        if( (key.getKeyType()== KeyType.Character && key.getCharacter()=='q') ||key.getKeyType() == KeyType.EOF)
            return false;
        else if(key.getKeyType() == KeyType.Character){
            char key_char = key.getCharacter();
            switch(key_char){
                //Left
                case 'h':
                case 'H':
                    this.hero.moveLeft();
                    return true;
                //Right
                case 'l':
                case 'L':
                   this.hero.moveRight();
                    return true;
                //Down
                case 'j':
                case 'J':
                    this.hero.moveDown();
                    return true;
                //UP
                case 'k':
                case 'K':
                    this.hero.moveUp();
                    return true;
                default:
                    return false;
            }
        }
        return false;


    }
    public void run(){
        boolean running = true;
        KeyStroke key;
        while(running){

            try{
                draw();
                key = screen.readInput();
                if(!processKey(key)){
                    running = false;
                    screen.close();
                    System.out.println("Happy to serve you sir!");
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

}

