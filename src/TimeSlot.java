import java.util.ArrayList;

public class TimeSlot {
    private int id;
    private int slot;
    private int day;


    public static ArrayList<TimeSlot>TimeTable;

    public enum Days{Saturday,Monday,Tuesday,Wednesday,Thursday};

    public TimeSlot(int id, int slot, int day) {
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
