import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC05_Checkout {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            WebElement closeAd = driver.findElement(
                By.xpath("//div[contains(@class,'close')]//svg")
            );
            closeAd.click();
            System.out.println("Ad closed");
        } catch (Exception e) {
            System.out.println("No ad present");
        }

        // Click Products
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Products')]"))).click();

        // Hover and Add to Cart
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='product-image-wrapper'])[1]")));

        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(text(),'Add to cart')])[1]"))).click();

        // Click View Cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[contains(text(),'View Cart')]"))).click();

        // Click Proceed to Checkout
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Proceed To Checkout')]"))).click();

        // If login required → click login
        try {
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//u[contains(text(),'Register / Login')]"))).click();

            // Enter Email
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@data-qa='login-email']")))
                    .sendKeys("yuvikagupta272@gmail.com");

            // Enter Password
            driver.findElement(By.xpath("//input[@data-qa='login-password']"))
                    .sendKeys("yuvi123");

            // Click Login
            driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        } catch (Exception e) {
            System.out.println("Already logged in");
        }

        // Proceed again after login
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Proceed To Checkout')]"))).click();

        // Enter comment
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@name='message']")))
                .sendKeys("Test Order");

        // Click Place Order
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Place Order')]"))).click();

        System.out.println("TC05 Passed: Checkout Process Initiated");

        driver.quit();
    }
}