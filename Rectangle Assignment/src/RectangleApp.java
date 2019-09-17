import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RectangleApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/rectangleApp.fxml"));
			Scene scene = new Scene (root, 750, 700);
			primaryStage.setTitle("Justin's Rectangle Application");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (IOException e) {
			
		}
	}

}
