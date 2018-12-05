import java.util.ArrayList;
import java.util.List;
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
            int room=Room.rooms.get(generateRandom(0,Room.rooms.size()-1)).getId();
            int lecturer=Lecturer.lecturers.get(generateRandom(0,Lecturer.lecturers.size()-1)).getId();
            int courses=Lecturer.lecturers.get(lecturer).getFavorites().get(generateRandom(0,Lecturer.lecturers.get(lecturer).getFavorites().size()-1)).getId();
           List<Integer> timeSlots;
            if(Course.getCourses().get(courses).isLab())
                timeSlots=getLabSlots();
            else
                timeSlots=getCourseSlots(generateRandom(0,TimeSlot.TimeTable.size()-1));
        return new Entry(timeSlots,room,lecturer,courses);
    }
public List<Integer> getCourseSlots(int id){
    List<Integer> timeSlots=new ArrayList<>();

    timeSlots.add(id);

    if(id <9) {
        timeSlots.add(id + 9);
        timeSlots.add(id + 15);
    }

    else if(id>8 && id <18) {
        timeSlots.add(id + 15);
        timeSlots.add(id-9);
    }
    else if (id >17 && id <24){
        timeSlots.add(id + 15);
    }
    else if (id>23 && id<33) {
        timeSlots.add(id - 15);
        timeSlots.add(id-9);
    }
    else
        timeSlots.add(id - 15);

    return  timeSlots;

}
public List<Integer>getLabSlots(){
    List<Integer> timeSlots=new ArrayList<>();

    int n= generateRandom(1,9);
    switch (n) {
        case 1: timeSlots.add(6);
                timeSlots.add(7);
                timeSlots.add(8);
            break;
        case 2:  timeSlots.add(15);
                timeSlots.add(16);
                timeSlots.add(17);
            break;
        case 3:  timeSlots.add(18);
                timeSlots.add(19);
            break;
        case 4:  timeSlots.add(20);
                timeSlots.add(21);
            break;
        case 5:  timeSlots.add(22);
                timeSlots.add(23);
            break;
        case 6:  timeSlots.add(30);
                 timeSlots.add(31);
                 timeSlots.add(32);
            break;
        case 7:  timeSlots.add(33);
                timeSlots.add(34);
            break;
        case 8:  timeSlots.add(35);
                 timeSlots.add(36);
            break;
        case 9:  timeSlots.add(37);
                 timeSlots.add(38);
            break;
    }

    return timeSlots;

}


   static public int generateRandom(int min , int max){
        int n=0;
        Random rand = new Random();
        n=rand.nextInt((max - min) + 1) + min;
        return n;
    }
}
