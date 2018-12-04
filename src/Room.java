import java.io.PrintWriter;
import java.util.ArrayList;

public class Room {
    private int id;
    private boolean inMainBulding;
    private String name;



    public static ArrayList<Room>rooms;

    public Room(int id, boolean inMainBulding, String name) {
        this.id = id;
        this.inMainBulding = inMainBulding;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean isInMainBulding() {
        return inMainBulding;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInMainBulding(boolean inMainBulding) {
        this.inMainBulding = inMainBulding;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", inMainBulding=" + inMainBulding +
                ", name='" + name + '\'' +
                '}';
    }


    public static void printRooms(){
        try {

            PrintWriter writer = new PrintWriter("Rooms.txt", "UTF-8");
            for(int i=0;i<rooms.size();i++)
                writer.println(rooms.get(i).name+","+(rooms.get(i).isInMainBulding()?"M":"O"));



            writer.close();



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
