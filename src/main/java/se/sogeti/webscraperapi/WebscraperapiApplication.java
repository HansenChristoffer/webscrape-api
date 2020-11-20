package se.sogeti.webscraperapi;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.constants.Settings;

@SpringBootApplication
@Slf4j
public class WebscraperapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebscraperapiApplication.class, args);
		checkFileStructures();
	}

	private static void checkFileStructures() {
		log.info("File structure check commencing...");

		File[] paths = { new File(Settings.BASE_SETTINGS_RELATIVE_PATH), new File(Settings.BASE_IMAGES_RELATIVE_PATH) };

		for (File f : paths) {
			if (!f.exists()) {
				log.info("File [!exists] : {} -> building...", f);
				f.mkdirs();
			}
		}

		log.info("File structure check complete!");
	}
}
