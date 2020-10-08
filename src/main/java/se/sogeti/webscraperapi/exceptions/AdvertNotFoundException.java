package se.sogeti.webscraperapi.exceptions;

public class AdvertNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5572262471571670963L;

    AdvertNotFoundException(long id) {
        super("Could not find advert with id, " + id);
    }    
}
