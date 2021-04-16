package Java.GUI.FXMLControllers;

import Java.FileOperations.FilesReader;
import Java.GUI.MyFXMLLoader;
import Java.GUI.viewChanger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class informationStageController implements Initializable, viewChanger {

    @FXML
    public BorderPane informationPane;
    public ImageView imageViewController;
    public ImageView descriptionController;

    public void GoToStart() {
        changePane("startStage");
    }

    public void Exit() {
        System.exit(0);
    }


    @Override
    public void changePane(String FXMLFileName) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Pane view = loader.getNextStage(FXMLFileName);
        informationPane.setCenter(view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File fileToBeLoaded = new File("src/Resources/raid3.JPG");
        Image imageToBeLoaded = new Image(fileToBeLoaded.toURI().toString());
        imageViewController.setImage(imageToBeLoaded);

        File fileToBeLoaded2 = new File("src/Resources/opis.JPG");
        Image imageToBeLoaded2 = new Image(fileToBeLoaded2.toURI().toString());
        descriptionController.setImage(imageToBeLoaded2);

    }
}
