
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MainScreen extends Application {
	
	Button startButton;
	
	public static void main(String[] args) {
		//System.out.println("HELLO");
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("100% Complete Dorothy's App");
		
		startButton = new Button();
		startButton.setText("the BUTTON");
		
		StackPane layout = new StackPane();
		layout.getChildren().add(startButton);
		
		//Scene scene = new Scene(layout, );
	}
}
