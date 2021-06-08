package Java.Model;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class SetOfDisks {

    private ArrayList<String> DiscOne;
    private ArrayList<String> DiscTwo;
    private ArrayList<String> ControlDisc;

    public SetOfDisks() {
        DiscOne = new ArrayList<>();
        DiscTwo = new ArrayList<>();
        ControlDisc = new ArrayList<>();
    }

    public SetOfDisks(ArrayList<String> discOne, ArrayList<String> discTwo, ArrayList<String> controlDisc) {
        this.DiscOne = discOne;
        this.DiscTwo = discTwo;
        this.ControlDisc = controlDisc;
    }

    public ArrayList<String> getDiscOne() {
        return this.DiscOne;
    }

    public void setDiscOne(ArrayList<String> discOne) {
        this.DiscOne = discOne;
    }

    public ArrayList<String> getDiscTwo() {
        return this.DiscTwo;
    }

    public void setDiscTwo(ArrayList<String> discTwo) {
        this.DiscTwo = discTwo;
    }

    public ArrayList<String> getControlDisc() {
        return this.ControlDisc;
    }

    private StringBuilder createLaneOfControlDisc(int index){

        StringBuilder stringBuilder = new StringBuilder();

        if (DiscOne == null || DiscOne.get(index).equals("") ){
            System.out.println("Disc One is empty");
        }
        if (DiscTwo == null || DiscTwo.get(index).equals("") ){
            System.out.println("Disc Two is empty");
        }
        if (!ifEqualLengthOfDisks(index)){
            System.out.println("Different sizes of disks.");
        } else {
            String tempDiscOne = DiscOne.get(index);
            String tempDiscTwo = DiscTwo.get(index);

            for (int i=0;i < tempDiscOne.length();i++) {
                if (tempDiscOne.charAt(i) == '1' && tempDiscTwo.charAt(i) == '1')
                    stringBuilder.append("1");
                else if (tempDiscOne.charAt(i) == '0' && tempDiscTwo.charAt(i) == '0')
                    stringBuilder.append("1");
                else
                    stringBuilder.append("0");
            }
        }
        return stringBuilder;
    }

    public void createControlDisc(){
        if (DiscOne.size() == DiscTwo.size()){
            for (int i=0;i<DiscOne.size();i++){
                ControlDisc.add(createLaneOfControlDisc(i).toString());
            }
        }
    }

    public boolean ifEqualLengthOfDisks(int index){
        for (int i=0; i<DiscOne.size();i++)
            if (DiscOne.get(index).length() != DiscTwo.get(index).length())
                return false;
        return true;
    }

    public void disableDiscOne(){
        DiscOne = null;
    }

    public void disableDiscTwo(){
        DiscTwo = null;
    }

    public void printDiscOne(){
        System.out.println("DiscOne:");
        printDisc(DiscOne);
    }

    public void printDiscTwo(){
        System.out.println("DiscTwo:");
        printDisc(DiscTwo);
    }

    public void printControlDisc(){
        System.out.println("ControlDisc:");
        printDisc(ControlDisc);
    }

    private void printDisc(ArrayList<String> disc){
        for (int i=0;i<disc.size();i++)
            System.out.println(disc.get(i));
    }

    public void recoverDisks(){
        if (ControlDisc == null){
            System.out.println("No control disc to recover from.");
        } else if (DiscTwo == null && DiscOne == null){
            System.out.println("Disc cannot be recovered when both disks are damaged.");
        } else if (DiscOne == null){
            DiscOne = recoverFromControlDiscAnd(DiscTwo);
        } else if (DiscTwo == null){
            DiscTwo = recoverFromControlDiscAnd(DiscOne);
        }
    }

    public ArrayList<String> recoverFromControlDiscAnd(ArrayList<String> disc){

        ArrayList<String> tempArrayList = new ArrayList<>();

        for (int i=0;i<disc.size();i++){
            StringBuilder stringBuilder = new StringBuilder();
            String tempRecoverDisc = disc.get(i);
            String tempControlDisc = ControlDisc.get(i);

            for (int j=0;j<tempControlDisc.length();j++) {
                if (tempRecoverDisc.charAt(j) == '1' && tempControlDisc.charAt(j) == '1')
                    stringBuilder.append("1");
                else if (tempRecoverDisc.charAt(j) == '0' && tempControlDisc.charAt(j) == '1')
                    stringBuilder.append("0");
                else if (tempRecoverDisc.charAt(j) == '1' && tempControlDisc.charAt(j) == '0')
                    stringBuilder.append("0");
                else
                    stringBuilder.append("1");
            }
            tempArrayList.add(stringBuilder.toString());
        }
        return tempArrayList;
    }

    public static ArrayList<String> convertTextFromTextAreaToArrayList(TextArea textArea,int numberOfCharsInRow){

        if(numberOfCharsInRow == 0)
            numberOfCharsInRow = 1;

        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> convertedText = new ArrayList<>();

        for (int i=0;i<textArea.getLength();i++){
            stringBuilder.append(textArea.getText().charAt(i));
            if (i%numberOfCharsInRow == numberOfCharsInRow-1){
                convertedText.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }
        }
        if (!stringBuilder.toString().equals("")){
            convertedText.add(stringBuilder.toString());
        }
        return convertedText;
    }

    public static String convertFromArrayListToString(ArrayList<String> arrayListToBeConverted){

        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0;i<arrayListToBeConverted.size();i++)
            stringBuilder.append(arrayListToBeConverted.get(i));
        return stringBuilder.toString();
    }
}
