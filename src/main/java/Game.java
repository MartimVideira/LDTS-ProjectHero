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
    Terminal terminal;
    private int x =10;
    private int y = 10;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(200, 200);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);

            screen.startScreen();
            screen.doResizeIfNecessary();

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
        screen.setCharacter(x,y,TextCharacter.fromCharacter('X')[0]);
        screen.refresh();

    }
    private void processKey(KeyStroke key){

        //H  left
        if(key.getKeyType() == KeyType.Character){
            char key_char = key.getCharacter();
            switch(key_char){
                //Left
                case 'h':
                case 'H':
                    x--;
                    break;
                //Right
                case 'l':
                case 'L':
                    x++;
                    break;
                //Down
                case 'j':
                case 'J':
                    y++;
                    break;
                //UP
                case 'k':
                case 'K':
                    y--;
                    break;
                default:
                    break;
            }
        }


    }
    public void run(){
        boolean running = true;
        KeyStroke key;
        while(running){

            try{
                draw();
                key = screen.readInput();
                processKey(key);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

}

