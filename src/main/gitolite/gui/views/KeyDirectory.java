package main.gitolite.gui.views;

import java.io.IOException;

import main.gitolite.gui.controllers.KeyDirectoryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class KeyDirectory extends AnchorPane {

    public KeyDirectory()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("KeyDirectory.fxml"));
            loader.setRoot(this);
            loader.setController(new KeyDirectoryController());
            loader.load();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
