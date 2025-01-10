package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View.fxml"));
		Parent root = fxmlLoader.load();
		Controller controller = fxmlLoader.getController();
		controller.init(primaryStage);
	

		primaryStage.setTitle("XML Editor");
		
		primaryStage.setScene(new Scene(root, 800, 500));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
