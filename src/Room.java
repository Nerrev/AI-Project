public class Room {
    private int id;
    private boolean inMainBulding;
    private String name;

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
}
