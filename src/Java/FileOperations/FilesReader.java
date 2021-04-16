package Java.FileOperations;

import java.nio.file.Files;
import java.nio.file.Paths;


public class FilesReader {

    public static boolean checkFile (String path) {
            if (Files.exists(Paths.get(path))){
                return true;
            }
            System.out.println("Nie znaleziono pliku o ścieżce: " + path);
            return false;
    }
}
