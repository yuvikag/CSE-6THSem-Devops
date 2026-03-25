import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC07_Wishlist {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // ---------- LOGIN ----------
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Signup / Login')]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@data-qa='login-email']")))
                .sendKeys("yuvikagupta272@gmail.com");

        driver.findElement(By.xpath("//input[@data-qa='login-password']"))
                .sendKeys("yuvi123");

        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        // ---------- NAVIGATE TO PRODUCTS ----------
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Products')]"))).click();

        // ---------- HOVER ON FIRST PRODUCT ----------
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='product-image-wrapper'])[1]")));

        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();

        // ---------- CLICK ADD TO WISHLIST ----------
        WebElement wishlist = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//a[contains(text(),'Add to wishlist')])[1]")));

        // Scroll + JS click (avoid ad issue)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wishlist);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wishlist);

        // ---------- VALIDATION ----------
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(),'added to your wishlist')]")));

        if (message.isDisplayed()) {
            System.out.println("TC07 Passed: Product added to wishlist");
        } else {
            System.out.println("TC07 Failed");
        }

        driver.quit();
    }
}