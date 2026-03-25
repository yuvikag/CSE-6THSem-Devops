import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC02_InvalidLogin {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://automationexercise.com");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a")).click();

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div[1]/div/form/input[2]")));
        email.sendKeys("wronguser@gmail.com");

        WebElement password = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/input[3]"));
        password.sendKeys("wrong123");

        driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/button")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/section/div/div/div[1]/div/form/p")));

        if (errorMsg.isDisplayed()) {
            System.out.println("TC02 Passed: Error message displayed");
        } else {
            System.out.println("TC02 Failed");
        }

        driver.quit();
    }
}