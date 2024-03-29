import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.LinkedList;
import java.util.List;

public class SessionSchedulerTest {

    private SessionScheduler sessionScheduler;

    @Before
    public void initSessionScheduler() {
        System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        sessionScheduler  = new SessionScheduler(options);
    }

    @Test
    public void testLogin() {
        //login with wrong username/password
        String dummyUsername = "asdf";
        String dummyPassword = "asdf";

        Assert.assertEquals(false, sessionScheduler.login(dummyUsername,dummyPassword));
        sessionScheduler.close();
    }

    @Test
    public void testBook() {
        List<String> testKeys = new LinkedList<>();

        testKeys.add(""); //Invalid key: empty string
        testKeys.add("Monday; 13PM - -26:15PM; Weight Room"); //Invalid key: Time slot does not exist

        boolean[] testResults = sessionScheduler.book(testKeys);
        Assert.assertEquals(false, testResults[0]);
        Assert.assertEquals(false, testResults[1]);
        sessionScheduler.close();
    }

}
