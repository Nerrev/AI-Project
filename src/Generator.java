import java.util.ArrayList;
import java.util.Random;

public class Generator {


    public Generator() {
    }



    public ArrayList<Table> generatePopulation(int populationSize){
        ArrayList<Table> population=new ArrayList<>(populationSize);

        for(int i=0;i<populationSize;i++)
                population.add(generateTable(i));


        //do evaluations maybe

        return population;
    }



    public Table generateTable(int id){
        Table table=null;
        ArrayList<Entry> entries= new ArrayList<>();

        int min= Lecturer.lecturers.size()*2*3;// lectureses*min courses*labs to 12 hours
        int max= Lecturer.lecturers.size()*4*3;// meh

        int numOfEntries=generateRandom(min,max);

        for(int i=0;i<numOfEntries;i++) {
            entries.add(generateEntry());

        }
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

    public ArrayList<Lecturer> generateLecturers(int numOfLecturers){
        ArrayList<Lecturer> Lecturers= new ArrayList<>();

        for(int i=0;i<numOfLecturers;i++)
            Lecturers.add(new Lecturer(i,"L"+i,generateFavs(Course.courses)));

        return Lecturers;
    }

    public Entry generateEntry(){

        return new Entry(TimeSlot.TimeTable.get(generateRandom(0,TimeSlot.TimeTable.size()-1)).getId(),Room.rooms.get(generateRandom(0,Room.rooms.size()-1)).getId(),Lecturer.lecturers.get(generateRandom(0,Lecturer.lecturers.size()-1)).getId(),Course.courses.get(generateRandom(0,Course.courses.size()-1)).getId());
    }



   static public int generateRandom(int min , int max){
        int n=0;
        Random rand = new Random();
        n=rand.nextInt((max - min) + 1) + min;
        return n;
    }
}
