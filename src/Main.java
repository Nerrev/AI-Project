import java.util.ArrayList;
import java.util.List;

public class Main {

    static public int populationSize=300;
    static public double mutationChance=0.5;
    static public double mutationRate=0.2;
    public static void main(String[] args) {
        Generator gen = new Generator();


        for (int l = 0; l < 1; l++) {
/*
            TimeSlot.TimeTable = gen.generateTimeTable();
            Room.rooms = gen.generateRooms(13, 6);
            Course.courses = gen.generateCourses(17, 8);
            Lecturer.lecturers = gen.generateLecturers(14);*/


            TimeSlot.TimeTable = gen.generateTimeTable();
            Room.rooms = gen.generateRooms(5, 3);
            Course.courses = gen.generateCourses(5, 5);
            Lecturer.lecturers = gen.generateLecturers(3);

            ArrayList<Table> population = gen.generatePopulation(populationSize);
            for (int i = 0; i < population.size(); i++)
                population.get(i).calculateFitness();

            int solution = -1;
            int generation = 0;
            while (generation < 10000) {

                 solution = done(population);
                if (solution >= 0) {
                    Lecturer.printLecturers();
                    Course.printCourses();
                    Room.printRooms();
                    break;
                }


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

            }/*
            for (int i = 0; i < population.size(); i++)
                System.out.println(population.get(i).getFitness());*/
            if (solution != -1) {
                System.out.println(population.get(solution).getId() + "/" + population.get(solution).getFitness());
                System.out.println(population.get(solution).getEnteries());
            }
            System.out.println(generation);

        }









  /*      TimeSlot.TimeTable= gen.generateTimeTable();
        Room.rooms= gen.generateRooms(13,6);
        Course.courses= gen.generateCourses(17,8);
        Lecturer.lecturers= gen.generateLecturers(4);
*//*
        ArrayList<Entry> en = new ArrayList<>();
        List<Integer> k=gen.getLabSlots();
        en.add( new Entry(gen.getCourseSlots(0),1,1,1));
        en.add( new Entry(gen.getCourseSlots(0),2,2,1));
        en.add(new Entry(gen.getCourseSlots(k.get(0)),1,1,1));
        en.add( new Entry(gen.getCourseSlots(0),2,3,1));
        en.add(new Entry(gen.getCourseSlots(k.get(0)),1,3,1));
        en.add( new Entry(gen.getCourseSlots(0),2,1,1));
        en.add(new Entry(k,1,1,17));
        Table t= new Table(0,en);

        System.out.println(t.timeRoomConflicts());
        System.out.println(t.lecturerTimeConflict());
        System.out.println(t.hoursViolations());
        t.calculateFitness();
        System.out.println(t.getFitness());*//*

        ArrayList<Table> population = gen.generatePopulation(populationSize);
        for (int i = 0; i < population.size(); i++)
            population.get(i).calculateFitness();


        GeneticsOperations go=new GeneticsOperations();

        Table parent1=gen.generateTable(0);
        Table parent2=gen.generateTable(1);
        parent1.calculateFitness();
        parent2.calculateFitness();
        System.out.println(parent1.getEnteries().size()+"-"+parent2.getEnteries().size());

        Table [] family=go.crossOver(parent1,parent2);

        go.mutate(.3,family[2]);
        Table [] t= go.selection(family);

        System.out.println(t[0].getId()+"-"+t[0].getFitness());
        System.out.println(t[1].getId()+"-"+t[1].getFitness());*/


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
