import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

/**
 * Created by 64 on 21.04.2016.
 */
public class GoogleSearchTest {

    @Test
    public void testSearch() {

        driver.get("http://google.com/ncr");
        driver.findElement(By.name("q")).sendKeys("Selenium automates browsers" + Keys.ENTER);
        wait.until(sizeOf(By.cssSelector(list), 10));
        wait.until(textToBePresentInElementLocated(By.cssSelector(list + ":nth-child(1)"), "Selenium automates browsers"));

    }

    @Test
    public void testFollowLink() {

        driver.get("http://google.com/ncr");
        driver.findElement(By.name("q")).sendKeys("Selenium automates browsers" + Keys.ENTER);
        wait.until(sizeOf(By.cssSelector(list), 10));
        followNthLink(1);
        wait.until(urlContains("http://www.seleniumhq.org/"));
    }

    static WebDriver driver = new FirefoxDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 10);
    String list = ".srg>.g";

    public void followNthLink(int index) {

        driver.findElement(By.cssSelector(list + ":nth-child(" + index + ") .r")).click();
    }

    public static ExpectedCondition<Boolean> sizeOf(final By elementsLocator, final int expectedSize) {
        return new ExpectedCondition<Boolean>() {
            private int listSize;
            private List<WebElement> elements;

            public Boolean apply(WebDriver driver) {
                elements = driver.findElements(elementsLocator);
                listSize = elements.size();
                return listSize == expectedSize;
            }

            public String toString() {
                return String.format("\nsize of list: %s\n to be: %s\n while actual size is: %s\n", elements, expectedSize, listSize);
            }
        };
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
