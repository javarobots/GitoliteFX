package main.gitolite.gui.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.gitolite.domain.models.ApplicationModel;
import main.gitolite.gui.controllers.AboutController;

/**
 * The About view displays build information about the application.
 * The buildInfo.txt file is parsed for build number and build
 * date. This is then shown in the dialog.
 * 
 * @author javarobots
 *
 */
public class About extends AnchorPane {
    
    public About()
    {
        try
        {
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(ApplicationModel.getInstance().getIcon());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("About.fxml"));
            loader.setRoot(this);
            loader.setController(new AboutController());
            loader.load();
            Scene scene = new Scene(this);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
