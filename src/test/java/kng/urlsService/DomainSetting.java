package kng.urlsService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;

public class DomainSetting {

    protected static String setEnvironmentDomain() {
        ClassPathResource resource = new ClassPathResource("environment.properties");
        Properties properties = new Properties();

        try (InputStream inputStream = resource.getInputStream()) {
            properties.load(inputStream);
            return properties.getProperty("domain");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
