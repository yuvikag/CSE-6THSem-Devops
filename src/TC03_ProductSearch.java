import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC03_ProductSearch {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://automationexercise.com");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Products
        WebElement products = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[2]/a")));
        products.click();

        // Enter search keyword
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/section[1]/div/input")));
        searchBox.sendKeys("shirt");

        // Click Search button
        WebElement searchBtn = driver.findElement(By.xpath("/html/body/section[1]/div/button/i"));
        searchBtn.click();

        // Validate results
        WebElement resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[2]")));

        if (resultText.isDisplayed()) {
            System.out.println("TC03 Passed: Search results displayed");
        } else {
            System.out.println("TC03 Failed");
        }

        driver.quit();
    }
}