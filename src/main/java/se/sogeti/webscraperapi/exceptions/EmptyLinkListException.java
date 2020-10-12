package se.sogeti.webscraperapi.exceptions;

public class EmptyLinkListException extends RuntimeException {
    
    /**
     *
     */
    private static final long serialVersionUID = 4769862033526852100L;

    public EmptyLinkListException() {
        super("The list of links are empty!");
    }
}
