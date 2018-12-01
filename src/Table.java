import java.util.ArrayList;

public class Table {
    private int id;
    private ArrayList<Entry> entries;
    private int fitness;

    public Table(int id, ArrayList<Entry> entries) {
        this.id = id;
        this.entries = entries;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Entry> getEnteries() {
        return entries;
    }

    public void setEnteries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
