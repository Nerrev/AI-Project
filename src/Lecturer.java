import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lecturer {
    private int id;
    private String name;
    private ArrayList<Integer> favorites;


    public static ArrayList<Lecturer> lecturers;

    public Lecturer(int id, String name, ArrayList<Integer> favorites) {
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

    public ArrayList<Integer> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Integer> favorites) {
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


    public static void readLectures(){
        Lecturer.lecturers = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("Lecturers.txt"))) {
            String line = br.readLine();

            while (line != null) {
                String[] info= line.split(",");
                int id= Integer.parseInt(info[0]);
                String name=info[1];
                ArrayList<Integer>favorites=new ArrayList<>();
                for(int i=2;i<info.length;i++)
                    favorites.add(Integer.parseInt(info[i]));

                lecturers.add(new Lecturer(id,name,favorites));
                line = br.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void printLecturers(){
        try {

            PrintWriter writer = new PrintWriter("Lecturers.txt", "UTF-8");
            for(int i=0;i<lecturers.size();i++){
                writer.print(lecturers.get(i).getId()+",");
                writer.print(lecturers.get(i).name);
                for(int j=0;j<lecturers.get(i).getFavorites().size()-1;j++)
                    writer.print(","+lecturers.get(i).getFavorites().get(j));
                writer.println();
            }


            writer.close();



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
