package main.gitolite.gui.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import main.gitolite.gui.controllers.ReposController;

public class Repos extends AnchorPane {

    public Repos()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("Repos.fxml"));
            loader.setRoot(this);
            ReposController controller = new ReposController();
            loader.setController(controller);
            loader.load();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
