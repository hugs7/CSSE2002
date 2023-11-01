package buttondemos;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


class ButtonDoer2 implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e) {
            System.out.println(e.getSource());
    }
}


public class ButtonDemo2 extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Called to start doing application stuff.
    public void start(Stage stage) {
        stage.setTitle("Button Demo 2");
        Label label = new Label("Hello");
        Button button = new Button("Useless Button");
        button.setOnAction(new ButtonDoer2());
        GridPane grid = new GridPane();
        grid.add(label, 0, 0);  // (col, row)
        grid.add(button, 1, 0);
        Scene scene = new Scene(grid); // Can only hold one "control".
        stage.setScene(scene);
        stage.show();
    }
}
