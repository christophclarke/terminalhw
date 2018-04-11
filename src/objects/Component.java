package objects;

import java.io.Console;

public interface Component {

    void render(Console console);

    void add(Console console) throws ImproperFormatException;

    void add(String arg1, String arg2, Console console) throws ImproperFormatException;

    void remove(Console console) throws ComponentDoesNotExistException, ImproperFormatException;

    void remove(String arg1, String arg2, Console console) throws ComponentDoesNotExistException, ImproperFormatException;

    Component open(String arg1, String arg2) throws ComponentDoesNotExistException, ImproperFormatException;

    String toDisplayName();

}
