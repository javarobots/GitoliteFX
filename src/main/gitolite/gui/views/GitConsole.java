package main.gitolite.gui.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import main.gitolite.gui.controllers.GitConsoleController;

public class GitConsole extends AnchorPane {
    
    public GitConsole()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new GitConsoleController());
            loader.setLocation(getClass().getResource("GitConsole.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
