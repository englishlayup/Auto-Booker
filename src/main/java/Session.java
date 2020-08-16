import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class Session {
    private String session_date;
    private String session_time;
    private String session_title;

    public Session(String session_date, WebElement tr) {
        this.session_date = session_date;
        session_time = tr.findElement(By.className("fc-list-item-time")).getText();
        session_title = tr.findElement(By.className("fc-list-item-title")).getText();
    }

    public Session(String session_date, String session_time, String session_title) {
        this.session_date = session_date;
        this.session_time = session_time;
        this.session_title = session_title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(session_time, session.session_time) &&
                Objects.equals(session_title, session.session_title);
    }

}
