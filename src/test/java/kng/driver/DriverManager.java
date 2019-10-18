package kng.driver;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import kng.filesService.FilesPaths;
import static kng.driver.Driver.driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.core.io.ClassPathResource;

class DriverManager {

    private ChromeOptions options;

    DriverManager() {
    }
//

    private String setDriverTrigger() {

        ClassPathResource resource = new ClassPathResource("driver.properties");
        Properties properties = new Properties();

        try (InputStream inputStream = resource.getInputStream()) {
            properties.load(inputStream);
            return properties.getProperty("driver");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private WebDriver setUpChromDriver() {

        String downloadFilepath = FilesPaths.getDownloadsFilesFolder();

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadFilepath);
        chromePrefs.put("profile.default_content_setting_values.popups", 2);
        options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-notifications");
        options.addArguments("start-maximized");

        System.setProperty("webdriver.chrome.driver", FilesPaths.getChromDriverPath());
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver driverSetup(String driverType) {
        if (driverType.equals("chrom")) {
            return setUpChromDriver();
        }
        return null;
    }

    WebDriver getDriver() {
        return this.driverSetup(this.setDriverTrigger());
    }
}
