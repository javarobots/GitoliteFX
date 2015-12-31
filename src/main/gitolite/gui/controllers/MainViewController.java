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
import main.gitolite.gui.views.About;
import main.gitolite.gui.views.KeyDirectory;



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
        
        setupDirectorySelectMenuItem();
        
        aboutMenuItem.setOnAction(event -> {
            new About();
        });
    }

    private void setupDirectorySelectMenuItem()
    {
        gitoliteDirectorySelectMenuItem.setOnAction(event -> {
            ApplicationModel model = ApplicationModel.getInstance();
            DirectoryChooser chooser = new DirectoryChooser();
            if (model.isRepoDirectoryDefined())
            {
                chooser.setInitialDirectory(model.getRepoDirectory());
            }
            File selectedDirectory = chooser.showDialog(null);
            if (selectedDirectory != null)
            {
                model.setRepoDirectory(selectedDirectory);
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
            addTabContent();
        }
    }
    
    private void addTabContent()
    {
        publicKeysTab.setContent(new KeyDirectory());
    }

    private void setupCloseMenuItem()
    {
        closeMenuItem.setOnAction(event -> {
            System.exit(0);
        });
    }
	
}
