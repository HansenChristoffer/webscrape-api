package se.sogeti.webscraperapi.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -1823934689513436906L;

    public CategoryNotFoundException(String id) {
        super("Could not find category with id, " + id);
    }    
}
