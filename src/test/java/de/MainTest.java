package de;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

    @BeforeEach
    public void setup() throws MalformedURLException {

        // 1. Define desired capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        cap.setPlatform(Platform.WINDOWS);
        // 2. Chrome options definition
        ChromeOptions options = new ChromeOptions();
        options.merge(cap);
//        options.setHeadless(true);
        // 3. Set driver & hubUrl
        String hubUrl = "http://192.168.1.67:4444/wd/hub";
        driver = new RemoteWebDriver(new URL(hubUrl), options);

        // 4. Set request URL
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
        login(registrationPage);
        int size = driver.findElements(By.partialLinkText("Simbirsoft Тестовое задание")).size();
        sendEmail(registrationPage, size);
    }

    @Step(value = "Login")
    private void login(RegistrationPage registrationPage) {
        registrationPage.login();
    }

    @Step(value = "Send email with {1}")
    private void sendEmail(RegistrationPage registrationPage, int size) {
        registrationPage.sendEmail(size);
    }

    @AfterEach
    public void burnDown() {
//        driver.quit();
    }

}
//HUB console
//java -jar selenium-server-standalone-3.141.59.jar -role hub

//Node console
//java -Dwebdriver.chrome.driver="C:/Users/Vaio/Downloads/chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.1.67:4444/grid/register