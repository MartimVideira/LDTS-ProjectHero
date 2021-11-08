import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Screen screen;
    Terminal terminal;
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
    public void run(){
        String class_apresentation = "This is my first true labs class and i'm using linux!";
        try {
            draw_name(class_apresentation, 0, 0, false);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

