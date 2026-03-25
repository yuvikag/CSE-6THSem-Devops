import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TC01_LoginTest {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://automationexercise.com");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement loginLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[4]/a")));
        loginLink.click();

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div[1]/div/form/input[2]")));
        email.sendKeys("yuvikagupta272@gmail.com");

        WebElement password = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/input[3]"));
        password.sendKeys("yuvi123");

        WebElement loginBtn = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/button"));
        loginBtn.click();

        System.out.println("TC01 Passed: Login Successful");

        driver.quit();
    }
}
