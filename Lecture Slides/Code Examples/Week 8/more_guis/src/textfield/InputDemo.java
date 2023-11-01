package textfield;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class InputDemo extends javafx.application.Application {

    private TextField tf;
    private TextField tf2;

    public void respondToButton() {
        String mirroredString = "";
        String src = tf.getText();
        for (int i = src.length()-1; i >= 0; --i) {
            mirroredString += src.charAt(i);
        }
        tf2.setText(mirroredString);
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Called to start doing application stuff.
    public void start(Stage stage) {
        stage.setTitle("Input Demo");
        tf = new TextField();
        tf2 = new TextField();
        Button button = new Button("Do it");

        button.setOnAction(new EventHandler<ActionEvent>() {
                               public void handle(ActionEvent e) {
                                   respondToButton();
                               }   // end of handle method
                           }   // end of class
        );

        GridPane grid = new GridPane();
        grid.add(tf, 0, 0);
        grid.add(button, 0, 1);
        grid.add(tf2, 0, 2);

        Scene scene = new Scene(grid); // Can only hold one "control".
        stage.setScene(scene);
        stage.show();
    }
}
