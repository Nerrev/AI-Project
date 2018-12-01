import java.util.Objects;

public class Course {
    private int id;
    private boolean lab;
    private String name;


    public Course(int id, boolean lab, String name) {
        this.id = id;
        this.lab = lab;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLab() {
        return lab;
    }

    public void setLab(boolean lab) {
        this.lab = lab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId() == course.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", lab=" + lab +
                ", name='" + name + '\'' +
                '}';
    }
}
