package exception;

@SuppressWarnings("serial")
public class CourseException extends Exception{

    public CourseException() {
        super();
    }


    public CourseException(String message) {
        super(message);
    }

}
