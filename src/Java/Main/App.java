package Java.Main;

import Java.FileOperations.FilesReader;
import Java.Model.SetOfDisks;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

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

    public static void setFirstDisc(ArrayList<String> disc){
        if (setOfDisks == null){
            setOfDisks = new SetOfDisks();
        }
            setOfDisks.setDiscOne(disc); }

    public static void setSecondDisc(ArrayList<String> disc){
        if (setOfDisks == null){
            setOfDisks = new SetOfDisks();
        }
        setOfDisks.setDiscTwo(disc);
    }

    public static void createControlDisc(){
        setOfDisks.createControlDisc();
    }

    public static ArrayList<String> getFirstDisc(){
           return setOfDisks.getDiscOne();
    }

    public static ArrayList<String> getSecondDisc(){
            return setOfDisks.getDiscTwo();
    }

    public static ArrayList<String> getControlDisc(){
            return setOfDisks.getControlDisc();
    }

    public static void clearDataFromDisks(){
        setOfDisks = null;
    }

    public static void recoverDiscOne(){
        ArrayList<String> discTwo = setOfDisks.getDiscTwo();
        ArrayList<String> recoveredDisc = setOfDisks.recoverFromControlDiscAnd(discTwo);
        setOfDisks.setDiscOne(recoveredDisc);
    }

    public static void recoverDiscTwo(){
        ArrayList<String> discOne = setOfDisks.getDiscOne();
        ArrayList<String> recoveredDisc = setOfDisks.recoverFromControlDiscAnd(discOne);
        setOfDisks.setDiscTwo(recoveredDisc);
    }

    public static void createReport(String status){
        try {
            FilesReader.createReport("report.txt",setOfDisks,status);
        } catch (Exception exception){
            exception.printStackTrace();
        }

    }
}
