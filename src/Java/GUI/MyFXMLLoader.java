package Java.GUI;

import Java.Main.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class MyFXMLLoader {

    public Pane getNextStage(String nameOfFile){
        Pane view = new Pane();

        try {
            URL URLofFile = App.class.getResource("/" +nameOfFile + ".fxml");;
            if (URLofFile == null){
                throw new java.io.FileNotFoundException("File can't be found");
            }

            view = new FXMLLoader().load(URLofFile);
        } catch (Exception e){
            System.out.println("There is no stage named" + nameOfFile);
        }

        return view;
    }
}
