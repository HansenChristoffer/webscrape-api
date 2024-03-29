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
            rtnStr = fetchSettingsValue(Settings.SETTINGS_FILES[0]);
        } else if (value.equalsIgnoreCase("as")) {
            rtnStr = fetchSettingsValue(Settings.SETTINGS_FILES[1]);
        } else if (value.equalsIgnoreCase("cs")) {
            rtnStr = fetchSettingsValue(Settings.SETTINGS_FILES[2]);
        } else {
            return ResponseEntity.badRequest().body("No such parameter");
        }

        return ResponseEntity.ok(rtnStr);
    }

    public ResponseEntity<String> setSettings(String value, String settingsValue) {
        boolean worked = false;

        if (value.equalsIgnoreCase("ls")) {
            worked = setSettingsValue(Settings.SETTINGS_FILES[0], settingsValue);
        } else if (value.equalsIgnoreCase("as")) {
            worked = setSettingsValue(Settings.SETTINGS_FILES[1], settingsValue);
        } else if (value.equalsIgnoreCase("cs")) {
            worked = setSettingsValue(Settings.SETTINGS_FILES[2], settingsValue);
        } else {
            return ResponseEntity.badRequest().body("No such parameter");
        }

        return worked ? ResponseEntity.status(HttpStatus.CREATED).body(settingsValue)
                : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("ERROR - [Did not modify settings!]");
    }

    public ResponseEntity<Boolean> isActive(String value) {
        if (value.equalsIgnoreCase("LS")) {
            return ResponseEntity.ok(Settings.activeLS);
        } else if (value.equalsIgnoreCase("CS")) {
            return ResponseEntity.ok(Settings.activeCS);
        } else if (value.equalsIgnoreCase("AS")) {
            return ResponseEntity.ok(Settings.activeAS);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    public static ResponseEntity<Boolean> setActive(String value, boolean bool) {
        boolean b = false;

        if (value.equalsIgnoreCase("LS")) {
            b = Settings.activeLS = bool;
        } else if (value.equalsIgnoreCase("CS")) {
            b = Settings.activeCS = bool;
        } else if (value.equalsIgnoreCase("AS")) {
            b = Settings.activeAS = bool;
        }

        return b == bool ? ResponseEntity.ok(bool) : ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(false);
    }

    public static ResponseEntity<Boolean> toggleActive(String value) {
        boolean b = false;

        if (value.equalsIgnoreCase("LS")) {
            b = Settings.activeLS = !Settings.activeLS;
        } else if (value.equalsIgnoreCase("CS")) {
            b = Settings.activeCS = !Settings.activeCS;
        } else if (value.equalsIgnoreCase("AS")) {
            b = Settings.activeAS = !Settings.activeAS;
        }

        return ResponseEntity.ok(b);
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
