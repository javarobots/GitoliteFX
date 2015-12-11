package main.gitolite.gui.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    private ObservableList<UserKey> userKeys;
    
    public void initialize()
    {
        setupDeleteButton();
        
        setupAddButton();
        
        setupKeyTableView();
        
        readTheKeyDirectory();
        
        setupListenerForKeyTableView();
    }

    private void setupListenerForKeyTableView()
    {
        currentKeyTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            Optional<UserKey> userKey = Optional.ofNullable(newVal);
            if (userKey.isPresent())
            {
                newKeyUserNameTextField.setText(userKey.get().userNameProperty().getValueSafe());
                keyTextArea.setText(userKey.get().keyValueProperty().getValueSafe());
            }
            else
            {
                newKeyUserNameTextField.setText("");
                keyTextArea.setText("");
            }
        });
    }

    private void readTheKeyDirectory()
    {
        File keyDir = ApplicationModel.getInstance().getKeyDir();
        if (keyDir.exists())
        {
            File[] keyFiles = keyDir.listFiles();
            Arrays.asList(keyFiles).forEach(file -> {
                try
                {
                    String[] splitName = file.getName().split("\\.");
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    StringBuilder keyBuilder = new StringBuilder();
                    boolean firstLine = true;
                    Optional<String> inLine = Optional.ofNullable(reader.readLine());
                    while (inLine.isPresent())
                    {
                        if (firstLine)
                        {
                            keyBuilder.append(inLine.get());
                            firstLine = false;
                        }
                        else
                        {
                            keyBuilder.append("\n").append(inLine.get());
                        }
                        inLine = Optional.ofNullable(reader.readLine());
                    }
                    reader.close();
                    userKeys.add(new UserKey(splitName[0], keyBuilder.toString()));
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        }
    }

    private void setupKeyTableView()
    {
        userKeys = FXCollections.observableArrayList();
        currentKeyTableView.setItems(userKeys);
        TableColumn<UserKey,String> col1 = new TableColumn<>("Username");
        col1.setCellValueFactory(celldata -> celldata.getValue().userNameProperty());
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
