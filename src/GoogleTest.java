import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        // Open Google
        driver.get("https://www.google.com");

        // Maximize window
        driver.manage().window().maximize();

        // Find search box and enter text
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("DevOps Selenium");

        // Submit search
        searchBox.submit();

        // Wait (just for demo)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        String title = driver.getTitle();
        
        // Close browser
        driver.quit();
    }
}