import java.sql.Time;
import java.util.ArrayList;

public class TimeSlot {
    private int id;
    private boolean SMW;
    private String time;


    public static ArrayList<TimeSlot>TimeTable;

    public TimeSlot(int id, boolean SMW) {
        this.id = id;
        this.SMW = SMW;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSMW() {
        return SMW;
    }

    public void setSMW(boolean SMW) {
        this.SMW = SMW;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "TimeSlot{" +
                "id=" + id +
                ", SMW=" + SMW +
                ", time='" + time + '\'' +
                '}';
    }
}
