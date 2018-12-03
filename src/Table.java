import java.util.ArrayList;
import java.util.HashMap;

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
    public int calcFitness(){
        int count=0;
        HashMap<Integer,Entry> ht =new HashMap<>();
        ArrayList<Entry> e = getEnteries();
        for(int i=0;i<e.size();i++){
            if(ht.get(e.get(i).hashCode()) == null)
                ht.put(e.get(i).hashCode(),e.get(i));
            else
                count++;
        }

       setFitness(count);
        return count;
    }
}
