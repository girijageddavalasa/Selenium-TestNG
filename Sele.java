package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Sele {
    WebDriver driver;

    @BeforeClass
    public void setup() {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\dharm\\OneDrive\\Desktop\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("C:\\Users\\dharm\\eclipse-workspace\\web\\src\\test\\java\\ui\\index.html");  // Open local file
    }

    @Test
    public void testValidLogin() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();

        driver.findElement(By.id("email")).sendKeys("girija@gmail.com");
        driver.findElement(By.id("password")).sendKeys("girija@123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("index1.html"));
    }
    
    @Test
    public void testEmailEmptyPasswordFilled() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();

        driver.findElement(By.id("password")).sendKeys("girija@123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String emailError = driver.findElement(By.id("emailError")).getText();
        Assert.assertEquals(emailError, "Please fill in the email.");
    }

    @Test
    public void testEmailFilledPasswordEmpty() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();

        driver.findElement(By.id("email")).sendKeys("girija@gmail.com");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String passwordError = driver.findElement(By.id("passwordError")).getText();
        Assert.assertEquals(passwordError, "Please fill in the password.");
    }

    @Test
    public void testBothEmpty() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String emailError = driver.findElement(By.id("emailError")).getText();
        String passwordError = driver.findElement(By.id("passwordError")).getText();
        Assert.assertEquals(emailError, "Please fill in the email.");
        Assert.assertEquals(passwordError, "Please fill in the password.");
    }

    @Test
    public void testIncorrectEmail() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();

        driver.findElement(By.id("email")).sendKeys("wrongemail@gmail.com");
        driver.findElement(By.id("password")).sendKeys("girija@123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String loginError = driver.findElement(By.id("loginError")).getText();
        Assert.assertEquals(loginError, "Incorrect email or password.");
    }

    @Test
    public void testIncorrectPassword() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("password")).clear();

        driver.findElement(By.id("email")).sendKeys("girija@gmail.com");
        driver.findElement(By.id("password")).sendKeys("wrongpassword");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String loginError = driver.findElement(By.id("loginError")).getText();
        Assert.assertEquals(loginError, "Incorrect email or password.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
