package objects;

import java.io.Console;
import java.util.ArrayList;

public interface Component {

    void render(Console c);
    void add();
    void remove();
    Component open(Component component);

}
