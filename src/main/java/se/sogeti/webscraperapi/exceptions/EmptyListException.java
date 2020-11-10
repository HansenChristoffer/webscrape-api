package se.sogeti.webscraperapi.exceptions;

public class EmptyListException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 4769862033526852100L;

    public EmptyListException() {
        super("The list is empty!");
    }
}
