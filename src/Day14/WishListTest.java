package Day14;

import Utilities.PageObjectModel;
import Utilities.ParameterDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class WishListTest extends ParameterDriver {
    @Test
    @Parameters({"keyword"})
    public void test1(String keyword) {
        login();

        PageObjectModel pageObjectModel = new PageObjectModel();

        pageObjectModel.searchInbox.sendKeys(keyword);
        pageObjectModel.searchButton.click();

        Random random = new Random();
        int randomIndex = random.nextInt(pageObjectModel.wishButtons.size());
        pageObjectModel.wishButtons.get(randomIndex).click();

        String productTitle = pageObjectModel.productTitles.get(randomIndex).getText();

        pageObjectModel.wishListButton.click();

        int i = 0;
        while(true){
            String productNameOnWishList = pageObjectModel.productNamesOnWishList.get(i).getText();
            i++;

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='text-left']//a")));
            Assert.assertEquals(productNameOnWishList, productTitle);
            boolean a = (productNameOnWishList.equals(productNameOnWishList));

            if (a){
                break;
            }
        }
    }
}
