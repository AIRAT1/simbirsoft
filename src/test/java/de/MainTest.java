package de;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {
    private static WebDriver driver;
    private static final String SITE_URL = "https://passport.yandex.ru";
    private ReadProperties readProperties = new ReadProperties();

    @BeforeEach
    public void setup() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        cap.setPlatform(Platform.WINDOWS);
        ChromeOptions options = new ChromeOptions();
        options.merge(cap);
        String hubUrl = readProperties.getHubUrl();
        driver = new RemoteWebDriver(new URL(hubUrl), options);
        driver.get(SITE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Epic("TESTING FOR https://passport.yandex.ru tasks")
    @Feature(value = "Test for task registration page is not null")
    @Severity(SeverityLevel.BLOCKER)
    @Description("In this test we will login with correct credentials.")
    @Story(value = "Test for login with correct credentials")
    @Test
    public void mainTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        assertNotNull(registrationPage);
        registrationPage.login();
        registrationPage.sendEmail(driver);
    }

    @AfterEach
    public void burnDown() {
        driver.quit();
    }
}
