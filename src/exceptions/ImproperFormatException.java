package exceptions;

public class ImproperFormatException extends Throwable {

    @Override
    public String toString() {
        return "%n\u001B[31mInput was improperly formatted\u001B[0m%n%n";
    }
}
