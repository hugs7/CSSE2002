package buttondemos;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ButtonDemo6 extends javafx.application.Application {

    private int counter = 0;
    private Stage stage;

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    public static void main(String[] args) {
        launch(args);
    }

    // Called to start doing application stuff.
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Button Demo 6");
        Label label = new Label("Hello");
        EventHandler<ActionEvent> eh = new ButtonDoer();
        button = new Button("Useless Button");
        button.setOnAction(eh);
        button2 = new Button("Equally Useless Button");
        button2.setOnAction(eh);
        button3 = new Button("3");
        button3.setOnAction(eh);
        button4 = new Button("4");
        button4.setOnAction(eh);
        button5 = new Button("5");
        button5.setOnAction(eh);

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
            if (e.getSource() == button) {
                System.out.println("1");
            } else if (e.getSource() == button2) {
                System.out.println("2");
            } else if (e.getSource() == button3) {
                System.out.println("3");
            } else if (e.getSource() == button4) {
                System.out.println("4");
            } else if (e.getSource() == button5) {
                System.out.println("5");
            }
        }
    }

}
