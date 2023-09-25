package Utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageObjectModel {

    public PageObjectModel(){
        PageFactory.initElements(ParameterDriver.driver,this);
    }

    @FindBy (css = "input[placeholder='Search']")
    public WebElement searchInbox;

    @FindBy (css = "button[class='btn btn-default btn-lg']")
    public WebElement searchButton;

    @FindBy (css = "button[data-original-title=\"Add to Wish List\"]")
    public List<WebElement> wishButtons;

    @FindBy (xpath = "//div[@class='caption']//a")
    public List<WebElement> productTitles;

    @FindBy (xpath = "//span[contains(text(),'Wish List')]")
    public WebElement wishListButton;

    @FindBy (xpath = "//td[@class='text-left']//a")
    public List<WebElement> productNamesOnWishList;


}
