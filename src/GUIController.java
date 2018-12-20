import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class GUIController{
    @FXML
    AnchorPane rootPane=new AnchorPane();
    @FXML
    Label S1=new Label();
    @FXML
    Label S2=new Label();
    @FXML
    Label S3=new Label();
    @FXML
    Label S4=new Label();
    @FXML
    Label S5=new Label();
    @FXML
    Label S6=new Label();
    @FXML
    Label S7=new Label();
    @FXML
    Label S8=new Label();
    @FXML
    Label S9=new Label();

    @FXML
    Label M1=new Label();
    @FXML
    Label M2=new Label();
    @FXML
    Label M3=new Label();
    @FXML
    Label M4=new Label();
    @FXML
    Label M5=new Label();
    @FXML
    Label M6=new Label();
    @FXML
    Label M7=new Label();
    @FXML
    Label M8=new Label();
    @FXML
    Label M9=new Label();



    @FXML
    Label W1=new Label();
    @FXML
    Label W2=new Label();
    @FXML
    Label W3=new Label();
    @FXML
    Label W4=new Label();
    @FXML
    Label W5=new Label();
    @FXML
    Label W6=new Label();
    @FXML
    Label W7=new Label();
    @FXML
    Label W8=new Label();
    @FXML
    Label W9=new Label();


    @FXML
    Label T1=new Label();
    @FXML
    Label T2=new Label();
    @FXML
    Label T3=new Label();
    @FXML
    Label T4=new Label();
    @FXML
    Label T5=new Label();
    @FXML
    Label T6=new Label();

    @FXML
    Label R1=new Label();
    @FXML
    Label R2=new Label();
    @FXML
    Label R3=new Label();
    @FXML
    Label R4=new Label();
    @FXML
    Label R5=new Label();
    @FXML
    Label R6=new Label();


    @FXML
    public void initialize() {
        printT(Main.Solutions.get(0));
    }


    public void  printT(Table tb) {
        ArrayList<Entry>[] tables = new ArrayList[Lecturer.lecturers.size()];
        ArrayList<Entry> entries = tb.getEnteries();
        for (int i = 0; i < tables.length; i++)
            tables[i] = new ArrayList<>();

        ArrayList<TimeSlot> timeTable = TimeSlot.TimeTable;
        for (int i = 0; i < entries.size(); i++) {
            Entry en = entries.get(i);
            int lecturer = en.getLecturer();
            tables[lecturer].add(en);
        }

        for (int i = 0; i < tables.length; i++) {
            String[] saturday = new String[9];
            String[] monday = new String[9];
            String[] tuesday = new String[6];
            String[] wednesday = new String[9];
            String[] thursday = new String[6];
            ArrayList<String[]> printable = new ArrayList<>();
            printable.add(saturday);
            printable.add(monday);
            printable.add(tuesday);
            printable.add(wednesday);
            printable.add(thursday);
            ArrayList<Entry> cTable = tables[i];
            for (int j = 0; j < cTable.size(); j++) {
                Entry ent = cTable.get(j);
                List<Integer> slots = ent.getTime();

                for (int k = 0; k < slots.size(); k++) {
                    TimeSlot slot = timeTable.get(slots.get(k));
                    String[] day = printable.get(slot.getDay());
                    int daySlot = slot.getSlot();
                    String room = Room.rooms.get(ent.getRoom()).getName();
                    String course = Course.courses.get(ent.getCourse()).getName();
                    day[daySlot] = "*" + course + " - " + room + "* ";
                }

            }

            String color="-fx-border-color:black; -fx-background-color: #498749;";
            
            S1.setText(printable.get(0)[0]);
            S1.setStyle(color);
            S2.setText(printable.get(0)[1]);
            S2.setStyle(color);
            S3.setText(printable.get(0)[2]);
            S3.setStyle(color);
            S4.setText(printable.get(0)[3]);
            S4.setStyle(color);
            S5.setText(printable.get(0)[4]);
            S5.setStyle(color);
            S6.setText(printable.get(0)[5]);
            S6.setStyle(color);
            S7.setText(printable.get(0)[6]);
            S7.setStyle(color);
            S8.setText(printable.get(0)[7]);
            S8.setStyle(color);
            S9.setText(printable.get(0)[8]);
            S9.setStyle(color);



           int day=1;

            M1.setText(printable.get(day)[0]);
            M1.setStyle(color);
            M2.setText(printable.get(day)[1]);
            M2.setStyle(color);
            M3.setText(printable.get(day)[2]);
            M3.setStyle(color);
            M4.setText(printable.get(day)[3]);
            M4.setStyle(color);
            M5.setText(printable.get(day)[4]);
            M5.setStyle(color);
            M6.setText(printable.get(day)[5]);
            M6.setStyle(color);
            M7.setText(printable.get(day)[6]);
            M7.setStyle(color);
            M8.setText(printable.get(day)[7]);
            M8.setStyle(color);
            M9.setText(printable.get(day)[8]);
            M9.setStyle(color);




            day=2;

            T1.setText(printable.get(day)[0]);
            T1.setStyle(color);
            T2.setText(printable.get(day)[1]);
            T2.setStyle(color);
            T3.setText(printable.get(day)[2]);
            T3.setStyle(color);
            T4.setText(printable.get(day)[3]);
            T4.setStyle(color);
            T5.setText(printable.get(day)[4]);
            T5.setStyle(color);
            T6.setText(printable.get(day)[5]);
            T6.setStyle(color);




             day=3;

            W1.setText(printable.get(day)[0]);
            W1.setStyle(color);
            W2.setText(printable.get(day)[1]);
            W2.setStyle(color);
            W3.setText(printable.get(day)[2]);
            W3.setStyle(color);
            W4.setText(printable.get(day)[3]);
            W4.setStyle(color);
            W5.setText(printable.get(day)[4]);
            W5.setStyle(color);
            W6.setText(printable.get(day)[5]);
            W6.setStyle(color);
            W7.setText(printable.get(day)[6]);
            W7.setStyle(color);
            W8.setText(printable.get(day)[7]);
            W8.setStyle(color);
            W9.setText(printable.get(day)[8]);
            W9.setStyle(color);



            day=4;

            R1.setText(printable.get(day)[0]);
            R1.setStyle(color);
            R2.setText(printable.get(day)[1]);
            R2.setStyle(color);
            R3.setText(printable.get(day)[2]);
            R3.setStyle(color);
            R4.setText(printable.get(day)[3]);
            R4.setStyle(color);
            R5.setText(printable.get(day)[4]);
            R5.setStyle(color);
            R6.setText(printable.get(day)[5]);
            R6.setStyle(color);


            print();


        }


    }

    public void print() {

        // Default printer object.
        Printer printer = Printer.getDefaultPrinter();

        // Print page layout object.
        // * Set "LANDSCAPE" as the page orientation for the convenience for the test.
        //   If the output pdf has the text information, the output file is shown in a PORTRAIT mode.
        //   If not, it will be shown in a LANDSCAPE mode.
        PageLayout layout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);

        // Create a printer job.
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            // Set the job name.
            job.getJobSettings().setJobName("TestPrint");


//            if (job.showPrintDialog(this.rootPane.getScene().getWindow())) {
                // Print out the specified pane.

                job.printPage(layout,this.rootPane);
  //          }
    //        else {
        //        System.out.println("Print canceled.");
      //      }

            // Finish the print job.
            job.endJob();
        }
    }
}
