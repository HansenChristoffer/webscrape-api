package se.sogeti.webscraperapi.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.sogeti.webscraperapi.services.SettingsService;

@RestController
@RequestMapping(value = { "/api" })
public class SettingsController {

    private SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @GetMapping(value = { "/settings" }, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getSettings(@RequestParam("value") String value) {
        return settingsService.getSettings(value);
    }

    @PostMapping(value = {
            "/settings" }, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> setSettings(@RequestParam("value") String value, @RequestBody String settingsValue) {
        return settingsService.setSettings(value, settingsValue);
    }

    @GetMapping(value = { "/status/isActive" })
    public ResponseEntity<Boolean> isActive() {
        return settingsService.isActive();
    }

    @GetMapping(value = { "/status/setActive" })
    public ResponseEntity<Boolean> setActive(@RequestParam("value") boolean value) {
        return settingsService.setActive(value);
    }

    @GetMapping(value = { "/status/toggle" })
    public ResponseEntity<Boolean> toggleActive() {
        return settingsService.toggleActive();
    }

}
