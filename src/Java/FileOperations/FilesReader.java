package Java.FileOperations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesReader {

    public static boolean checkFile(String path) {
        Path filePath = Paths.get("src\\Resources\\" +path);
        return Files.exists(filePath);
    }

    public static String readFileLane(String path) throws IOException {
        try {
            Path filePath = Paths.get("src\\Resources\\" + path);
            BufferedReader bufferedReader = Files.newBufferedReader(filePath);
            return bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Nie znaleziono pliku o ścieżce: " + path);
        } catch (IOException e){
            e.printStackTrace();
            throw new IOException("Wystapil jakis blad - zwiazany z z operacjami I/O");
        }
    }
}
