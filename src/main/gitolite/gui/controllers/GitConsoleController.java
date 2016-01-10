package main.gitolite.gui.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.gitolite.utility.ButtonUtility;
import main.gitolite.utility.LaunchGitBash;

public class GitConsoleController {
    
    @FXML private Button terminalButton;
    
    public void initialize()
    {
        ButtonUtility.setIconOnButton(terminalButton, FontAwesomeIcon.TERMINAL);
        terminalButton.setOnAction(event -> {
            LaunchGitBash launchGit = new LaunchGitBash();
            launchGit.launchGitBash();
        });
    }

}
