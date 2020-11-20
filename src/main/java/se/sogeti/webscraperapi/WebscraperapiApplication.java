package se.sogeti.webscraperapi;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se.sogeti.webscraperapi.constants.Settings;

@SpringBootApplication
public class WebscraperapiApplication {

	public static void main(String[] args) {
		checkFileStructures();
		SpringApplication.run(WebscraperapiApplication.class, args);
	}

	private static void checkFileStructures() {
		File[] paths = { new File(Settings.BASE_SETTINGS_RELATIVE_PATH), new File(Settings.BASE_IMAGES_RELATIVE_PATH) };

		for (File f : paths) {
			if (!f.exists()) {
				f.mkdirs();
			}
		}
	}
}
