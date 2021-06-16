package de;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RegistrationPage {
    private final String URL_MATCH = "https://passport.yandex.ru";
    private String username;
    private String password;
    private String mailAddress;
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\'passp-field-login\']")
    @CacheLookup
    private WebElement loginField;

    @FindBy(xpath = "//*[@id=\'root\']/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/div[1]/form/div[3]/button")
    @CacheLookup
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\'passp-field-passwd\']")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\'root\']/div/div[2]/div[2]/div/div/div[2]/div[3]/div/div/div/form/div[3]/button")
    @CacheLookup
    private WebElement passwordButton;

    @FindBy(xpath = "//*[@id=\'root\']/div/div[2]/div[1]/div/div/div/a[1]/span[1]")
    @CacheLookup
    private WebElement userButton;

    @FindBy(xpath = "//*[@id=\'root\']/div/div[2]/div[1]/div/div/div/div/ul/ul/li[1]/a/span")
    @CacheLookup
    private WebElement postButton;

    @FindBy(xpath = "//*[@id=\'nb-1\']/body/div[2]/div[8]/div/div[3]/div[2]/div[2]/div/div/a/span")
    @CacheLookup
    private WebElement newMailButton;

    @FindBy(xpath = "//*[@id=\'nb-1\']/body/div[2]/div[11]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[1]/div[1]/div[1]/div/div/div/div/div")
    @CacheLookup
    private WebElement mailAddressField;

    @FindBy(xpath = "//*[@id=\'nb-1\']/body/div[2]/div[11]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[1]/div[1]/div[3]/div/div/input")
    @CacheLookup
    private WebElement mailThemeField;

    @FindBy(xpath = "//*[@id=\'cke_1_contents\']/div")
    @CacheLookup
    private WebElement mailTextField;

    @FindBy(xpath = "//*[@id=\'nb-1\']/body/div[2]/div[11]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div/div[1]/div[1]/button")
    @CacheLookup
    private WebElement mailSendButton;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void login() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")){
            properties.load(fis);
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            mailAddress = properties.getProperty("mailAddress");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        loginField.sendKeys(username);
        loginButton.click();
        passwordField.sendKeys(password);
        passwordButton.click();
        userButton.click();
        postButton.click();
    }

    public void sendEmail(int size) {
        newMailButton.click();
        mailAddressField.sendKeys(mailAddress);
        mailThemeField.sendKeys("Simbirsoft Тестовое задание. Хайруллин");
        mailTextField.sendKeys(String.valueOf(size));
        mailSendButton.click();
    }
}
