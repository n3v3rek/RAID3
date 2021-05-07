package Java.Main;

import Java.Model.SetOfDisks;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static SetOfDisks setOfDisks;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/startStage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("RAID3 - Nadmiarowa macież niezależnych dysków");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void setFirstDisc(String disc){ setOfDisks.setDiscOne(disc); }

    public static void setSecondDisc(String disc){
        setOfDisks.setDiscTwo(disc);
    }


    public static void createControlDisc(String disc){
        setOfDisks.createControlDisc();
    }


    public static String getFirstDisc(){
        if (setOfDisks != null) {
           return setOfDisks.getDiscOne();
        }
        return "Error in disc one - no disc";
    }

    public static String getSecondDisc(){
        if (setOfDisks != null) {
            return setOfDisks.getDiscTwo();
        }
        return "Error in disc two - no disc";
    }

    public static String getControlDisc(){
        if (setOfDisks != null) {
            return setOfDisks.getControlDisc();
        }
        return "Error in control disc - no disc";
    }
}
