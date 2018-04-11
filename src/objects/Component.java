package objects;

import java.io.Console;

public interface Component {

    void render(Console console);

    void add(Console console);

    void add(String arg1, int arg2, Console console);

    void remove(Console console);

    void remove(String arg1, int arg2, Console console);

    Component open(String componentString);

    String toDisplayName();

}
