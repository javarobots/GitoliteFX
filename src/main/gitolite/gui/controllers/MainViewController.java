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
    }

    private void setupCloseMenuItem()
    {
        closeMenuItem.setOnAction(event -> {
            System.exit(0);
        });
    }
	
}
