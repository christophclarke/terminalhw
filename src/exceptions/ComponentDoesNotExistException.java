package exceptions;

public class ComponentDoesNotExistException extends Throwable {

    @Override
    public String toString() {
        return "%n\u001B[31mComponent does not exist\u001B[0m%n%n";
    }
}
