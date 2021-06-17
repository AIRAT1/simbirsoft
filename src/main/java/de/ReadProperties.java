package de;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private final String username;
    private final String password;
    private final String mailAddress;
    private final String hubUrl;

    public ReadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")){
            properties.load(fis);
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            mailAddress = properties.getProperty("mailAddress");
            hubUrl = properties.getProperty("hubUrl");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public String getHubUrl() {
        return hubUrl;
    }
}
