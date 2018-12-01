import java.util.ArrayList;
import java.util.Random;

public class Generator {


    public Generator() {
    }



    public ArrayList<Table> generatePopulation(int populationSize,ArrayList<Lecturer> lecturers,ArrayList<Course> courses,ArrayList<Room> rooms,ArrayList<TimeSlot> timeTable){
        ArrayList<Table> population=new ArrayList<>();

        for(int i=0;i<populationSize;i++)
                population.add(generateTable(i,lecturers,courses,rooms,timeTable));


        //do evaluations maybe

        return population;
    }



    public Table generateTable(int id,ArrayList<Lecturer> lecturers,ArrayList<Course> courses,ArrayList<Room> rooms,ArrayList<TimeSlot> timeTable){
        Table table=null;
        ArrayList<Entry> entries= new ArrayList<>();

        int min= lecturers.size()*2*3;// lectureses*min courses*labs to 12 hours
        int max= lecturers.size()*4*3;// meh

        int numOfEntries=generateRandom(min,max);

        for(int i=min;i<max;i++)
            entries.add(generateEntry(lecturers,courses,rooms,timeTable));


        table= new Table(id,entries);

        return table;
    }



   public ArrayList<Course> generateCourses(int numOfCourses,int numOfLabs){
       ArrayList<Course> Courses= new ArrayList<>();
        int i=0;
       for(i=0; i<numOfCourses;i++)
           Courses.add(new Course(i,false,"C"+i));

       for(int j=i;j<numOfLabs+i;j++)
           Courses.add(new Course(j,true,"L"+(j-i)));


        return Courses;
   }


    public ArrayList<Course> generateFavs(ArrayList<Course> courses){
        ArrayList<Course> favs= new ArrayList<>();

        int i=0;
        while(favs.size()<5){
            int index=generateRandom(0,courses.size()-1);
            if(!courses.get(index).isLab() && !favs.contains(courses.get(index)) ) {
                favs.add (courses.get(index));
                i++;
            }
        }

        while(favs.size()<10){
            int index=generateRandom(0,courses.size()-1);
            if(courses.get(index).isLab() && !favs.contains(courses.get(index))) {
                favs.add(courses.get(index));
                i++;
            }
        }

        return favs;
    }

    public ArrayList<Room> generateRooms(int numOfRooms){
        ArrayList<Room> rooms= new ArrayList<>();

        int numOfIn=(int)(numOfRooms*0.75);
        int numOfOut=numOfRooms-numOfIn;

        int i=0;

        for(i=0;i<numOfIn;i++)
            rooms.add(new Room(i,true,"R"+i));
        for(int j=i;j<numOfOut+i;j++)
            rooms.add(new Room(j,false,"R"+j));


        return rooms;
    }

    public ArrayList<TimeSlot> generateTimeTable(){
        ArrayList<TimeSlot> TimeTable=new ArrayList<>();

        for(int i=0;i<9;i++)
            TimeTable.add(new TimeSlot(i,true));
        for(int i=9;i<15;i++)
            TimeTable.add(new TimeSlot(i,false));


        return TimeTable;
    }

    public ArrayList<Lecturer> generateLecturers(int numOfLecturers,ArrayList<Course> courses){
        ArrayList<Lecturer> Lecturers= new ArrayList<>();

        for(int i=0;i<numOfLecturers;i++)
            Lecturers.add(new Lecturer(i,"L"+i,generateFavs(courses)));

        return Lecturers;
    }

    public Entry generateEntry(ArrayList<Lecturer> lecturers,ArrayList<Course> courses,ArrayList<Room> rooms,ArrayList<TimeSlot> timeTable){

        return new Entry(timeTable.get(generateRandom(0,timeTable.size()-1)).getId(),rooms.get(generateRandom(0,rooms.size()-1)).getId(),lecturers.get(generateRandom(0,lecturers.size()-1)).getId(),courses.get(generateRandom(0,courses.size()-1)).getId());
    }



    public int generateRandom(int min , int max){
        int n=0;
        Random rand = new Random();
        n=rand.nextInt((max - min) + 1) + min;
        return n;
    }
}
