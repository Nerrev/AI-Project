import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {


    public ArrayList<Table> generatePopulation(int populationSize){
        ArrayList<Table> population=new ArrayList<>(populationSize);

        for(int i=0;i<populationSize;i++)
                population.add(generateTable(i));
        return population;
    }



    public Table generateTable(int id){
        Table table=null;
        ArrayList<Entry> entries= new ArrayList<>();

        int min= Lecturer.lecturers.size()*(2+3);// max for minimum Entries = 2 courses (6 hours) + 3 labs(6 hours) = 12 hours  for each instructor
        int max= Lecturer.lecturers.size()*7;// Max entries(18 hours *  number of instructors) -> 7 is the max combination that won't cross 18 hours (2 courses + 5 labs // 4 courses + 3 labs)

        int numOfEntries=generateRandom(min,max);

        for(int i=0;i<numOfEntries;i++) {
            Entry entry = generateEntry();
            entries.add(entry);

        }
        table= new Table(id,entries);
        return table;
    }

    public Entry generateEntry(){
        int room=generateRandom(0,Room.rooms.size()-1);
        int lecturer=generateRandom(0,Lecturer.lecturers.size()-1);
        ArrayList<Course> lFavorites=Lecturer.lecturers.get(lecturer).getFavorites();
        int courses=lFavorites.get(generateRandom(0,lFavorites.size()-1)).getId();
        List<Integer> timeSlots;
        if(Course.getCourses().get(courses).isLab())
            timeSlots=getLabSlots();
        else
            timeSlots=getCourseSlots(generateRandom(0,TimeSlot.TimeTable.size()-1));
        return new Entry(timeSlots,room,lecturer,courses);
    }





    public ArrayList<Room> generateRooms(int numOfRoomsIN,int numOfRoomsOUT){
        ArrayList<Room> rooms= new ArrayList<>();

        int i=0;

        for(i=0;i<numOfRoomsIN;i++)
            rooms.add(new Room(i,true,"R"+i));
        for(int j=i;j<numOfRoomsOUT+i;j++)
            rooms.add(new Room(j,false,"R"+j));

        return rooms;
    }

    public ArrayList<TimeSlot> generateTimeTable(){
        ArrayList<TimeSlot> TimeTable=new ArrayList<>();

        for(int i=0;i<39;i++)
            TimeTable.add(new TimeSlot(i,getSlot(i),getDay(i)));

        return TimeTable;
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

    public ArrayList<Lecturer> generateLecturers(int numOfLecturers){
        ArrayList<Lecturer> Lecturers= new ArrayList<>();

        for(int i=0;i<numOfLecturers;i++)
            Lecturers.add(new Lecturer(i,"L"+i,generateFavs(Course.courses)));

        return Lecturers;
    }



    public ArrayList<Course> generateFavs(ArrayList<Course> courses){
        ArrayList<Course> favs= new ArrayList<>();


        while(favs.size()<5){
            int index=generateRandom(0,courses.size()-1);
            Course course=courses.get(index);
            if(!course.isLab() && !favs.contains(course) )
                favs.add (course);

        }

        while(favs.size()<10){
            int index=generateRandom(0,courses.size()-1);
            if(courses.get(index).isLab() && !favs.contains(courses.get(index)))
                favs.add(courses.get(index));
        }

        return favs;
    }


    public List<Integer> getCourseSlots(int id){
    List<Integer> timeSlots=new ArrayList<>();

    timeSlots.add(id);

    if(id <9) {
        timeSlots.add(id + 9);
        timeSlots.add(id + 24);
    }

    else if(id>8 && id <18) {
        timeSlots.add(id-9);
        timeSlots.add(id + 15);
    }
    else if (id >17 && id <24){
        timeSlots.add(id + 15);
    }
    else if (id>23 && id<33) {
        timeSlots.add(id-24);
        timeSlots.add(id - 15);
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
            slot=id-18;
        else if (id>23 && id<33)
            slot=id-24;
        else
            slot=id-33;

        return slot;
    }
   static public int generateRandom(int min , int max){
        int n=0;
        Random rand = new Random();
        n=rand.nextInt((max - min) + 1) + min;
        return n;
    }
}
