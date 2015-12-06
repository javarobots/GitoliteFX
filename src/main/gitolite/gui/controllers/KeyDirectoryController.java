package main.gitolite.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.gitolite.domain.models.UserKey;

public class KeyDirectoryController {
    
    @FXML private TableView<UserKey> currentKeyTableView;
    @FXML private TextField newKeyUserNameTextField;
    @FXML private Button deleteButton;
    @FXML private Button addButton;
    @FXML private TextArea keyTextArea;
    
    public KeyDirectoryController()
    {
        
    }
}
