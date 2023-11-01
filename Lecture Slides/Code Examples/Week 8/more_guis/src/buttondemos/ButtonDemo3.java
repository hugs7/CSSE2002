package buttondemos;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


class ButtonDoer3 implements EventHandler<ActionEvent> {

    private ButtonDemo3 bd3;

    public ButtonDoer3(ButtonDemo3 b) {
        bd3 = b;
    }

    public void handle(ActionEvent e) {
        bd3.respondToButton();
    }
}


public class ButtonDemo3 extends javafx.application.Application {

    private int counter = 0;
    private Stage stage;

    void respondToButton() {
        counter++;
        stage.setTitle("Button clicked " + counter + " times");
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Called to start doing application stuff.
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Button Demo 3");
        Label label = new Label("Hello");
        Button button = new Button("Useless Button");
        button.setOnAction(new ButtonDoer3(this));
        GridPane grid = new GridPane();
        grid.add(label, 0, 0);  // (col, row)
        grid.add(button, 1, 0);
        Scene scene = new Scene(grid); // Can only hold one "control".
        stage.setScene(scene);
        stage.show();
    }
}
