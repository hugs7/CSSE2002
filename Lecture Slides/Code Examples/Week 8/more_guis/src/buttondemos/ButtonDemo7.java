package buttondemos;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ButtonDemo7 extends javafx.application.Application {

    private int counter = 0;
    private Stage stage;

    private Button button;
    private Button button2;

    public void respondToButton() {
        System.out.println("Button1");
    }

    public void respondToButton2() {
        System.out.println("Button2");
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Called to start doing application stuff.
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Button Demo 7");
        Label label = new Label("Hello");
        button = new Button("1");
        button2 = new Button("2");

        button.setOnAction(new EventHandler<ActionEvent>() {
                               public void handle(ActionEvent e) {
                                   respondToButton();
                               }   // end of handle method
                           }   // end of class
        );

        button2.setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent e) {
                                    respondToButton2();
                                }   // end of handle method
                            }   // end of class
        );

        GridPane grid = new GridPane();
        grid.add(label, 0, 0);    // (col, row)
        grid.add(button, 1, 0);
        grid.add(button2, 2, 0);

        Scene scene = new Scene(grid);  // Can only hold one "control".
        stage.setScene(scene);
        stage.show();
    }
}
