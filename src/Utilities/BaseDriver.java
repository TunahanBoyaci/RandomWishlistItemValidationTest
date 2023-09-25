package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDriver {
    public static WebDriver driver;

    @BeforeClass
    public void createDriver(){
        closePreviousDrivers();
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE);

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        Duration duration = Duration.ofSeconds(15);
        driver.manage().timeouts().pageLoadTimeout(duration);
        driver.manage().timeouts().implicitlyWait(duration);
    }

    @AfterClass (alwaysRun = true)
    public void quitDriver(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }

    public void closePreviousDrivers(){
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void takeScreenShot() throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM_dd_yyyy_hh_mm_ss_SSS");
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("screenShots/screenShot" + localDateTime.format(dateTimeFormatter) + ".png"));
    }

    public void login(){
        driver.get("https://opencart.abstracta.us/index.php?route=account/login");

        WebElement advancedButton = driver.findElement(By.cssSelector("button[id=\"details-button\"]"));
        advancedButton.click();

        WebElement proceedButton = driver.findElement(By.cssSelector("a[id=\"proceed-link\"]"));
        proceedButton.click();

        WebElement emailInbox = driver.findElement(By.cssSelector("input[id='input-email']"));
        emailInbox.sendKeys("ahmetyilmaz2@gmail.com");

        WebElement passInbox = driver.findElement(By.cssSelector("input[id='input-password']"));
        passInbox.sendKeys("123456Tuna.");

        WebElement logButton = driver.findElement(By.cssSelector("input[value='Login']"));
        logButton.click();
    }


    public void login(String username, String password){
        driver.get("https://opencart.abstracta.us/index.php?route=account/login");

        WebElement advancedButton = driver.findElement(By.cssSelector("button[id=\"details-button\"]"));
        advancedButton.click();

        WebElement proceedButton = driver.findElement(By.cssSelector("a[id=\"proceed-link\"]"));
        proceedButton.click();

        WebElement emailInbox = driver.findElement(By.cssSelector("input[id='input-email']"));
        emailInbox.sendKeys(username);

        WebElement passInbox = driver.findElement(By.cssSelector("input[id='input-password']"));
        passInbox.sendKeys(password);

        WebElement logButton = driver.findElement(By.cssSelector("input[value='Login']"));
        logButton.click();
    }
}
