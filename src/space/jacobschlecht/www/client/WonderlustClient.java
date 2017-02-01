package space.jacobschlecht.www.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class WonderlustClient extends Application {

	public NetworkThread client;
	public TextArea console;
	public TextField input;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		console = new TextArea();
		console.setEditable(false);
		
		input = new TextField();
		input.setEditable(true);
		
		input.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                client.sendMessageToServer(input.getText());
                input.setText("");
            }
        });
		
		StackPane root = new StackPane();
		root.getChildren().add(console);
		root.getChildren().add(input);
		
		Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Wonderlust");
        primaryStage.setScene(scene);
        primaryStage.show();
		
		client = new NetworkThread(this);
		client.start();
	}

}
