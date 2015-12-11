package main.gitolite.gui.controllers;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.gitolite.domain.models.ApplicationModel;
import main.gitolite.domain.models.UserKey;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

public class KeyDirectoryController {
    
    @FXML private TableView<UserKey> currentKeyTableView;
    @FXML private TextField newKeyUserNameTextField;
    @FXML private Button deleteButton;
    @FXML private Button addButton;
    @FXML private TextArea keyTextArea;
    
    public void initialize()
    {
        setupDeleteButton();
        
        setupAddButton();
        
        setupKeyTableView();
        
        File keyDir = ApplicationModel.getInstance().getKeyDir();
        if (keyDir.exists())
        {
            File[] keyFiles = keyDir.listFiles();
            //Create UserKey objects and populate table
        }
        
    }

    private void setupKeyTableView()
    {
        TableColumn<UserKey,String> col1 = new TableColumn<>("Username");
        col1.setCellValueFactory(celldata -> celldata.getValue().keyValueProperty());
        col1.prefWidthProperty().bind(currentKeyTableView.widthProperty());
        currentKeyTableView.getColumns().add(col1);
    }

    private void setupAddButton()
    {
        addButton.setText("");
        addButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PLUS));
    }

    private void setupDeleteButton()
    {
        deleteButton.setText("");
        deleteButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.MINUS));
    }
}
