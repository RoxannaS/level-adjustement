package main;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LevAdjustGUI extends Application {
		
	public static void main(String[] args) {
		launch(args);

	}
	
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("LevAdjust.fxml"));
		
		Scene levAdjustScene = new Scene(root);
		
		primaryStage.setTitle("LevelAdjusment");
		primaryStage.setScene(levAdjustScene);
		primaryStage.show();
		
	}
	
}
