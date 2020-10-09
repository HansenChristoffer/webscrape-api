package se.sogeti.webscraperapi.exceptions;

public class AbstractNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 3849977173836121489L;

    public <T> AbstractNotFoundException(T t) {
        super("Could not find document with key value : " + t);
    }
}
