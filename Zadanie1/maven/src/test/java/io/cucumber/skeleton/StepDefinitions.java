package io.cucumber.skeleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;


public class StepDefinitions {

    private WebDriver driver;
    private WebDriverWait wait;
    String alias, firstname, lastname, company, address, city, postcode, phone;


    @Before
    public void iOpenTheBrowser() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\chromedriver.exe");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 3);
            driver.manage().window().maximize();
        }
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Given("I am logged")
    public void iAmLogged() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
        WebElement element = driver.findElement(By.cssSelector("input[name=email]"));
        element.sendKeys("imie.nazwisko@gmail.com");
        element = driver.findElement(By.cssSelector("input[name=password]"));
        element.sendKeys("zadanieautomat");
        element = driver.findElement(By.cssSelector("button[type=submit]"));
        element.click();
        wait.until(ExpectedConditions.urlContains("controller=my-account"));
    }

    @And("I click on Addresses section and I am on the Addresses page")
    public void iClickOnAddressesSectionAndIAmOnTheAddressesPage() {
        WebElement element = driver.findElement(By.id("addresses-link"));
        element.click();
        wait.until(ExpectedConditions.urlContains("controller=addresses"));

    }

    @And("I am in Addresses section and I click on button - create new address")
    public void iAmInAddressesSectionAndIClickOnButtonCreateNewAddress() {
        WebElement element = driver.findElement(By.cssSelector("a[data-link-action='add-address']"));
        element.click();
        wait.until(ExpectedConditions.urlContains("controller=address"));
    }

    @When("I enter {string} as alias and enter {string} as first name and enter {string} as last name and  enter {string} as company and  enter {string} as address and  enter {string} as city and  enter {string} as postcode and  enter {string} as phone")
    public void iEnterAsAliasAndEnterAsFirstNameAndEnterAsLastNameAndEnterAsCompanyAndEnterAsAddressAndEnterAsCityAndEnterAsPostcodeAndEnterAsPhone(String alias, String firstname, String lastname, String company, String address, String city, String postcode, String phone) {
        this.alias = alias;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.phone = phone;

        WebElement element = driver.findElement(By.cssSelector("input[name=alias]"));
        element.sendKeys(alias);
        assertEquals(alias, element.getAttribute("value"));

        element = driver.findElement(By.cssSelector("input[name=firstname]"));
        element.clear();
        element.sendKeys(firstname);
        assertEquals(firstname, element.getAttribute("value"));

        element = driver.findElement(By.cssSelector("input[name=lastname]"));
        element.clear();
        element.sendKeys(lastname);
        assertEquals(lastname, element.getAttribute("value"));

        element = driver.findElement(By.cssSelector("input[name=company]"));
        element.sendKeys(company);
        assertEquals(company, element.getAttribute("value"));

        element = driver.findElement(By.cssSelector("input[name=address1]"));
        element.sendKeys(address);
        assertEquals(address, element.getAttribute("value"));

        element = driver.findElement(By.cssSelector("input[name=city]"));
        element.sendKeys(city);
        assertEquals(city, element.getAttribute("value"));

        element = driver.findElement(By.cssSelector("input[name=postcode]"));
        element.sendKeys(postcode);
        assertEquals(postcode, element.getAttribute("value"));

        element = driver.findElement(By.cssSelector("input[name=phone]"));
        element.sendKeys(phone);
        assertEquals(phone, element.getAttribute("value"));
    }

    @And("I click on Save button")
    public void iClickOnSaveButton() {
        WebElement element = driver.findElement(By.cssSelector("button[type=submit]"));
        element.click();
    }

    @Then("New address is created")
    public void newAddressIsCreated() {
        wait.until(ExpectedConditions.urlContains("controller=address"));
    }
}
