import org.omg.CORBA.DynAnyPackage.Invalid;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public
class SeleniumTest {


    @Test
    public void openGooglePage() throws InterruptedException {

        WebDriver driver = getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://www.google.pl");

        // przechodzimy do okienka z akceptacją cookies
       new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("frame_xpath")));
        driver.switchTo().frame(0);
        //znajdowanie przycisku
        WebElement agreeButton = driver.findElement(By.xpath("//span[contains(text(), 'Zgadzam się')]"));
        // kliknięcie w przycisk
        agreeButton.click();

        //powrót do pierwotnego okna
        driver.switchTo().defaultContent();

        // znajdź pole wyszukiwania
        WebElement searchField = driver.findElement(By.name("q"));
        // wprowadź wartość selenium do pola
        searchField.sendKeys("Selenium");
        // zasymuluj naciśniecie entera
        searchField.sendKeys(Keys.ENTER);

        //znalezienie rezultatu
        WebElement results = driver.findElement(By.xpath("//a[cointains(@href, 'selenium.dev)]//span'"));


        Assert.assertTrue(results.isDisplayed());



    }

    public WebDriver getDriver(String browser) {

        if (browser == "firefox") {
            System.setProperty("webdriver.gecko.driver", "D:\\firefox_webdriver\\geckodriver-v0.29.1-win64\\geckodriver.exe");
            return new FirefoxDriver();

        } else if (browser == "chrome") {
            System.setProperty("webdriver.chrome.driver", "D:\\chrome_webdriver\\chromedriver_win32\\chromedriver.exe");
            return new ChromeDriver();
        } else if (browser == "ie") {
            System.setProperty("webdriver.ie.driver", "D:\\IE_webdriver\\IEDriverServer_x64_3.150.2\\IEDriverServer.exe");
            return new InternetExplorerDriver();
        }
        else {
            throw new InvalidArgumentException("Invalid browse name");
        }


    }
}
