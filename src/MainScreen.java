
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MainScreen extends Application implements EventHandler<ActionEvent> {
	
	Button startButton;
	
	public static void main(String[] args) {
		//System.out.println("HELLO");
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("100% Complete Dorothy's App");
		
		startButton = new Button();
		startButton.setText("THE button");
		startButton.setOnAction(this);
		
		StackPane layout = new StackPane();
		layout.getChildren().add(startButton);
		
		Scene scene = new Scene(layout, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void handle (ActionEvent event) {
		System.out.println("YOU PRESSED A BUTTON");
	}
}
