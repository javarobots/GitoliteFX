package main.gitolite.gui.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.gitolite.domain.models.ApplicationModel;
import main.gitolite.domain.models.ConfigModel;
import main.gitolite.domain.models.ConfigRepo;
import main.gitolite.domain.models.ConfigRepoRule;
import main.gitolite.domain.parsers.GitoliteConfParser;
import main.gitolite.utility.ButtonUtility;

public class ReposController {
    
    @FXML private Button repoDeleteButton;
    @FXML private Button repoAddButton;
    @FXML private Button userEditButton;
    @FXML private Button userDeleteButton;
    @FXML private Button userAddButton;
    @FXML private Button groupEditButton;
    @FXML private Button groupDeleteButton;
    @FXML private Button groupAddButton;
    
    @FXML private TableView<ConfigRepo> repoTableView;
    @FXML private TableView<ConfigRepoRule> userTableView;
    @FXML private TableView<ConfigRepoRule> groupTableView;
    
    private ObservableList<ConfigRepo> repos;
    private ObservableList<ConfigRepoRule> userRules;
    private ObservableList<ConfigRepoRule> groupRules;
    private ConfigRepo selectedRepo;
    
    public void initialize()
    {
        
        
        setupButtons();
        
        setupRepoTable();
        
        setupUserTable();
        
        setupGroupTable();
        
        
        
        String confFile = readConfFile();
        if (!confFile.isEmpty())
        {
            GitoliteConfParser parser = new GitoliteConfParser();
            ConfigModel model = parser.parse(confFile);
            Iterator<ConfigRepo> repos =  model.getRepos().iterator();
            while (repos.hasNext())
            {
                this.repos.add(repos.next());
            }
        }
        
    }

    private void setupGroupTable()
    {
        groupTableView.getColumns().clear();
        TableColumn<ConfigRepoRule, String> groupUserCol = new TableColumn<>("User");
        groupUserCol.prefWidthProperty().bind(groupTableView.widthProperty().multiply(.33));
        groupTableView.getColumns().add(groupUserCol);
        TableColumn<ConfigRepoRule, String> membersCol = new TableColumn<>("Members");
        membersCol.prefWidthProperty().bind(groupTableView.widthProperty().multiply(.33));
        groupTableView.getColumns().add(membersCol);
        TableColumn<ConfigRepoRule, String> groupPermissionCol = new TableColumn<>("Permission");
        groupPermissionCol.prefWidthProperty().bind(groupTableView.widthProperty().multiply(.34));
        groupTableView.getColumns().add(groupPermissionCol);
    }

    private void setupUserTable()
    {
        userTableView.getColumns().clear();
        TableColumn<ConfigRepoRule, String> userCol = new TableColumn<>("User");
        userCol.prefWidthProperty().bind(userTableView.widthProperty().multiply(.33));
        userTableView.getColumns().add(userCol);
        TableColumn<ConfigRepoRule, String> branchCol = new TableColumn<>("Branch");
        branchCol.prefWidthProperty().bind(userTableView.widthProperty().multiply(.33));
        userTableView.getColumns().add(branchCol);
        TableColumn<ConfigRepoRule, String> permissionCol = new TableColumn<>("Permission");
        permissionCol.prefWidthProperty().bind(userTableView.widthProperty().multiply(.34));
        userTableView.getColumns().add(permissionCol);
    }

    private void setupRepoTable()
    {
        repoTableView.getColumns().clear();
        TableColumn<ConfigRepo, String> repoCol = new TableColumn<>("Repository");
        repoCol.prefWidthProperty().bind(repoTableView.widthProperty());
        repoCol.setCellValueFactory(cellData -> cellData.getValue().getRepoName());
        repoTableView.getColumns().add(repoCol);
        
        repos = FXCollections.observableArrayList();
        repoTableView.setItems(repos);
        
        repoTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)-> {
            selectedRepo = newVal;
            if (newVal != null)
            {
                repoDeleteButton.setDisable(false);
                //add rules through method
            }
            else
            {
                repoDeleteButton.setDisable(true);
            }
        });
    }

    private String readConfFile()
    {
        StringBuilder confFileContents = new StringBuilder();
        try
        {
            Optional<String> inLine;
            BufferedReader confReader = new BufferedReader(new FileReader(ApplicationModel.getInstance().getConfFile()));
            inLine = Optional.ofNullable(confReader.readLine());
            while (inLine.isPresent())
            {
                confFileContents.append(inLine.get()).append("\n");
                inLine = Optional.ofNullable(confReader.readLine());
            }
            confFileContents.deleteCharAt(confFileContents.length() - 1);
            confReader.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
        
        return confFileContents.toString();
    }

    private void setupButtons()
    {
        ButtonUtility.setIconOnButtonAndTooltipWithInitialState(repoDeleteButton,
                FontAwesomeIcon.MINUS,
                "Delete selected repository",
                true);
        ButtonUtility.setIconOnButtonAndTooltipWithInitialState(repoAddButton,
                FontAwesomeIcon.PLUS,
                "Add new repository",
                false);
        ButtonUtility.setIconOnButtonAndTooltipWithInitialState(userEditButton,
                FontAwesomeIcon.PENCIL,
                "Edit selected user permissions",
                true);
        ButtonUtility.setIconOnButtonAndTooltipWithInitialState(userDeleteButton,
                FontAwesomeIcon.MINUS,
                "Remove selected user from repository",
                true);
        ButtonUtility.setIconOnButtonAndTooltipWithInitialState(userAddButton,
                FontAwesomeIcon.PLUS,
                "Add user to repository",
                false);
        ButtonUtility.setIconOnButtonAndTooltipWithInitialState(groupEditButton,
                FontAwesomeIcon.PENCIL,
                "Edit selected group",
                true);
        ButtonUtility.setIconOnButtonAndTooltipWithInitialState(groupDeleteButton,
                FontAwesomeIcon.MINUS,
                "Delete selected group",
                true);
        ButtonUtility.setIconOnButtonAndTooltipWithInitialState(groupAddButton,
                FontAwesomeIcon.PLUS,
                "Add goup to repository",
                false);
    }

}
