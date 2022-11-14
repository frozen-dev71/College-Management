package exception;

@SuppressWarnings("serial")
public class CoursePlanException extends Exception{

    public CoursePlanException() {
        super();
    }


    public CoursePlanException(String message) {
        super(message);
    }
}
