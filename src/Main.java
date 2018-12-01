import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Generator gen= new Generator();

        ArrayList<TimeSlot> timeTable= gen.generateTimeTable();
        ArrayList<Room> rooms= gen.generateRooms(10);
        ArrayList<Course> courses= gen.generateCourses(10,5);
        ArrayList<Lecturer> lecturers= gen.generateLecturers(7,courses);
        Entry e= gen.generateEntry(lecturers,courses,rooms,timeTable);






    }
}
