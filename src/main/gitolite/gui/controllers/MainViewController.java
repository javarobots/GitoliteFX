package main.gitolite.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;

public class MainViewController {
    
    @FXML private MenuItem closeMenuItem;
    @FXML private MenuItem aboutMenuItem; 
    @FXML private Tab publicKeysTab;
    
    public void initialize()
    {
        setupCloseMenuItem();
        
        //TODO utilize preferences for checking if gitolite directory
        //directory has been defined. If it has add in view for existing tabs
        //And if it hasn't show a dialog asking the user to select
        //the gitolite admin repo root directory
    }

    private void setupCloseMenuItem()
    {
        closeMenuItem.setOnAction(event -> {
            System.exit(0);
        });
    }
	
}
