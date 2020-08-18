/*This class constructor encapsulates the process of opening up the website. It also have some functions to interact with the site*/

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
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
    public boolean[] book(List<String> bookingKeys) {
        this.switchToWeekView();
        //an array that indicates booking key at position i is successfully booked or not
        boolean[] isBooked = new boolean[bookingKeys.size()];

        for (String bookingKey:
             bookingKeys) {

//            wait.until(ExpectedConditions.invisibilityOf(this.findElement(By.xpath("//*[@id=\"modal-book-reservation\"]"))));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String currDate = "";
            String session_time = "";
            String session_title = "";
            List<WebElement> TableRows = this.findElements(By.xpath("/html/body/section[2]/section/div/div[3]/div/div[2]/div/div/table/tbody/tr"));

            for (WebElement row : TableRows
            ) {
                if (row.getAttribute("class").equals("fc-list-heading")) {
                    currDate = row.findElement(By.xpath("./td/span[1]")).getText();
                } else {
                    session_time = row.findElement(By.xpath("./td[1]")).getText();
                    session_title = row.findElement(By.xpath("./td[3]")).getText();
                }
                if(bookingKey.equals(currDate + "; " + session_time + "; " + session_title)) {
                    WebElement rowTime = row.findElement(By.xpath("./td[1]"));
                    wait.until(ExpectedConditions.elementToBeClickable(rowTime)).click();

                    WebElement btnBook = this.findElement(By.xpath("//*[@id=\"btnBook\"]"));

                    //if btnBook is not visible, the session may be full or booked already
                    try {
                        wait.until(ExpectedConditions.visibilityOf(btnBook));
                    } catch(TimeoutException e) {
                        this.findElement(By.xpath("/html/body/section[2]/section/div/div[4]/div/div/form/div[1]/ul/li/a")).click();
                        break;
                    }
                    wait.until(ExpectedConditions.elementToBeClickable(btnBook)).click();
                    isBooked[bookingKey.indexOf(bookingKey)] = true;
                    break;
                }
            }
        }


        return isBooked;
    }

    public boolean login(String username, String password) {

        //wait for the page-loader display attribute to become "none", then click on the login
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("page-loader")));

        if(!this.findElement(By.xpath("//*[@id=\"liLogin\"]")).isDisplayed()) return false;

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"liLogin\"]"))).click();

        //click on the username and then
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Username\"]"))).click();
        this.findElement(By.xpath("//*[@id=\"Username\"]")).sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Password\"]"))).click();
        this.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnLogin\"]"))).click();

        return this.findElement(By.xpath("//*[@id=\"alertContainer\"]")).isDisplayed();
    }

    //press on "change view" then press "List Week View"
    public void switchToWeekView() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-login")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal-backdrop fade")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/section/div/div[3]/div/div[1]/div[2]/ul/li[2]/button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/section/div/div[3]/div/div[1]/div[2]/ul/li[2]/ul/li[6]/a"))).click();
    }
}
