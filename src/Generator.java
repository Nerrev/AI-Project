import java.util.ArrayList;
import java.util.Random;

public class Generator {


    public Generator() {
    }



    public ArrayList<Table> generatePopulation(int populationSize){
        ArrayList<Table> population=new ArrayList<>(populationSize);

        for(int i=0;i<populationSize;i++)
                population.add(generateTable(i));
        return population;
    }



    public Table generateTable(int id){
        Table table=null;
        ArrayList<Entry> entries= new ArrayList<>();

        int min= Lecturer.lecturers.size()*2*3;// lecturers*min courses*labs to 12 hours
        int max= Lecturer.lecturers.size()*4*3;// meh

        int numOfEntries=generateRandom(min,max);

        for(int i=0;i<numOfEntries;i++) {
            Entry entry = generateEntry();
            entries.add(entry);

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

        for(int i=0;i<39;i++)
            TimeTable.add(new TimeSlot(i,getSlot(i),getDay(i)));

        return TimeTable;
    }

    public TimeSlot.Days getDay(int id){
        TimeSlot.Days day;

        if(id <9)
            day= TimeSlot.Days.Saturday;
        else if(id>8 && id <18)
            day= TimeSlot.Days.Monday;
        else if (id >17 && id <24)
            day= TimeSlot.Days.Tuesday;
        else if (id>23 && id<33)
            day= TimeSlot.Days.Wednesday;
        else
            day= TimeSlot.Days.Thursday;

        return day;
    }

    public int getSlot(int id){
        int slot=0;


        if(id <9)
            slot=id;
        else if(id>8 && id <18)
            slot=id-9;
        else if (id >17 && id <24)
            slot=id-15;
        else if (id>23 && id<33)
            slot=id-24;
        else
            slot=id-30;

        return slot;
    }


    public ArrayList<Lecturer> generateLecturers(int numOfLecturers){
        ArrayList<Lecturer> Lecturers= new ArrayList<>();

        for(int i=0;i<numOfLecturers;i++)
            Lecturers.add(new Lecturer(i,"L"+i,generateFavs(Course.courses)));

        return Lecturers;
    }

    public Entry generateEntry(){
            int timeSlot=TimeSlot.TimeTable.get(generateRandom(0,TimeSlot.TimeTable.size()-1)).getId();
            int room=Room.rooms.get(generateRandom(0,Room.rooms.size()-1)).getId();
            int lecturer=Lecturer.lecturers.get(generateRandom(0,Lecturer.lecturers.size()-1)).getId();
            int courses=Lecturer.lecturers.get(lecturer).getFavorites().get(generateRandom(0,Lecturer.lecturers.get(lecturer).getFavorites().size()-1)).getId();
        return new Entry(timeSlot,room,lecturer,courses);
    }



   static public int generateRandom(int min , int max){
        int n=0;
        Random rand = new Random();
        n=rand.nextInt((max - min) + 1) + min;
        return n;
    }
}
