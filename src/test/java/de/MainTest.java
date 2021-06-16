package de;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MainTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setup() throws MalformedURLException {

        // 1. Define desired capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        cap.setPlatform(Platform.WINDOWS);
        // 2. Chrome options definition
        ChromeOptions options = new ChromeOptions();
        options.merge(cap);
        // 3. Set driver & hubUrl
        String hubUrl = "http://192.168.1.67:4444/wd/hub";
        driver = new RemoteWebDriver(new URL(hubUrl), options);

        // 4. Set request URL
        driver.get("https://passport.yandex.ru");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void mainTest() {
        String title = driver.getTitle();
        System.out.println(driver.getTitle());
        Assert.assertTrue(title.equals("Авторизация"));

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.login();

        int size = driver.findElements(By.partialLinkText("Simbirsoft Тестовое задание")).size();
        Assert.assertEquals(2, size);

        registrationPage.sendEmail(size);
    }

    @AfterClass
    public static void burnDown() {
//        driver.quit();
    }

}
//HUB console
//java -jar selenium-server-standalone-3.141.59.jar -role hub

//Node console
//java -Dwebdriver.chrome.driver="C:/Users/Vaio/Downloads/chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.1.67:4444/grid/register