package Java.GUI.FXMLControllers;

import Java.GUI.MyFXMLLoader;
import Java.GUI.viewChanger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class startStageController implements Initializable, viewChanger {

    @FXML
    public BorderPane startPane;
    public Button startButton;
    public Button exitButton;
    public Button informationButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void changePane(String FXMLFileName) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Pane view = loader.getNextStage(FXMLFileName);
        startPane.setCenter(view);
    }

    public void Start() {

    }

    public void GoToInformation() {
        changePane("informationStage");
    }

    public void Exit() {
        System.exit(0);
    }
}
