import java.util.ArrayList;

public class Lecturer {
    private int id;
    private String name;
    private ArrayList<Course> favorites;
    private ArrayList<Entry> schedule;

    public void addToSchedule(){

    }

    public static ArrayList<Lecturer> lecturers;

    public Lecturer(int id, String name, ArrayList<Course> favorites) {
        this.id = id;
        this.name = name;
        this.favorites = favorites;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Course> favorites) {
        this.favorites = favorites;
    }

    public ArrayList<Entry> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Entry> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", favorites=" + favorites +
                '}';
    }
}
