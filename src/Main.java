import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Generator gen= new Generator();

        ArrayList<TimeSlot> timeTable= gen.generateTimeTable();
        ArrayList<Room> rooms= gen.generateRooms(10);
        ArrayList<Course> courses= gen.generateCourses(10,5);
        ArrayList<Lecturer> lecturers= gen.generateLecturers(7,courses);

        Table x =gen.generateTable(0,lecturers,courses,rooms,timeTable);

        System.out.println(x.calcFitness());


    }
}
