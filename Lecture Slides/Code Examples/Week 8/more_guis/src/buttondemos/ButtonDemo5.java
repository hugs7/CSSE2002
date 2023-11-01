package buttondemos;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ButtonDemo5 extends javafx.application.Application {

    private int counter = 0;
    private Stage stage;

    public void respondToButton() {
        counter++;
        stage.setTitle("Button clicked " + counter + " times");
    }

    public void respondToButton4() {
        System.out.println("Button4");
    }

    public void respondToButton5() {
        System.out.println("Button5");
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Called to start doing application stuff.
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Button Demo 5");
        Label label = new Label("Hello");
        EventHandler<ActionEvent> eh = new ButtonDoer();
        Button button = new Button("Useless Button");
        button.setOnAction(eh);
        Button button2 = new Button("Equally Useless Button");
        button2.setOnAction(eh);
        Button button3 = new Button("3");
        button3.setOnAction(new Button3Doer());
        Button button4 = new Button("4");
        button4.setOnAction(new Button4Doer());
        Button button5 = new Button("5");
        button5.setOnAction(new Button5Doer());

        GridPane grid=new GridPane();
        grid.add(label, 0, 0);   // (col, row)
        grid.add(button, 1, 0);
        grid.add(button2, 2, 0);
        grid.add(button3, 0, 1);
        grid.add(button4, 1, 1);
        grid.add(button5, 2, 1);

        Scene scene = new Scene(grid);  // Can only hold one "control".
        stage.setScene(scene);
        stage.show();
    }


    // Making an inner class is more than just scoping.
    private class ButtonDoer implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            respondToButton();
            System.out.println(e.getSource());  // Could use the source to work out
                                                // what we should do.
        }
    }

    private class Button3Doer implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            counter = -1;
            respondToButton();
        }
    }

    private class Button4Doer implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            respondToButton4();
        }
    }

    private class Button5Doer implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            respondToButton5();
        }
    }

}
