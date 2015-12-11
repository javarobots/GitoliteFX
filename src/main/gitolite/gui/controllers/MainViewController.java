package main.gitolite.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
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
    }

    private void checkForDefinedGitRepoDirectory()
    {
        if (!ApplicationModel.getInstance().isRepoDirectoryDefined())
        {
            publicKeysTab.setDisable(true);
            reposTab.setDisable(true);
            Platform.runLater(() -> {
                Alert alert = new Alert(AlertType.ERROR, "Please select the root directory of the git-o-lite admin repository.");
                alert.showAndWait();
            });
        }
    }

    private void setupCloseMenuItem()
    {
        closeMenuItem.setOnAction(event -> {
            System.exit(0);
        });
    }
	
}
