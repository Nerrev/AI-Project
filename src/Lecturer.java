import java.io.PrintWriter;
import java.util.ArrayList;

public class Lecturer {
    private int id;
    private String name;
    private ArrayList<Course> favorites;


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


    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", favorites=" + favorites +
                '}';
    }

    public static void printLecturers(){
        try {

            PrintWriter writer = new PrintWriter("Lecturers.txt", "UTF-8");
            for(int i=0;i<lecturers.size();i++){
                writer.print(lecturers.get(i).name);
                for(int j=0;j<lecturers.get(i).getFavorites().size()-1;j++)
                    writer.print(","+lecturers.get(i).getFavorites().get(j).getId());
                writer.println();
            }


            writer.close();



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
