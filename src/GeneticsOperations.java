import java.util.ArrayList;
import java.util.Random;

public class GeneticsOperations {


    public Table selectParent(ArrayList<Table> population){ //selects the best table from a random group of population
        ArrayList<Table> randomGroup=getRandomGroup(population);
        randomGroup.sort(Table::compareTo);
        return randomGroup.get(0);
    }


    public Table[] crossOver(Table parent1, Table parent2){
        Table[] family= new Table[4]; //return parents and children

        family[0]=parent1;
        family[1]=parent2;

        ArrayList<Entry> child1Entries=new ArrayList<>();
        ArrayList<Entry> child2Entries=new ArrayList<>();

        // where to split the gene // from 1 to the half of parent(smaller size parent)
        int crossOverIndex=Generator.generateRandom(1,Math.min(parent1.getEnteries().size(),parent2.getEnteries().size())/2);

        //c1=p1(0-crossOverIndex)+p2(CrossoverIndex-end)
        //c2=p2(0-crossOverIndex)+p1(CrossoverIndex-end)
        for(int i=0;i<crossOverIndex;i++) {
            child1Entries.add(parent1.getEnteries().get(i));
            child2Entries.add(parent2.getEnteries().get(i));
        }

        for(int i=crossOverIndex;i<parent2.getEnteries().size();i++)
            child1Entries.add(parent2.getEnteries().get(i));

        for(int i=crossOverIndex;i<parent1.getEnteries().size();i++)
            child2Entries.add(parent1.getEnteries().get(i));

        //create tables with the new entries
        Table firstChild = new Table(-1,child1Entries);
        Table secondChild = new Table(-1,child2Entries);

        //calculate the fitness for the new table
        firstChild.calculateFitness();
        secondChild.calculateFitness();

        //add children to family
        family[2]=firstChild;
        family[3]=secondChild;


        return family;
    }



public ArrayList<Table> getRandomGroup(ArrayList<Table> population){ //selects a random number of tables from population for parent selection
        Random rand = new Random();
        int min=2,max=population.size()-1,range=max-min+1;
        int groupSize=rand.nextInt((max/2)-min+1) + min;
        ArrayList<Table> randomGroup= new ArrayList<>(groupSize);

        for(int i=0;i<groupSize;i++)
            randomGroup.add(population.get(rand.nextInt(max+1)));


        return randomGroup;
    }

}
