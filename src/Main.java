import java.util.ArrayList;
public class Main {

    static public int populationSize=100;
    static public double mutationChance=0.3;
    public static void main(String[] args) {
        Generator gen= new Generator();

        TimeSlot.TimeTable= gen.generateTimeTable();
        Room.rooms= gen.generateRooms(10);
        Course.courses= gen.generateCourses(10,5);
        Lecturer.lecturers= gen.generateLecturers(7);

        ArrayList<Table>population=gen.generatePopulation(populationSize);

        for(int i =0;i<population.size();i++)
            population.get(i).calculateFitness();


        GeneticsOperations go= new GeneticsOperations();

        Table parent1=go.selectParent(population);
        System.out.println("P1("+parent1.getId()+"):"+" Size:"+parent1.getEnteries().size() + " fitness:"+parent1.getFitness());

        Table parent2=go.selectParent(population);
        System.out.println("P2("+parent2.getId()+"):"+" Size:"+parent2.getEnteries().size() + " fitness:"+parent2.getFitness());


        Table[] family=go.crossOver(parent1,parent2);


        System.out.println(family[2].getEnteries().size()+" "+family[2].getFitness()+" - "+family[3].getEnteries().size()+" "+family[3].getFitness());

        if(Math.random()>mutationChance)
            go.mutate(0.5,family[2]);
        if(Math.random()>mutationChance)
            go.mutate(0.5,family[3]);

        System.out.println(family[2].getEnteries().size()+" "+family[2].getFitness()+" - "+family[3].getEnteries().size()+" "+family[3].getFitness());


    }
}
