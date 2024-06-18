package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.auideas.configuration.LoadProperties;
import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
    public static String token;
    SoftAssert softAssert = new SoftAssert();
    public static WebDriver webDriver;
    WebDriverWait webDriverWait;
    WebElement webElement;
    Faker faker = new Faker();

    @BeforeClass
    public  void StartDriver(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(LoadProperties.env.getProperty("URL"));
        webDriver.manage().window().maximize();
    }
    @AfterClass 
    public  void endDriver(){
        webDriver.close();
    }

    public void assertIsEqual(By by, String expected){
        if (expected != null){
            webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
            webElement =webDriver.findElement(by);
            webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(webElement)));
            softAssert.assertEquals(webElement.getText(), expected);
        }
        else{
            return;
        }
    }

    public void assertIsEqualByStringOnly(String actual, String expected) {
        if (expected != null) {
            softAssert.assertEquals(actual, expected);
        } else {
            return;
        }
    }
}
