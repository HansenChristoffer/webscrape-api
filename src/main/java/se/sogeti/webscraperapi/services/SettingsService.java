package se.sogeti.webscraperapi.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.constants.Settings;

@Service
@Slf4j
public class SettingsService {

    public ResponseEntity<String> getSettings(String value) {
        String rtnStr = "";

        if (value.equalsIgnoreCase("ls")) {
            rtnStr = fetchSettingsValue("src/main/resources/config/settings/linkscraper-settings.xml");
        } else if (value.equalsIgnoreCase("as")) {
            rtnStr = fetchSettingsValue("src/main/resources/config/settings/adscraper-settings.xml");
        } else if (value.equalsIgnoreCase("cs")) {
            rtnStr = fetchSettingsValue("src/main/resources/config/settings/categoryscraper-settings.xml");
        } else {
            return ResponseEntity.badRequest().body("No such parameter");
        }

        return ResponseEntity.ok(rtnStr);
    }

    public ResponseEntity<String> setSettings(String value, String settingsValue) {
        boolean worked = false;

        if (value.equalsIgnoreCase("ls")) {
            worked = setSettingsValue("src/main/resources/config/settings/linkscraper-settings.xml", settingsValue);
        } else if (value.equalsIgnoreCase("as")) {
            worked = setSettingsValue("src/main/resources/config/settings/adscraper-settings.xml", settingsValue);
        } else if (value.equalsIgnoreCase("cs")) {
            worked = setSettingsValue("src/main/resources/config/settings/categoryscraper-settings.xml", settingsValue);
        } else {
            return ResponseEntity.badRequest().body("No such parameter");
        }

        return worked ? ResponseEntity.status(HttpStatus.CREATED).body(settingsValue)
                : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("ERROR - [Did not modify settings!]");
    }

    public ResponseEntity<Boolean> isActive() {
        return ResponseEntity.ok(Settings.active);
    }

    public static ResponseEntity<Boolean> setActive(Boolean value) {
        Settings.active = value;

        return Settings.active == value ? ResponseEntity.ok(value)
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body(false);
    }

    public static ResponseEntity<Boolean> toggleActive() {
        Settings.active = !Settings.active;

        return ResponseEntity.ok().body(Settings.active);
    }

    private String fetchSettingsValue(String path) {
        StringBuilder value = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            String st;

            while ((st = br.readLine()) != null) {
                value.append(st);
            }
        } catch (IOException e) {
            log.error("getSettingsValue().IOException == {}", e.getMessage());
        }

        return value.toString();
    }

    private boolean setSettingsValue(String path, String settingsValue) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), false))) {
            bw.write(settingsValue);
            bw.flush();
        } catch (IOException e) {
            log.error("setSettingsValue().IOException == {}", e.getMessage());
            return false;
        }

        return true;
    }
}
