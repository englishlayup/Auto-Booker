/*This class constructor encapsulates the process of opening up the website. It also have some functions to interact with the site*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SessionScheduler extends FirefoxDriver {
    WebDriverWait wait;

    public SessionScheduler() {
        super();
        wait = new WebDriverWait(this, 5);
        this.get("https://9855.ezfacility.com/");
    }

    //**Note: This function only works in List View Mode
    public boolean book(List<String> bookingKeys) {
        for (String bookingKey:
             bookingKeys) {

            wait.until(ExpectedConditions.invisibilityOf(this.findElement(By.xpath("//*[@id=\"modal-book-reservation\"]"))));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String key = "";
            String currDate = "";
            List<WebElement> TableRows = this.findElements(By.xpath("/html/body/section[2]/section/div/div[3]/div/div[2]/div/div/table/tbody/tr"));

            for (WebElement row : TableRows
            ) {
                if (row.getAttribute("class").equals("fc-list-heading")) {
                    key = currDate = row.findElement(By.xpath("./td/span[1]")).getText();
                } else {
                    String session_time = row.findElement(By.xpath("./td[1]")).getText();
                    String session_title = row.findElement(By.xpath("./td[3]")).getText();
                    key += "; " + session_time + "; " + session_title;
                }
                if(key.equals(bookingKey)) {
                    WebElement rowTime = row.findElement(By.xpath("./td[1]"));
                    wait.until(ExpectedConditions.elementToBeClickable(rowTime)).click();

                    WebElement btnBook = this.findElement(By.xpath("//*[@id=\"btnBook\"]"));
                    wait.until(ExpectedConditions.elementToBeClickable(btnBook)).click();
                    break;
                }
                key = currDate;
            }
        }


        return true;
    }

    public boolean login(String username, String password) {

        //wait for the page-loader display attribute to become "none", then click on the login
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("page-loader")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"liLogin\"]"))).click();
        //click on the username and then
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Username\"]"))).click();
        this.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Password\"]"))).click();
        this.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnLogin\"]"))).click();

        return this.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div")).isDisplayed();
    }

    //press on "change view" then press "List Week View"
    public void switchToWeekView() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-login")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-backdrop fade")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/section/div/div[3]/div/div[1]/div[2]/ul/li[2]/button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/section/div/div[3]/div/div[1]/div[2]/ul/li[2]/ul/li[6]/a"))).click();
    }
}
