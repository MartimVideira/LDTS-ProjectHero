import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.security.Key;

public class Game {
    private Screen screen;
    private Arena arena;
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

            this.arena= new Arena(30,30);

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
        this.screen.clear();
        this.arena.draw(this.screen.newTextGraphics());
        this.screen.refresh();

    }

    public int processKey(KeyStroke key){
        if( (key.getKeyType()== KeyType.Character && key.getCharacter()=='q') ||key.getKeyType() == KeyType.EOF)
            return 0;

        return arena.processKey(key);

    }


    public void run(){
        boolean running = true;
        KeyStroke key;
        while(running){

            try{
                draw();
                key = screen.readInput();
                int gameResult = processKey(key);
                if(gameResult == 0){
                    running = false;
                    screen.close();
                    System.out.println("Happy to serve you sir!");
                }
                else if(gameResult == 2)
                    System.out.println("OUR HERO WAS HIT BY  A MONSTER");

            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

}

