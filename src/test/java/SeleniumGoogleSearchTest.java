import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

/**
 * Created by 64 on 21.04.2016.
 */
public class SeleniumGoogleSearchTest {

    static WebDriver driver = new FirefoxDriver();


    @Test
        public void testSearchAndFollowLink() {


            driver.get("http://google.com/ncr");
            driver.findElement(By.name("q")).sendKeys("Selenium automates browsers" + Keys.ENTER);

//            $(By.name("q")).setValue("Selenium automates browsers").pressEnter();
//            searchResults.shouldHaveSize(10);

        (new WebDriverWait(driver, 6)).until(sizeOf(".srg>.g", 10));

//        searchResults.get(0).shouldHave(text("Selenium automates browsers"));

        (new WebDriverWait(driver, 6)).until(
                textToBePresentInElementLocated(By.cssSelector(".srg>.g:nth-child(1)"), "Selenium automates browsers"));

//            searchResults.get(0).find(".r").click();
//            $("h1").shouldHave(exactText("Browser Automation"));


            assertEquals("http://www.seleniumhq.org/", url());
        }

//        ElementsCollection searchResults = $$(".srg>.g");

//    List<WebElement> searchResults = driver.findElements(By.cssSelector(".srg>.g"));

    public class ExamplePage{

        @FindBy(css = ".srg>.g")
        List<WebElement> searchResults;
    }

    ExamplePage page = PageFactory.initElements(driver, ExamplePage.class);

    public static ExpectedCondition<Boolean> sizeOf(final By elementsLocator, final int expectedSize){
        return new ExpectedCondition<Boolean>() {
            private int listSize;
            private List<WebElement> searchResults;

            public Boolean apply(WebDriver driver) {
                searchResults = driver.findElements(elementsLocator);
                listSize = searchResults.size();
                return listSize == expectedSize;
            }

            public String toString() {
                return String.format("\nsize of list: %s\n to be: %s\n while actual size is: %s\n", searchResults, expectedSize, listSize);
            }
        };
    }


    @AfterClass
    public static void teardown(){
        driver.quit();
    }

}
