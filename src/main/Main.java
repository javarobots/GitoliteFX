package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.gitolite.domain.models.ApplicationModel;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    FXMLLoader loader = new FXMLLoader();
		    loader.setLocation(getClass().getResource("gitolite/gui/views/Main.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Gitolite Repository Manager");
			primaryStage.getIcons().add(ApplicationModel.getInstance().getIcon());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
