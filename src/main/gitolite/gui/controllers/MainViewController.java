package main.gitolite.gui.controllers;

import java.io.File;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.stage.DirectoryChooser;
import main.gitolite.domain.models.ApplicationModel;



public class MainViewController {
    
    @FXML private MenuItem closeMenuItem;
    @FXML private MenuItem aboutMenuItem;
    @FXML private MenuItem gitoliteDirectorySelectMenuItem;
    @FXML private Tab publicKeysTab;
    @FXML private Tab reposTab;
    
    public void initialize()
    {
        setupCloseMenuItem();
        
        checkForDefinedGitRepoDirectory();
        
        gitoliteDirectorySelectMenuItem.setOnAction(event -> {
            DirectoryChooser chooser = new DirectoryChooser();
            File selectedDirectory = chooser.showDialog(null);
            if (selectedDirectory != null)
            {
                ApplicationModel.getInstance().setRepoDirectory(selectedDirectory);
                checkForDefinedGitRepoDirectory();
            }
        });
    }

    private void checkForDefinedGitRepoDirectory()
    {
        if (!ApplicationModel.getInstance().isRepoDirectoryDefined())
        {
            publicKeysTab.setDisable(true);
            reposTab.setDisable(true);
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.ERROR, "Please select the root directory of the gitolite admin repository.");
                alert.showAndWait();
            });
        }
        else
        {
            publicKeysTab.setDisable(false);
            reposTab.setDisable(false);
        }
    }

    private void setupCloseMenuItem()
    {
        closeMenuItem.setOnAction(event -> {
            System.exit(0);
        });
    }
	
}
