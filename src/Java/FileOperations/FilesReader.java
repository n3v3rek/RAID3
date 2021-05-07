package Java.FileOperations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesReader {

    public static boolean checkFile(String path) {
        return Files.exists(Paths.get(path));
    }

    public static String readFile(String path) throws IOException {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));
            return bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Nie znaleziono pliku o ścieżce: " + path);
        } catch (IOException e){
            e.printStackTrace();
            throw new IOException("Wystapil jakis blad - zwiazany z z operacjami I/O");
        }
    }

    //TODO
    //Metoda zapisująca stringa w pliku -> zapis dysku parzystości
}
