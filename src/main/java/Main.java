
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<String> bookingKeys = null;
        String username = null;
        String password = null;
        try {
            Scanner sc = new Scanner(new File("src/main/resources/Super secret stuff.txt"));
            bookingKeys = getBookingKeys();
            username = sc.next();
            password = sc.next();
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
        SessionScheduler driver = new SessionScheduler();

        driver.login(username, password);
        System.out.println(Arrays.toString(driver.book(bookingKeys)));
        driver.close();
    }

    private static List<String> getBookingKeys() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/main/resources/BookingKeys.txt"));
        List<String> bookingKeys = new LinkedList<>();

        while(sc.hasNextLine()) {
            bookingKeys.add(sc.nextLine());
        }
        sc.close();
        return bookingKeys;
    }
}
