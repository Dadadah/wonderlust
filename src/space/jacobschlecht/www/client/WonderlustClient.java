package space.jacobschlecht.www.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
		console.setWrapText(true);
		
		input = new TextField();
		input.setEditable(true);
		
		input.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                client.sendMessageToServer(input.getText());
                input.setText("");
            }
        });
		
		BorderPane root = new BorderPane();
		root.setCenter(console);
		root.setBottom(input);
		
		Scene scene = new Scene(root, 500, 300);

        primaryStage.setTitle("Wonderlust");
        primaryStage.setScene(scene);
        primaryStage.show();
		
		client = new NetworkThread(this);
		client.start();
	}

}
