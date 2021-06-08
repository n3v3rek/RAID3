package Java.GUI.FXMLControllers;

import Java.FileOperations.FilesReader;
import Java.GUI.MyFXMLLoader;
import Java.GUI.viewChanger;
import Java.Main.App;
import Java.Model.SetOfDisks;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class loadDisksStageController implements Initializable, viewChanger {

    @FXML
    public BorderPane loadDisksPane;
    public TextArea discOneTextArea;
    public TextArea discTwoTextArea;
    public TextArea controlDiscTextArea;
    public Button checkDisksButton;
    public Button createControlDisc;
    public Button nextStageButton;
    public Label discStatus;
    public Label controlDiscStatus;

    @Override
    public void changePane(String FXMLFileName) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Pane view = loader.getNextStage(FXMLFileName);
        loadDisksPane.setCenter(view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareTextAreas();
        preventFromMaliciousAccess();
    }

    private void prepareTextAreas(){
        prepareTextArea(discOneTextArea);
        prepareTextArea(discTwoTextArea);
        prepareTextArea(controlDiscTextArea);
    }
    private void prepareTextArea(TextArea textArea){
        textArea.setWrapText(true);
        textArea.setText("");
        textArea.setEditable(false);
    }

    private void setTextToTextAreaFromDisc(ArrayList<String> disc,TextArea textArea){
        StringBuilder stringBuilder = new StringBuilder();
        for (String lineOfCodeFromDisc : disc)
            stringBuilder.append(lineOfCodeFromDisc);
        textArea.setText(stringBuilder.toString());
    }

    public ArrayList<String> generateRandomDisc(){
        Random random = new Random();
        ArrayList<String> discToBeGenerated = new ArrayList<>();
        StringBuilder stringBuilder;

        for (int i=0;i<8;i++){
            stringBuilder = new StringBuilder();
            for (int j=0;j<32;j++){
                stringBuilder.append(random.nextInt(2));
            }
            discToBeGenerated.add(stringBuilder.toString());
        }
        return discToBeGenerated;
    }

    public void generateRandomlyDiscOne(){
        ArrayList<String> randomGeneratedDisc = generateRandomDisc();
        setTextToTextAreaFromDisc(randomGeneratedDisc,discOneTextArea);
        discOneTextArea.setEditable(false);
        preventFromMaliciousAccess();
    }

    public void generateRandomlyDiscTwo(){
        ArrayList<String> randomGeneratedDisc = generateRandomDisc();
        setTextToTextAreaFromDisc(randomGeneratedDisc,discTwoTextArea);
        discTwoTextArea.setEditable(false);
        preventFromMaliciousAccess();
    }

    public void generateDiscOneFromFile() throws IOException {
        try {
            ArrayList<String> discGeneratedFromFile = FilesReader.readFile("discOne.txt");
            setTextToTextAreaFromDisc(discGeneratedFromFile,discOneTextArea);

        }catch (IOException ioException){
            discOneTextArea.setText("IO mistake on loading disc one.");
        }finally {
            discOneTextArea.setEditable(false);
            preventFromMaliciousAccess();
        }
    }

    public void generateDiscTwoFromFile() throws IOException {
        try {
            ArrayList<String> discGeneratedFromFile = FilesReader.readFile("discTwo.txt");
            setTextToTextAreaFromDisc(discGeneratedFromFile,discTwoTextArea);
        }catch (IOException ioException){
            discTwoTextArea.setText("IO mistake on loading disc two.");
        }finally {
            discTwoTextArea.setEditable(false);
            preventFromMaliciousAccess();
        }
    }

    public void enableWritingInAreaOne(){
        discOneTextArea.setEditable(true);
        preventFromMaliciousAccess();
    }

    public void enableWritingInAreaTwo(){
        discTwoTextArea.setEditable(true);
        preventFromMaliciousAccess();
    }

    public void goToMenu(){
        App.clearDataFromDisks();
        changePane("startStage");
    }

    public void goToNextStage(){
        changePane("exploreDataStage");
    }

    public void preventFromMaliciousAccess(){
        createControlDisc.setDisable(true);
        nextStageButton.setDisable(true);
        discStatus.setText("");
        controlDiscStatus.setText("");
    }

    public void checkIfDisksAreCorrect() {
        if (!checkIfTextAreasAreEmpty()) {
            if (checkIfLengthOfTextAreasEqual()) {
                if (checkIfTextAreasAreOnlyBinary()) {
                    createControlDisc.setDisable(false);
                    discStatus.setText("Pomyślnie dodano dyski!");
                    discStatus.setStyle("-fx-text-fill: Lime");
                    App.setFirstDisc(SetOfDisks.convertTextFromTextAreaToArrayList(discOneTextArea,32));
                    App.setSecondDisc(SetOfDisks.convertTextFromTextAreaToArrayList(discTwoTextArea,32));
                }else{
                    discStatus.setText("Błąd - wartości niebinarne!");
                    discStatus.setStyle("-fx-text-fill: red");
                }
            } else {
                discStatus.setText("Błąd - różne wielkości dysków!");
                discStatus.setStyle("-fx-text-fill: red");
            }
        } else {
            discStatus.setText("Błąd - dyski są puste!");
            discStatus.setStyle("-fx-text-fill: red");
        }
    }

    public void createControlDisc(){
        App.createControlDisc();
        String convertedTextFromControlDisc = SetOfDisks.convertFromArrayListToString(App.getControlDisc());
        controlDiscTextArea.setText(convertedTextFromControlDisc);
        controlDiscStatus.setText("Pomyślnie wygenerowano!");
        controlDiscStatus.setStyle("-fx-text-fill: Lime");
        nextStageButton.setDisable(false);
    }

    private boolean checkIfLengthOfTextAreasEqual(){
        return discOneTextArea.getLength() == discTwoTextArea.getLength();
    }

    private boolean checkIfTextAreasAreOnlyBinary(){
        for (int i = 0; i<discOneTextArea.getLength();i++){
            if (discOneTextArea.getText().charAt(i) != '0' && discOneTextArea.getText().charAt(i) !='1')
                return false;
            if (discTwoTextArea.getText().charAt(i) != '0' && discTwoTextArea.getText().charAt(i) !='1')
                return false;
        }
        return true;
    }

    private boolean checkIfTextAreasAreEmpty(){
        return discOneTextArea.getLength() == 0 || discTwoTextArea.getLength() == 0;
    }
}
