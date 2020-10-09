package se.sogeti.webscraperapi.exceptions;

public class SellerNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 4861012421960377904L;

    public SellerNotFoundException(String id) {
        super("Could not find seller with id, " + id);
    }    
}
