package exception;

@SuppressWarnings("serial")
public class InputException extends Exception{

    public InputException() {

    }

    public InputException(String message) {
        super(message);
    }
}
