import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static main.CustomConditions.*;
import static main.CustomConditions.sizeOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;
/**
 * Created by 64 on 21.04.2016.
 */
public class GoogleSearchTest {

    @Test
    public void testSearchAndFollowLink() {

        driver.get("http://google.com/ncr");
        search("Selenium automates browsers");
        wait.until(sizeOf(By.cssSelector(results), 10));
        wait.until(textToBePresentInElementLocated(By.cssSelector(results + ":nth-child(1)"), "Selenium automates browsers"));
        followNthLink(0);
        wait.until(urlContains("http://www.seleniumhq.org/"));
    }

    @Test
    public void testFollowLink() {

        driver.get("http://google.com/ncr");
        search("Selenium automates browsers");
        followNthLink(0);
        wait.until(urlContains("http://www.seleniumhq.org/"));
    }

    static WebDriver driver = new FirefoxDriver();
    static WebDriverWait wait = new WebDriverWait(driver, 10);
    String results = ".srg>.g";

    public void search(String queryText){
        driver.findElement(By.name("q")).sendKeys(queryText + Keys.ENTER);
    }

    public void followNthLink(int index) {
        wait.until(minimumSizeOf(By.cssSelector(results), index + 1));
        driver.findElements(By.cssSelector(results)).get(index).findElement(By.className("r")).click();
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
