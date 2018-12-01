public class Table {
    private int id;
    private Entry[] entries;
    private int fitness;

    public Table(int id, Entry[] entries) {
        this.id = id;
        this.entries = entries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entry[] getEnteries() {
        return entries;
    }

    public void setEnteries(Entry[] entries) {
        this.entries = entries;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
