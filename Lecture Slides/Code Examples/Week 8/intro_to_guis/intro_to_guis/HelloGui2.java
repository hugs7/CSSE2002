import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class HelloGui2 extends javafx.application.Application {

public static void main(String[] args) {
    launch(args);
}

// Called to start doing application stuff
public void start(Stage stage) {
    stage.setTitle("Greetings");
    Label label = new Label("Hello ");
    Button button = new Button("Useless Button");
    GridPane grid = new GridPane();
    grid.add(label,0,0);  // (col, row)
    grid.add(button,1,0);
    Scene scene = new Scene(grid); // can only hold one "control"
    stage.setScene(scene);
//     stage.setHeight(60);
//     stage.setWidth(60);
    stage.show();
}

}
