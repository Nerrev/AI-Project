import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Table implements Comparable<Table> {
    private int id;
    private ArrayList<Entry> entries;
    private int fitness;
    private int hardFitness;
    private double softFitness;



    public Table(int id, ArrayList<Entry> entries) {
        this.id = id;
        this.entries = entries;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Entry> getEnteries() {
        return entries;
    }

    public int getHardFitness() {
        return hardFitness;
    }

    public void setHardFitness(int hardFitness) {
        this.hardFitness = hardFitness;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public double getSoftFitness() {
        return softFitness;
    }

    public void setSoftFitness(double softFitness) {
        this.softFitness = softFitness;
    }

    @Override
    public int compareTo(Table o) {
        if (this == o) return 0;
        if (!(o instanceof Table)) return -2;

        if(this.getFitness() > o.getFitness())
            return 1;
        else if(this.getFitness() == o.getFitness())
            return 0;
        else
            return -1;

    }

    public void calculateFitness(){
        setHardFitness(timeRoomConflicts()+lecturerTimeConflict()+hoursViolations());
        int softConflicts=at8COurses();
        setFitness(getHardFitness()+softConflicts);
        setSoftFitness(1-((double)softConflicts/entries.size()));
    }

    public int timeRoomConflicts(){ //calculate how many entries have the same (room and time)
        int conflicts=0;
        HashMap<Integer,Boolean> ht =new HashMap<>();
        ArrayList<Entry> e = getEnteries();
        for(int i=0;i<e.size();i++){
            Entry en=e.get(i);
            int currentConflicts=0;
            for(int j=0;j<en.getTime().size();j++) {
                int hash = en.timeRoomHash(en.getTime().get(j));
                if (ht.get(hash) == null)
                    ht.put(hash, true);
                else
                    currentConflicts++;
            }
            if(currentConflicts>0)
                conflicts++;

        }
        return conflicts;
    }

     public int hoursViolations(){ //add hours for each teacher in a hash table and then check the violations
        int violations=0;

         int[][]ht =new int[Lecturer.lecturers.size()][2];
         ArrayList<Entry> e = getEnteries();
         for(int i=0;i<e.size();i++) {
             Entry en=e.get(i);
             int hours=0;
             int index=en.getLecturer();
             if(Course.getCourses().get(en.getCourse()).isLab())
                 hours=2;
             else {
                 hours = 3;
                 ht[index][1]+=1;
             }
                 ht[index][0]+=hours;
         }

         for(int i=0;i<Lecturer.lecturers.size();i++) {
             int hours=ht[i][0];
             int courses=ht[i][1];
             if(hours!=0) {
                 if (hours > 18 || hours < 12)
                     violations++;
                 if (courses < 2)
                     violations++;
             }
         }
        return violations;
     }

     public int at8COurses(){
        int coursesAt8=0;
        boolean[] slotsAt8=new boolean [39];//{0,9,18,24,33};
        slotsAt8[0]=true;
        slotsAt8[9]=true;
        slotsAt8[18]=true;
        slotsAt8[24]=true;
        slotsAt8[33]=true;
        for(int i=0;i<entries.size();i++){
            int slot=entries.get(i).getTime().get(0);
            if(slotsAt8[slot])
                coursesAt8++;
        }

        return coursesAt8;
     }
     public int  lecturerViolations(){
         int violations=0;
         HashMap<Integer, List<Integer>> ht =new HashMap<>();
         HashMap<Integer,Integer> ht2 =new HashMap<>();
         ArrayList<Entry> e = getEnteries();
         for(int i=0;i<e.size();i++) {
             boolean flag=false;
             Entry en=e.get(i);
             int hash=en.getLecturer();
             List<Integer>v=ht.get(hash);
             if (v == null)
                 ht.put(hash,en.getTime());
             else {
                 for(int k=0;k<en.getTime().size();k++) {
                     if (v.contains(en.getTime().get(k))) {
                         violations++;
                         flag=true;
                         break;
                     }
                 }
                 if(!flag){
                 for(int k=0;k<en.getTime().size();k++) {
                     if (v.contains(en.getTime().get(k) + 1) || v.contains(en.getTime().get(k) - 1)) {
                         if(ht2.get(en.getLecturer()) == null) {
                             ht2.put(en.getLecturer(), 1);
                         }
                         else {
                             ht2.put(en.getLecturer(), ht2.get(en.getLecturer())+1);
                         }
                         break;
                     }
                 }
                 }
                 v.addAll(en.getTime());
             }
         }
         for(int i=0;i<Lecturer.lecturers.size();i++) {
             int hours=ht2.getOrDefault(i,0);
             if (hours > 3)
                 violations++;
         }

         return violations;
     }

     public void printTable(){
        ArrayList<Entry> [] tables= new ArrayList[Lecturer.lecturers.size()];
        for(int i=0;i<tables.length;i++)
                tables[i]=new ArrayList<>();

        ArrayList<TimeSlot> timeTable=TimeSlot.TimeTable;
         for(int i=0;i<entries.size();i++){
            Entry en=entries.get(i);
            int  lecturer=en.getLecturer();
            tables[lecturer].add(en);
        }


        for(int i=0;i<tables.length;i++){
            String[] saturday= new String[9];
            String[] monday= new String[9];
            String[] tuesday= new String[6];
            String[] wednesday= new String[9];
            String[] thursday= new String[6];
            ArrayList<String[]> printable=new ArrayList<>();
            printable.add(saturday);
            printable.add(monday);
            printable.add(tuesday);
            printable.add(wednesday);
            printable.add(thursday);
            ArrayList<Entry> cTable=tables[i];
            for(int j=0;j<cTable.size();j++){
                Entry ent=cTable.get(j);
                List<Integer>slots=ent.getTime();

                for(int k=0;k<slots.size();k++){
                    TimeSlot slot=timeTable.get(slots.get(k));
                    String[] day=printable.get(slot.getDay());
                    int daySlot=slot.getSlot();
                    String room=Room.rooms.get(ent.getRoom()).getName();
                    String course= Course.courses.get(ent.getCourse()).getName();
                    day[daySlot]="|  *"+course+" - "+room+"*  |";
                }


            }

            String[] week={"Saturday","Monday","Tuesday","Wednesday","Thursday"};
            String[] SMW={"8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00"};
            String[] TR={"8:00","9:30","11:00","12:30","2:00","3:30","5:00"};
            /*String[] SMW={"8:00-9:00","9:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00"};
            String[] TR={"8:00-9:30","9:30-11:00","11:00-12:30","12:30-2:00","2:00-3:30","3:30-5:00"};
            */String lecturer=Lecturer.lecturers.get(i).getName();

            System.out.println("Weakly Table For * "+lecturer+" * : Soft Fitness="+new DecimalFormat("#.##").format(getSoftFitness()));

            for(int p=0;p<week.length;p++){
                System.out.print("- "+week[p]+": ");
                for(int m=0;m<printable.get(p).length;m++) {
                    String t="";
                    if(p == 0 || p==1 || p==3)
                        t=SMW[m];
                    else
                        t=TR[m];
                    System.out.print(t);
                    String x=printable.get(p)[m];
                    if(x == null)
                        System.out.print("|  ------  |");
                    else
                        System.out.print(x);
                }
                if(p == 0 || p==1 || p==3)
                    System.out.print(SMW[9]);
                else
                    System.out.print(TR[6]);
                System.out.println("#");
            }

            System.out.println("_______________________________________________________________________________________");

        }




     }


    public int lecturerTimeConflict(){//calculate how many classes a teacher has in the same time slot
        int conflicts=0;
        HashMap<Integer,Boolean> ht =new HashMap<>();
        ArrayList<Entry> e = getEnteries();
        for(int i=0;i<e.size();i++) {
            Entry en=e.get(i);
            int currentConflicts=0;
            for(int j=0;j<en.getTime().size();j++) {
                int hash = en.timeLecturerHash(en.getTime().get(j));
                if (ht.get(hash) == null)
                    ht.put(hash, true);
                else
                    currentConflicts++;
            }
            if(currentConflicts>0)
                conflicts++;
        }
        return conflicts;
    }
}
