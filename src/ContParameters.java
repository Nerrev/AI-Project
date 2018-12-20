import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ContParameters {
    @FXML
    TextField PopulationSize;

    @FXML
    TextField MutationRate;

    @FXML
    TextField NumberOfTries;
    @FXML
    TextField MaxGenerations;
    @FXML
    TextField MutationChance;

    @FXML
    TextField AmLectures;

    @FXML
    RadioButton FirstSolution;

    @FXML
    RadioButton OptimalSolution;


    @FXML
    public void initialize() {

        PopulationSize.setText("300");
        MutationChance.setText("0.5");
        MutationRate.setText("0.3");
        MaxGenerations.setText("10000");
        NumberOfTries.setText("150");
        AmLectures.setText("0.7");
        FirstSolution.selectedProperty().setValue(true);
    }

    public void go(){
        Main.populationSize=Integer.parseInt(PopulationSize.getText());
        Main.maxGenerations=Integer.parseInt(MaxGenerations.getText());
        Main.maxTries=Integer.parseInt(NumberOfTries.getText());
        Main.mutationChance= Double.parseDouble(MutationChance.getText());
        Main.mutationRate= Double.parseDouble(MutationRate.getText());
        Main.softConstraintsGoal= Double.parseDouble(AmLectures.getText());
        if(FirstSolution.isSelected())
            Main.firstSolution=true;
        else
            Main.firstSolution=false;
        w();
        Main.Work();
        pr();
    }
    public void pr(){
        try {
            Parent pane = FXMLLoader.load(
                    getClass().getResource("table.fxml"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void w(){
        try {
            Parent pane = FXMLLoader.load(
                    getClass().getResource("wait.fxml"));
            Main.primaryStage.getScene().setRoot(pane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
