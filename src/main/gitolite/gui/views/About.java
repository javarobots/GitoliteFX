package main.gitolite.gui.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.gitolite.gui.controllers.AboutController;

public class About extends AnchorPane {
    
    public About()
    {
        try
        {
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("About.fxml"));
            loader.setRoot(this);
            loader.setController(new AboutController());
            loader.load();
            Scene scene = new Scene(this);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
