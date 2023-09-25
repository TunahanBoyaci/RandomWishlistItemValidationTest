package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParameterDriver {
    public static WebDriver driver;

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void createParameterDriver(String browserName) {
        closePreviousDrivers();

        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE);

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();

        Duration duration = Duration.ofSeconds(15);
        driver.manage().timeouts().pageLoadTimeout(duration);
        driver.manage().timeouts().implicitlyWait(duration);
    }

    @AfterClass
    public void waitAndQuit() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }

    public void closePreviousDrivers() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
        } catch (Exception e) {
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

    public void login() {
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


    public void login(String username, String password) {
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
