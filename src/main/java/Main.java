import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        String keyOfBooking = "Sunday; 1PM - 2:15PM; Weight Room";

        System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://9855.ezfacility.com/");

        WebDriverWait wait = new WebDriverWait(driver, 3);
        login(wait, driver, "dtran", "Gemini06");
        switchToWeekView(wait, driver);
        HashMap<String, WebElement> SessionSchedule = getSessionSchedule(driver);
        book(wait, driver, SessionSchedule, keyOfBooking);
    }

    public static HashMap getSessionSchedule(WebDriver driver) {
        List<WebElement> TableRows = driver.findElements(By.xpath("/html/body/section[2]/section/div/div[3]/div/div[2]/div/div/table/tbody/tr"));
        HashMap<String, WebElement> SessionSchedule = new HashMap<>();

        String currDate = "init";

        for (WebElement row : TableRows
        ) {
            if (row.getAttribute("class").equals("fc-list-heading")) {
                currDate = row.findElement(By.xpath("./span[1]")).getText();
            } else {
                String session_time = row.findElement(By.xpath("./td[1]")).getText();
                String session_title = row.findElement(By.xpath("./td[3]")).getText();
                String key = currDate + "; " + session_time + "; " + session_title;
                SessionSchedule.put(key, row);
            }
        }

        return SessionSchedule;
    }

    public static boolean book(WebDriverWait wait, WebDriver driver, HashMap<String, WebElement> SessionSchedule, String keyOfBooking) {

        WebElement rowTime = SessionSchedule.get(keyOfBooking).findElement(By.xpath("./td[1]"));
        wait.until(ExpectedConditions.visibilityOf(rowTime));
        wait.until(ExpectedConditions.elementToBeClickable(rowTime)).click();

        WebElement btnBook = driver.findElement(By.xpath("//*[@id=\"btnBook\"]"));
        wait.until(ExpectedConditions.visibilityOf(btnBook));
        wait.until(ExpectedConditions.elementToBeClickable(btnBook)).click();
        return true;
    }

    public static boolean login(WebDriverWait wait, WebDriver driver, String username, String password) {

        //wait for the page-loader display attribute to become "none", then click on the login
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("page-loader")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"liLogin\"]"))).click();
        //click on the username and then
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Username\"]"))).click();
        driver.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Password\"]"))).click();
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnLogin\"]"))).click();

        return true;
    }

    public static void switchToWeekView(WebDriverWait wait, WebDriver driver) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-login")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-backdrop fade")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/section/div/div[3]/div/div[1]/div[2]/ul/li[2]/button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/section/div/div[3]/div/div[1]/div[2]/ul/li[2]/ul/li[6]/a"))).click();
    }
}
