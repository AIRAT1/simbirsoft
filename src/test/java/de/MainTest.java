package de;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {
    static WebDriver driver;

    @BeforeClass
    public static void setup() {
//        System.setProperty("webdriver.chrome.driver", "/Users/Vaio/Downloads/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://passport.yandex.ru");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void mainTest() {
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertTrue(title.equals("Авторизация"));
        driver.findElement(By.xpath("//*[@id=\'passp-field-login\']")).sendKeys("AIRIZZIO");
        driver.findElement(By.xpath("//*[@id=\'root\']/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/div[1]/form/div[3]/button")).click();
        driver.findElement(By.xpath("//*[@id=\'passp-field-passwd\']")).sendKeys("Diana2013");
        driver.findElement(By.xpath("//*[@id=\'root\']/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[3]/button")).click();

        driver.findElement(By.xpath("//*[@id=\'root\']/div/div[2]/div[1]/div/div/div/a[1]/span[1]")).click();
        driver.findElement(By.xpath("//*[@id=\'root\']/div/div[2]/div[1]/div/div/div/div/ul/ul/li[1]/a/span")).click();

        int size = driver.findElements(By.partialLinkText("Simbirsoft Тестовое задание")).size();
        System.out.println(size);

        driver.findElement(By.xpath("//*[@id=\'nb-1\']/body/div[2]/div[8]/div/div[3]/div[2]/div[2]/div/div/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\'nb-1\']/body/div[2]/div[11]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[1]/div[1]/div[1]/div/div/div/div/div")).sendKeys("airizzio@yandex.ru");
        driver.findElement(By.xpath("//*[@id=\'nb-1\']/body/div[2]/div[11]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[1]/div[1]/div[3]/div/div/input")).sendKeys("Simbirsoft Тестовое задание. Хайруллин");
        driver.findElement(By.xpath("//*[@id=\'cke_1_contents\']/div")).sendKeys(String.valueOf(size));
        driver.findElement(By.xpath("//*[@id=\'nb-1\']/body/div[2]/div[11]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div/div[1]/div[1]/button")).click();

    }

    @AfterClass
    public static void burnDown() {
        driver.quit();
    }

}
