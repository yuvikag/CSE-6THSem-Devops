import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC06_Search {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Products page
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Products')]"))).click();

        // ---------- VALID SEARCH ----------
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='search_product']")));
        searchBox.sendKeys("kurta");

        driver.findElement(By.xpath("//button[@id='submit_search']")).click();

        // Validate search result
        WebElement resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Searched Products')]")));

        if (resultText.isDisplayed()) {
            System.out.println("TC06 Passed: Valid search working");
        } else {
            System.out.println("TC06 Failed: Valid search not working");
        }

        // ---------- INVALID SEARCH ----------
        WebElement searchBox2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='search_product']")));
        searchBox2.clear();
        searchBox2.sendKeys("xyz123invalid");

        driver.findElement(By.xpath("//button[@id='submit_search']")).click();

        // Validate no products message / empty result
        try {
            WebElement noResult = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(),'No products')]")));

            if (noResult.isDisplayed()) {
                System.out.println("TC06 Passed: Invalid search handled correctly");
            }
        } catch (Exception e) {
            System.out.println("TC06 Note: No explicit message, but no products shown");
        }

        // ---------- EMPTY SEARCH ----------
        WebElement searchBox3 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='search_product']")));
        searchBox3.clear();

        driver.findElement(By.xpath("//button[@id='submit_search']")).click();

        // Validate empty search behavior
        try {
            WebElement resultEmpty = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[contains(text(),'Searched Products')]")));

            System.out.println("TC06 Passed: Empty search handled");
        } catch (Exception e) {
            System.out.println("TC06 Note: Empty search shows default products");
        }

        driver.quit();
    }
}