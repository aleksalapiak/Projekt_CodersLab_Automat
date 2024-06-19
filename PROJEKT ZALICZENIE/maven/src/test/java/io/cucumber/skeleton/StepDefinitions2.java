package io.cucumber.skeleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.Rectangle;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;


public class StepDefinitions2 {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void iOpenTheBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
        driver.manage().window().maximize();
    }


    @After
    public void tearDown() {
        try {
            takeScreenshot("screenshot.png");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public void takeScreenshot(String filePath) throws Exception {
        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image, "png", new File(filePath));
        System.out.println("Screenshot saved: " + filePath);
}

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
        WebElement element = driver.findElement(By.cssSelector("input[name=email]"));
        element.sendKeys("imie.nazwisko@gmail.com");
        element = driver.findElement(By.cssSelector("input[name=password]"));
        element.sendKeys("zadanieautomat");
        element = driver.findElement(By.cssSelector("button[type=submit]"));
        element.click();
        wait.until(ExpectedConditions.urlContains("controller=my-account"));
    }

    @When("the user searches for {string}")
    public void theUserSearchesFor(String Hummingbird) {
        WebElement element = driver.findElement(By.cssSelector("input[name=s]"));
        element.sendKeys(Hummingbird);
        element.sendKeys(Keys.RETURN);
    }

    @Then("the user selects the product {string}")
    public void theUserSelectsTheProduct(String HummingbirdPrintedSweater) {
        WebElement element = driver.findElement(By.cssSelector("article[data-id-product='2']"));
        element.click();
    }

    @And("the user selects size {string}")
    public void theUserSelectsSize(String M) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement sizeDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("group_1")));
        Select select = new Select(sizeDropdown);
        select.selectByVisibleText(M);
    }

    @And("the user selects quantity {int}")
    public void theUserSelectsQuantity(Integer quantity) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = driver.findElement(By.id("quantity_wanted"));
        element.clear();
        WebElement increaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-touchspin.js-touchspin.bootstrap-touchspin-up")));
        for (int i = 1; i < quantity; i++) {
            increaseButton.click();
        }
    }

    @And("the user adds the product to the cart")
    public void theUserAddsTheProductToTheCart() {
        WebElement element = driver.findElement(By.cssSelector("button[type=submit]"));
        element.click();
    }

    @And("the user proceeds to checkout and is in the shopping cart")
    public void theUserProceedsToCheckout() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-primary[href*='controller=cart']")));
        element.click();
        wait.until(ExpectedConditions.urlContains("controller=cart&action=show"));
    }

    @And("the user click on proceed to checkout button")
    public void theUserClickOnProceedToCheckoutButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-primary[href*='controller=order']")));
        element.click();
    }

    @And("the user confirms the address")
    public void theUserConfirmsTheAddress() {
        WebElement element = driver.findElement(By.cssSelector("input[type='radio'][value='13562']"));
        element.click();
        WebElement elementContinue = driver.findElement(By.cssSelector("button[name=confirm-addresses]"));
        elementContinue.click();
    }

    @And("the user selects the pickup method pick up in store")
    public void theUserSelectsThePickupMethod() {
        WebElement element = driver.findElement(By.id("delivery_option_8"));
        if (!element.isSelected()) {
            element.click();
        }
        WebElement elementContinue = driver.findElement(By.cssSelector("button[name=confirmDeliveryOption]"));
        elementContinue.click();
    }

    @And("the user selects the payment option Pay by Check")
    public void theUserSelectsThePaymentOption() {
        WebElement element = driver.findElement(By.id("payment-option-1"));
        if (!element.isSelected()) {
            element.click();
            WebElement consent = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
            consent.click();
        }
    }

    @And("the user places the order with an obligation to pay")
    public void theUserPlacesTheOrderWithAnObligationToPay() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#payment-confirmation button[type='submit']")));
        element.click();
    }

    @Then("the user takes a screenshot of the order confirmation and total amount")
    public void theUserTakesAScreenshotOfTheOrderConfirmationAndTotalAmount() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content-hook_order_confirmation")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".total-value.font-weight-bold")));
        wait.until(ExpectedConditions.urlContains("controller=order-confirmation"));
        }
    }
