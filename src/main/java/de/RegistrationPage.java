package de;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private ReadProperties readProperties;

    @FindBy(id = "passp-field-login")
    @CacheLookup
    private WebElement loginField;

    @FindBy(className = "passp-sign-in-button")
    @CacheLookup
    private WebElement loginButton;

    @FindBy(id = "passp-field-passwd")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(className = "passp-sign-in-button")
    @CacheLookup
    private WebElement passwordButton;

    @FindBy(className = "user-account__name")
    @CacheLookup
    private WebElement userButton;

    @FindBy(className = "menu__text")
    @CacheLookup
    private WebElement postButton;

    @FindBy(className = "mail-ComposeButton-Text")
    @CacheLookup
    private WebElement newMailButton;

    @FindBy(className = "composeYabbles")
    @CacheLookup
    private WebElement mailAddressField;

    @FindBy(className = "composeTextField")
    @CacheLookup
    private WebElement mailThemeField;

    @FindBy(className = "cke_wysiwyg_div")
    @CacheLookup
    private WebElement mailTextField;

    @FindBy(className = "ComposeControlPanelButton-Button")
    @CacheLookup
    private WebElement mailSendButton;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        readProperties = new ReadProperties();
    }

    public void login() {
        loginField.sendKeys(readProperties.getUsername());
        loginButton.click();
        passwordField.sendKeys(readProperties.getPassword());
        passwordButton.click();
        userButton.click();
        postButton.click();
    }

    public void sendEmail(WebDriver driver) {
        int size = driver.findElements(By.partialLinkText("Simbirsoft Тестовое задание")).size();
        newMailButton.click();
        mailAddressField.sendKeys(readProperties.getMailAddress());
        mailThemeField.sendKeys( "Simbirsoft Тестовое задание. Хайруллин");
        mailTextField.sendKeys(String.valueOf(size));
        mailSendButton.click();
    }
}
