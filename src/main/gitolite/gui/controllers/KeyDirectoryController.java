package main.gitolite.gui.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Optional;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import main.gitolite.domain.models.ApplicationModel;
import main.gitolite.domain.models.UserKey;

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
       
        addKeyListenerToTextField();
    }

    private void addKeyListenerToTextField()
    {
        newKeyUserNameTextField.setOnKeyReleased(event -> {
            if (!newKeyUserNameTextField.getText().isEmpty())
            {
                addButton.setDisable(false);
            }
            else
            {
                addButton.setDisable(true);
            }
        });
    }

    private void setupListenerForKeyTableView()
    {
        currentKeyTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            Optional<UserKey> userKey = Optional.ofNullable(newVal);
            if (userKey.isPresent())
            {
                newKeyUserNameTextField.setText(userKey.get().userNameProperty().getValueSafe());
                keyTextArea.setText(userKey.get().keyValueProperty().getValueSafe());
                deleteButton.setDisable(false);
            }
            else
            {
                newKeyUserNameTextField.setText("");
                keyTextArea.setText("");
                deleteButton.setDisable(true);
            }
        });
    }

    private void readTheKeyDirectory()
    {
        userKeys.clear();
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
        addButton.setDisable(true);
        addButton.setTooltip(new Tooltip("Add new key"));
        addButton.setOnAction(event -> {
            if (!keyNameExists() && !keyTextArea.getText().isEmpty())
            {
                try
                {
                    StringBuilder nameBuilder = new StringBuilder(newKeyUserNameTextField.getText());
                    nameBuilder.append(".pub");
                    File keyFile = new File(ApplicationModel.getInstance().getKeyDir(),nameBuilder.toString());
                    FileWriter writer = new FileWriter(keyFile);
                    writer.write(keyTextArea.getText());
                    writer.close();
                    Alert successAlert = new Alert(AlertType.INFORMATION, "Key file successfully written.", ButtonType.OK);
                    successAlert.showAndWait();
                    readTheKeyDirectory();
                    UserKey[] keyToSelect = new UserKey[1];
                    userKeys.forEach(key -> {
                        if (key.userNameProperty().get().equalsIgnoreCase(newKeyUserNameTextField.getText()))
                        {
                            keyToSelect[0] = key;
                        }
                    });
                    if (keyToSelect[0] != null)
                    {
                        currentKeyTableView.getSelectionModel().select(keyToSelect[0]);
                    }
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else
            {
                Alert errorAlert = new Alert(AlertType.ERROR, "Key file name already exists or the key text area is empty.", ButtonType.OK);
                errorAlert.showAndWait();
            }
        });
    }
    
    private boolean keyNameExists()
    {
        Boolean[] result = new Boolean[1];
        result[0] = false;
        userKeys.forEach(key -> {
            if (key.userNameProperty().get().equalsIgnoreCase(newKeyUserNameTextField.getText()))
            {
                result[0] = true;
            }
        });
        return result[0];
    }

    private void setupDeleteButton()
    {
        deleteButton.setText("");
        deleteButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.MINUS));
        deleteButton.setDisable(true);
        deleteButton.setTooltip(new Tooltip("Delete selected key"));
        deleteButton.setOnAction(event -> {
            deleteSelectedKey();
        });
    }

    private void deleteSelectedKey()
    {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to remove the selected key?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
           if (response == ButtonType.YES)
           {
               StringBuilder nameBuilder = new StringBuilder(newKeyUserNameTextField.getText());
               nameBuilder.append(".pub");
               File deleteFile = new File(ApplicationModel.getInstance().getKeyDir(), nameBuilder.toString());
               if (deleteFile.delete())
               {
                   Alert successAlert = new Alert(AlertType.INFORMATION, "Key file successfully deleted.", ButtonType.OK);
                   successAlert.showAndWait();
                   readTheKeyDirectory();
               }
           }
        });
    }
}
