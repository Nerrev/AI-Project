import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;



public class Main extends Application {


    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        this.primaryStage=primaryStage;
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    static public int populationSize=300;
    static public double mutationChance=0.5;
    static public double mutationRate=0.3;
    static public int maxGenerations=10000;
    static public int maxTries=150;
    static public double softConstraintsGoal=0.7;
    static public boolean firstSolution=true;

    public static ArrayList<Table> Solutions=new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }


    public static void Work(){
        Generator gen = new Generator();
        TimeSlot.TimeTable=gen.generateTimeTable();
        Room.readRooms();
        Course.readCourses();
        Lecturer.readLectures();
        for (int l = 0; l < maxTries; l++) {
            boolean flag=false;
            ArrayList<Table> population = gen.generatePopulation(populationSize);
            for (int i = 0; i < population.size(); i++)
                population.get(i).calculateFitness();

            int generation = 0;
            while (generation < maxGenerations) {

                if(findSolutions(population) && firstSolution) {
                    flag=true;
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
                //System.out.println(generation);
            }
            if(flag)
                break;

        }
  /*      if(Solutions.isEmpty()){
            System.out.println("Failed ! No Solution was Found.");
        }
        else {
            Collections.sort(Solutions);
            Solutions.get(0);
        }*/

    }

    public static boolean findSolutions(ArrayList<Table>population){
        for(int i=0;i<population.size();i++){
            int hardFitness=population.get(i).getHardFitness();
            double softFitness=population.get(i).getSoftFitness();
            if(hardFitness == 0 && softFitness<=softConstraintsGoal){
                Table t=population.get(i);
                Solutions.add(population.get(i));
                population.remove(t.getId());
                return true;
            }
        }
        return false;
    }

}
