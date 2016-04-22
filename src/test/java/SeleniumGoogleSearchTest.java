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
        (new WebDriverWait(driver, 10)).until(sizeOf(By.cssSelector(".srg>.g"), 10));
        (new WebDriverWait(driver, 10)).until(textToBePresentInElementLocated(By.cssSelector(".srg>.g:nth-child(1)"), "Selenium automates browsers"));
        driver.findElement(By.cssSelector(".srg>.g")).findElement(By.cssSelector(".r")).click();
        (new WebDriverWait(driver, 10)).until(textToBePresentInElementLocated(By.cssSelector("h1"), "Browser Automation"));
        assertEquals("http://www.seleniumhq.org/", driver.getCurrentUrl());
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
