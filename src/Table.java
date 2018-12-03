import java.util.ArrayList;
import java.util.HashMap;

public class Table implements Comparable<Table> {
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

    @Override
    public int compareTo(Table o) {
        if (this == o) return 0;
        if (!(o instanceof Table)) return -2;

        if(this.getFitness() > o.getFitness())
            return 1;
        else if(this.getFitness() == o.getFitness())
            return 0;
        else
            return -1;

    }

    public int calculateFitness(){
        int count=0;
        HashMap<Integer,Entry> ht =new HashMap<>();
        ArrayList<Entry> e = getEnteries();
        for(int i=0;i<e.size();i++)
            if(ht.get(e.get(i).hashCode()) == null)
                ht.put(e.get(i).hashCode(),e.get(i));
            else
                count++;

       setFitness(count);
        return count;
    }
}
