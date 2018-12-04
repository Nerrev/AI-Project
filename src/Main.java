import java.util.ArrayList;
public class Main {

    static public int populationSize=1000;
    static public double mutationChance=0.3;
    public static void main(String[] args) {
        Generator gen= new Generator();

        TimeSlot.TimeTable= gen.generateTimeTable();
        Room.rooms= gen.generateRooms(10);
        Course.courses= gen.generateCourses(10,5);
        Lecturer.lecturers= gen.generateLecturers(7);

        ArrayList<Table>population=gen.generatePopulation(populationSize);
        for (int i = 0; i < population.size(); i++)
            population.get(i).calculateFitness();

        int solution=-1;
        int generation=0;
        while(generation < 2000000) {

            int k= done(population);
            if(k>=0)
                break;


            GeneticsOperations go = new GeneticsOperations();

            Table parent1 = go.selectParent(population);
            //System.out.println("P1(" + parent1.getId() + "):" + " Size:" + parent1.getEnteries().size() + " fitness:" + parent1.getFitness());

            Table parent2 = go.selectParent(population);
            //System.out.println("P2(" + parent2.getId() + "):" + " Size:" + parent2.getEnteries().size() + " fitness:" + parent2.getFitness());


            Table[] family = go.crossOver(parent1, parent2);


            //System.out.println(family[2].getEnteries().size() + " " + family[2].getFitness() + " - " + family[3].getEnteries().size() + " " + family[3].getFitness());

            if (Math.random() < mutationChance)
                go.mutate(0.5, family[2]);
            if (Math.random() < mutationChance)
                go.mutate(0.5, family[3]);

          //  System.out.println(family[2].getEnteries().size() + " " + family[2].getFitness() + " - " + family[3].getEnteries().size() + " " + family[3].getFitness());


            Table[] selected = go.selection(family);

            for (int i = 0; i < selected.length; i++)
                population.set(selected[i].getId(), selected[i]);

            generation++;

        }
        for(int i=0;i< population.size();i++)
        System.out.println(population.get(i).getFitness());
        System.out.println(generation);
    }







    public static int done(ArrayList<Table>population){
        int f=-1;
        for(int i=0;i<population.size();i++)
            if(population.get(i).getFitness() == 0) {
                f = i;
                return f;
            }

            return f;
    }
}
