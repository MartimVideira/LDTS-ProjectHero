import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;


public class Application {
    public static void main(String[] args) {
        System.out.println("Hello Demonio!");
        try {
            TerminalSize terminalSize = new TerminalSize(200, 200);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            Terminal terminal = terminalFactory.createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);

            screen.startScreen();
            screen.doResizeIfNecessary();

            int x = 0, y = 0;
            while (true) {

                String gfname = "Lara Daniela Rodrigues Ferreira";
                for (int i = 0; i < gfname.length(); i++) {
                    screen.setCharacter(x+i, y, TextCharacter.fromCharacter(gfname.charAt(i))[0]);

                }
                screen.refresh();
                y+=1;
                x+=1;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
