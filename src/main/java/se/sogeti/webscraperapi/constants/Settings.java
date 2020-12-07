package se.sogeti.webscraperapi.constants;

public class Settings {

    public static final String BASE_IMAGES_RELATIVE_PATH = "./data/images";
    public static final String BASE_SETTINGS_RELATIVE_PATH = "./config/settings";
    public static final String[] SETTINGS_FILES = { BASE_SETTINGS_RELATIVE_PATH.concat("/linkscraper-settings.xml"),
            BASE_SETTINGS_RELATIVE_PATH.concat("/adscraper-settings.xml"),
            BASE_SETTINGS_RELATIVE_PATH.concat("/categoryscraper-settings.xml") };

    public static boolean activeLS = false;
    public static boolean activeCS = false;
    public static boolean activeAS = false;

    private Settings() {
    }

}
