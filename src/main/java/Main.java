
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/home/ubuntu/geckodriver");
        List<String> bookingKeys = null;
        String username = null;
        String password = null;
        try {
            Scanner sc = new Scanner(new File("/home/ubuntu/Super secret stuff.txt"));
            bookingKeys = getBookingKeys();
            username = sc.next();
            password = sc.next();
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        SessionScheduler driver = new SessionScheduler(options);

        driver.login(username, password);
        System.out.println(Arrays.toString(driver.book(bookingKeys)));
        driver.close();
    }

    private static List<String> getBookingKeys() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/home/ubuntu/BookingKeys.txt"));
        List<String> bookingKeys = new LinkedList<>();

        while(sc.hasNextLine()) {
            bookingKeys.add(sc.nextLine());
        }
        sc.close();
        return bookingKeys;
    }
}
