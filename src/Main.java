import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static public int populationSize=100;
    public static void main(String[] args) {
        Generator gen= new Generator();

        ArrayList<TimeSlot> timeTable= gen.generateTimeTable();
        ArrayList<Room> rooms= gen.generateRooms(10);
        ArrayList<Course> courses= gen.generateCourses(10,5);
        ArrayList<Lecturer> lecturers= gen.generateLecturers(7,courses);

        ArrayList<Table>population=gen.generatePopulation(populationSize,lecturers,courses,rooms,timeTable);

        for(int i =0;i<population.size();i++)
            population.get(i).calculateFitness();


        GeneticsOperations go= new GeneticsOperations();

        Table parent1=go.selectParent(population);
        System.out.println("P1("+parent1.getId()+"):"+" Size:"+parent1.getEnteries().size() + " fitness:"+parent1.getFitness());

        Table parent2=go.selectParent(population);
        System.out.println("P2("+parent2.getId()+"):"+" Size:"+parent2.getEnteries().size() + " fitness:"+parent2.getFitness());


        Table[] family=go.crossOver(parent1,parent2);


        System.out.println(family[2].getEnteries().size()+" "+family[2].getFitness()+" - "+family[3].getEnteries().size()+" "+family[3].getFitness());



    }
}
