package objects;

import exceptions.*;

import javax.swing.*;
import java.io.Console;

public interface Component {

    void render(Console console);

    String render();

    void add(Console console) throws ImproperFormatException;

    void add(String arg1, String arg2, Console console) throws ImproperFormatException;

    void remove(Console console) throws ComponentDoesNotExistException, ImproperFormatException;

    void remove(String arg1, String arg2, Console console) throws ComponentDoesNotExistException, ImproperFormatException;

    Component open(Console console) throws ComponentDoesNotExistException, ImproperFormatException;

    Component open(String arg1, String arg2, Console console) throws ComponentDoesNotExistException, ImproperFormatException;

    Component out();

    String toDisplayName();

}
