import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC04_AddToCart {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Products
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Products')]"))).click();

        // Hover on first product
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='product-image-wrapper'])[1]")));

        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();

        // Click Add to Cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(text(),'Add to cart')])[1]"))).click();

        // Click View Cart (popup)
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[contains(text(),'View Cart')]"))).click();

        // Validate cart page
        WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table[@id='cart_info_table']")));

        if (cart.isDisplayed()) {
            System.out.println("TC04 Passed");
        } else {
            System.out.println("TC04 Failed");
        }

        driver.quit();
    }
}