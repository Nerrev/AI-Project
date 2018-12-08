import java.util.List;
import java.util.Objects;

public class Entry {
    private List<Integer> time;
    private int room;
    private int lecturer;
    private int course;

    public Entry(List<Integer> time, int room, int lecturer, int course) {
        this.time = time;
        this.room = room;
        this.lecturer = lecturer;
        this.course = course;
    }

    public List<Integer> getTime() {
        return time;
    }

    public void setTime(List<Integer> time) {
        this.time = time;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getLecturer() {
        return lecturer;
    }

    public void setLecturer(int lecturer) {
        this.lecturer = lecturer;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }


    public int timeRoomHash(int time) {
        return Objects.hash(time, getRoom());
    }

    public int timeLecturerHash(int time){
        return Objects.hash(time, getLecturer());
    }

    @Override
    public String toString() {
        return "Entry{" +
                "time=" + time +
                ", room=" + room +
                ", lecturer=" + lecturer +
                ", course=" + course +
                '}';
    }
}
