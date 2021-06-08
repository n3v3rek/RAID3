package Java.GUI.FXMLControllers;

import Java.FileOperations.FilesReader;
import Java.GUI.MyFXMLLoader;
import Java.GUI.viewChanger;
import Java.Main.App;
import Java.Model.SetOfDisks;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class exploreDataStageController implements Initializable, viewChanger {

    @FXML
    public BorderPane exploreDataPane;
    public TextArea discOneTextArea;
    public TextArea discTwoTextArea;
    public TextArea controlDiscTextArea;
    public TextArea currentDiscOneTextArea;
    public TextArea currentDiscTwoTextArea;
    public Button deleteDiscOneButton;
    public Button deleteDiscTwoButton;
    public Button editDiscOneButton;
    public Button editDiscTwoButton;
    public Button deleteSomeDataFromDiscOneButton;
    public Button deleteSomeDataFromDiscTwoButton;
    public Button changeSomeDataFromDiscOneButton;
    public Button changeSomeDataFromDiscTwoButton;
    public Button recoverDiscOneButton;
    public Button recoverDiscTwoButton;

    @Override
    public void changePane(String FXMLFileName) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Pane view = loader.getNextStage(FXMLFileName);
        exploreDataPane.setCenter(view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareTextAreaFromDisc(discOneTextArea,App.getFirstDisc());
        prepareTextAreaFromDisc(discTwoTextArea,App.getSecondDisc());
        prepareTextAreaFromDisc(controlDiscTextArea,App.getControlDisc());
        prepareTextAreaFromDisc(currentDiscOneTextArea,App.getFirstDisc());
        currentDiscOneTextArea.setStyle("-fx-border-color: lime; -fx-border-width: 5px");
        prepareTextAreaFromDisc(currentDiscTwoTextArea,App.getSecondDisc());
        currentDiscTwoTextArea.setStyle("-fx-border-color: lime; -fx-border-width: 5px");

        recoverDiscOneButton.setDisable(true);
        recoverDiscTwoButton.setDisable(true);
    }
    private void prepareTextAreaFromDisc(TextArea textArea, ArrayList<String> arrayListFromDisc){

        String convertedTextFromArrayList = SetOfDisks.convertFromArrayListToString(arrayListFromDisc);

        textArea.setWrapText(true);
        textArea.setText(convertedTextFromArrayList);
        textArea.setEditable(false);
    }

    public void goToMenuStage(){
        App.clearDataFromDisks();
        changePane("startStage");
    }

    public void exitFromApp(){
        System.exit(0);
    }

    public void blockDiscOneOptions(boolean state){
        deleteDiscOneButton.setDisable(state);
        editDiscOneButton.setDisable(state);
        changeSomeDataFromDiscOneButton.setDisable(state);
        deleteSomeDataFromDiscOneButton.setDisable(state);
        recoverDiscOneButton.setDisable(state);
    }

    public void blockDiscTwoOptions(boolean state){
        deleteDiscTwoButton.setDisable(state);
        editDiscTwoButton.setDisable(state);
        changeSomeDataFromDiscTwoButton.setDisable(state);
        deleteSomeDataFromDiscTwoButton.setDisable(state);
        recoverDiscTwoButton.setDisable(state);
    }

    public void deleteDiscOne(){
        blockDiscTwoOptions(true);
        currentDiscOneTextArea.setText("");
        currentDiscOneTextArea.setStyle("-fx-border-color: red;  -fx-border-width: 5px");
        App.setFirstDisc(null);
        recoverDiscOneButton.setDisable(false);
    }

    public void deleteDiscTwo(){
        blockDiscOneOptions(true);
        currentDiscTwoTextArea.setText("");
        currentDiscTwoTextArea.setStyle("-fx-border-color: red;  -fx-border-width: 5px");
        App.setSecondDisc(null);
        recoverDiscTwoButton.setDisable(false);
    }

    public void recoverDiscOne(){

        App.recoverDiscOne();
        ArrayList<String> recoveredDiscAsArrayList = App.getFirstDisc();
        String recoveredDisc = SetOfDisks.convertFromArrayListToString(recoveredDiscAsArrayList);

        currentDiscOneTextArea.setText(recoveredDisc);
        currentDiscOneTextArea.setStyle("-fx-border-color: lime; -fx-border-width: 5px");
        currentDiscOneTextArea.setEditable(false);
        blockDiscTwoOptions(false);
        recoverDiscOneButton.setDisable(true);
        recoverDiscTwoButton.setDisable(true);
    }

    public void recoverDiscTwo(){
        App.recoverDiscTwo();
        currentDiscTwoTextArea.setStyle("-fx-border-color: lime; -fx-border-width: 5px");
        ArrayList<String> recoveredDiscAsArrayList = App.getSecondDisc();
        String recoveredDisc = SetOfDisks.convertFromArrayListToString(recoveredDiscAsArrayList);
        currentDiscTwoTextArea.setText(recoveredDisc);
        blockDiscOneOptions(false);
        recoverDiscOneButton.setDisable(true);
        recoverDiscTwoButton.setDisable(true);
    }

    public void editDiscOne(){
        if (!currentDiscOneTextArea.isEditable()){
        currentDiscOneTextArea.setEditable(true);
        currentDiscOneTextArea.setStyle("-fx-border-color: orange; -fx-border-width: 5px");
        blockDiscTwoOptions(true);
        } else{
            if (!checkIfTextAreasAreEmpty(currentDiscOneTextArea) && checkIfLengthOfTextAreasEqual(currentDiscOneTextArea, currentDiscTwoTextArea) &&
                    checkIfTextAreaIsOnlyBinary(currentDiscOneTextArea) && currentDiscOneTextArea.getText().equals(discOneTextArea.getText())) {
                currentDiscOneTextArea.setStyle("-fx-border-color: lime; -fx-border-width: 5px");
                blockDiscTwoOptions(false);
                recoverDiscOneButton.setDisable(true);
                recoverDiscTwoButton.setDisable(true);
            } else {
                currentDiscOneTextArea.setStyle("-fx-border-color: red; -fx-border-width: 5px");
                recoverDiscOneButton.setDisable(false);
            }
            ArrayList<String> convertedDiscToArrayList = SetOfDisks.convertTextFromTextAreaToArrayList(currentDiscOneTextArea,32);
            App.setFirstDisc(convertedDiscToArrayList);
            currentDiscOneTextArea.setEditable(false);
        }
    }

    public void editDiscTwo(){
        if (!currentDiscTwoTextArea.isEditable()){
            currentDiscTwoTextArea.setEditable(true);
            currentDiscTwoTextArea.setStyle("-fx-border-color: orange; -fx-border-width: 5px");
            blockDiscOneOptions(true);
        } else{
            if (!checkIfTextAreasAreEmpty(currentDiscTwoTextArea) && checkIfLengthOfTextAreasEqual(currentDiscOneTextArea, currentDiscTwoTextArea) &&
                    checkIfTextAreaIsOnlyBinary(currentDiscTwoTextArea) && currentDiscTwoTextArea.getText().equals(discTwoTextArea.getText())) {
                currentDiscTwoTextArea.setStyle("-fx-border-color: lime; -fx-border-width: 5px");
                blockDiscOneOptions(false);
                recoverDiscOneButton.setDisable(true);
                recoverDiscTwoButton.setDisable(true);
            } else {
                currentDiscTwoTextArea.setStyle("-fx-border-color: red; -fx-border-width: 5px");
                recoverDiscTwoButton.setDisable(false);
            }
            ArrayList<String> convertedDiscToArrayList = SetOfDisks.convertTextFromTextAreaToArrayList(currentDiscTwoTextArea,32);
            App.setSecondDisc(convertedDiscToArrayList);
            currentDiscTwoTextArea.setEditable(false);
        }
    }

    public ArrayList<String> getDiscAfterDeleteOfSomeBits(TextArea textArea){

        StringBuilder discWithoutBits = new StringBuilder();
        Random randomNumbersGenerator = new Random();

        for (int i=0;i<textArea.getLength();i++){
            if (randomNumbersGenerator.nextInt(3) == 1)
                discWithoutBits.append(textArea.getText().charAt(i));
        }

        textArea.setText(discWithoutBits.toString());
        textArea.setStyle("-fx-border-color: red; -fx-border-width: 5px");

        return  SetOfDisks.convertTextFromTextAreaToArrayList(textArea,32);
    }

    public void deleteSomeBitsFromDiscOne(){
        App.setFirstDisc(getDiscAfterDeleteOfSomeBits(currentDiscOneTextArea));
        blockDiscTwoOptions(true);
        recoverDiscOneButton.setDisable(false);
    }

    public void deleteSomeBitsFromDiscTwo(){
        App.setSecondDisc(getDiscAfterDeleteOfSomeBits(currentDiscTwoTextArea));
        blockDiscOneOptions(true);
        recoverDiscTwoButton.setDisable(false);
    }

    public ArrayList<String> getDiscAfterChangingOfSomeBits(TextArea textArea){

        StringBuilder discWithNonBinaryBits= new StringBuilder();
        Random randomNumbersGenerator = new Random();

        for (int i=0;i<textArea.getLength();i++){
            if (randomNumbersGenerator.nextInt(3) == 1)
                discWithNonBinaryBits.append('9');
            else
                discWithNonBinaryBits.append(textArea.getText().charAt(i));
        }

        textArea.setText(discWithNonBinaryBits.toString());
        textArea.setStyle("-fx-border-color: red; -fx-border-width: 5px");

        return  SetOfDisks.convertTextFromTextAreaToArrayList(textArea,32);
    }

    public void changeSomeBitsFromDiscOne(){
        App.setFirstDisc(getDiscAfterChangingOfSomeBits(currentDiscOneTextArea));
        blockDiscTwoOptions(true);
        recoverDiscOneButton.setDisable(false);
    }

    public void changeSomeBitsFromDiscTwo(){
        App.setSecondDisc(getDiscAfterChangingOfSomeBits(currentDiscTwoTextArea));
        blockDiscOneOptions(true);
        recoverDiscTwoButton.setDisable(false);
    }

    private boolean checkIfLengthOfTextAreasEqual(TextArea textAreaOne,TextArea textAreaTwo){
        return textAreaOne.getLength() == textAreaTwo.getLength();
    }

    private boolean checkIfTextAreaIsOnlyBinary(TextArea textArea){
        for (int i = 0; i<textArea.getLength();i++){
            if (textArea.getText().charAt(i) != '0' && textArea.getText().charAt(i) !='1')
                return false;
        }
        return true;
    }

    private boolean checkIfTextAreasAreEmpty(TextArea textArea){
        return textArea.getLength() == 0;
    }

    public ArrayList<String> resetDiscFromTextAreaToTextArea(TextArea currentTextArea,TextArea previousTextArea){

        String textFromPreviousTextArea = previousTextArea.getText();

        currentTextArea.setText(textFromPreviousTextArea);
        currentTextArea.setStyle("-fx-border-color: lime; -fx-border-width: 5px");

        return  SetOfDisks.convertTextFromTextAreaToArrayList(currentTextArea,32);
    }

    public void resetCurrentDisks(){
        App.setFirstDisc(resetDiscFromTextAreaToTextArea(currentDiscOneTextArea,discOneTextArea));
        blockDiscOneOptions(false);

        App.setSecondDisc(resetDiscFromTextAreaToTextArea(currentDiscTwoTextArea,discTwoTextArea));
        blockDiscTwoOptions(false);

        currentDiscOneTextArea.setEditable(false);
        currentDiscTwoTextArea.setEditable(false);

        recoverDiscOneButton.setDisable(true);
        recoverDiscTwoButton.setDisable(true);
    }

    public void createReport(){

        StringBuilder status = new StringBuilder();

        if(checkIfTextAreasAreEmpty(currentDiscOneTextArea) && checkIfTextAreasAreEmpty(currentDiscTwoTextArea)) {
            status.append("Error - 1 - Empty disc one and two.");
        }else if (checkIfTextAreasAreEmpty(currentDiscOneTextArea)){
            status.append("Error - 2 - Empty disc one.");
        }else if (checkIfTextAreasAreEmpty(currentDiscTwoTextArea)){
            status.append("Error - 3 - Empty disc two.");
        }else if (!checkIfLengthOfTextAreasEqual(currentDiscOneTextArea,currentDiscTwoTextArea)){
            status.append("Error - 4 - Different size of disks.");
        }else if (!checkIfTextAreaIsOnlyBinary(currentDiscOneTextArea) && ! checkIfTextAreaIsOnlyBinary(currentDiscTwoTextArea)) {
            status.append("Error - 5 - Disks contain non-binary values.");
        }else if (!checkIfTextAreaIsOnlyBinary(currentDiscOneTextArea)){
            status.append("Error - 6 - Disc one contains non-binary values.");
        }else if (!checkIfTextAreaIsOnlyBinary(currentDiscTwoTextArea)){
            status.append("Error - 7 - Disc two contains non-binary values.");
        }else {
            status.append("OK - 0");
        }
        App.createReport(status.toString());
    }
}
