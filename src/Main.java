import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static public int populationSize=300;
    static public double mutationChance=0.5;
    static public double mutationRate=0.3;
    static public int maxGenerations=10000;
    static public int maxTries=50;
    static public double softConstraintsGoal=0.6;

    public static ArrayList<Table> Solutions=new ArrayList<>();

    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        Generator gen = new Generator();
        TimeSlot.TimeTable=gen.generateTimeTable();
        Room.readRooms();
        Course.readCourses();
        Lecturer.readLectures();
        for (int l = 0; l < maxTries; l++) {
            ArrayList<Table> population = gen.generatePopulation(populationSize);
            for (int i = 0; i < population.size(); i++)
                population.get(i).calculateFitness();

            int generation = 0;
            while (generation < maxGenerations) {

                if(findSolutions(population))
                    break;


                GeneticsOperations go = new GeneticsOperations();

                Table parent1 = go.selectParent(population);
                //System.out.println("P1(" + parent1.getId() + "):" + " Size:" + parent1.getEnteries().size() + " fitness:" + parent1.getFitness());

                Table parent2 = go.selectParent(population);
                //System.out.println("P2(" + parent2.getId() + "):" + " Size:" + parent2.getEnteries().size() + " fitness:" + parent2.getFitness());


                Table[] family = go.crossOver(parent1, parent2);

                //System.out.println(family[2].getEnteries().size() + " " + family[2].getFitness() + " - " + family[3].getEnteries().size() + " " + family[3].getFitness());

                if (Math.random() < mutationChance)
                    go.mutate(mutationRate, family[2]);
                if (Math.random() < mutationChance)
                    go.mutate(mutationRate, family[3]);

                //  System.out.println(family[2].getEnteries().size() + " " + family[2].getFitness() + " - " + family[3].getEnteries().size() + " " + family[3].getFitness());


                Table[] selected = go.selection(family);

                for (int i = 0; i < selected.length; i++)
                    population.set(selected[i].getId(), selected[i]);

                generation++;
                //System.out.println(generation);
            }
            System.out.println("Computing...");
        }
        if(Solutions.isEmpty())
            System.out.println("Failed ! No Solution was Found.");

        for(int i=0;i<Solutions.size();i++)
            Solutions.get(i).printTable();

    }


    public static boolean findSolutions(ArrayList<Table>population){
        for(int i=0;i<population.size();i++){
            int hardFitness=population.get(i).getHardFitness();
            double softFitness=population.get(i).getSoftFitness();
            if(hardFitness == 0 && softFitness>softConstraintsGoal){
                Solutions.add(population.get(i));
                return true;
            }
        }
        return false;
    }
}
