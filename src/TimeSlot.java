import java.util.ArrayList;

public class TimeSlot {
    private int id;
    private int slot;
    private Days day;


    public static ArrayList<TimeSlot>TimeTable;

    public enum Days{Saturday,Monday,Tuesday,Wednesday,Thursday};

    public TimeSlot(int id, int slot, Days day) {
        this.id = id;
        this.slot = slot;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }
}
