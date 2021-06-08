package Java.FileOperations;

import Java.Model.SetOfDisks;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilesReader {

    public static ArrayList<String> readFile(String path)
        throws IOException{
        try {
            Path filePath = Paths.get("src\\Resources\\" + path);
            BufferedReader bufferedReader = Files.newBufferedReader(filePath);
            ArrayList<String> fileLine = bufferedReader.lines().collect(Collectors.toCollection(ArrayList::new));
            bufferedReader.close();
            return fileLine;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Nie znaleziono pliku o ścieżce: " + path);
        } catch (IOException e){
            e.printStackTrace();
            throw new IOException("Wystapil jakis blad - zwiazany z z operacjami I/O");
        }

    }

    public static void createReport(String path, SetOfDisks setOfDisks, String status)
            throws IOException{
        try {
            FileWriter fileWriter = new FileWriter("src\\Resources\\" + path);

            fileWriter.write("Disc One:\n");
            if (setOfDisks.getDiscOne() != null){
                for (String lane : setOfDisks.getDiscOne()){
                    fileWriter.write(lane +"\n");
                }
            }else {
                fileWriter.write("Disc one is not initialized.\n");
            }

            fileWriter.write("Disc Two:\n");
            if (setOfDisks.getDiscTwo() != null) {
                for (String lane : setOfDisks.getDiscTwo()) {
                    fileWriter.write(lane + "\n");
                }
            }else {
                fileWriter.write("Disc two is not initialized.\n");
            }

            fileWriter.write("Control Disc:\n");
            for (String lane : setOfDisks.getControlDisc()){
                fileWriter.write(lane+"\n");
            }

            fileWriter.write("Status - " + status);
            fileWriter.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Nie znaleziono pliku o ścieżce: " + path);
        } catch (IOException e){
            e.printStackTrace();
            throw new IOException("Wystapil jakis blad - zwiazany z z operacjami I/O");
        }
    }
}
