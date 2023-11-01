import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class HelloGui extends javafx.application.Application {

public static void main(String[] args) {
    launch(args);
}

// Called to start doing application stuff
public void start(Stage stage) {
    stage.setTitle("Greetings");
    Label label = new Label("Hello ");
    Scene scene = new Scene(label); // can only hold one "control"
    stage.setScene(scene);
    stage.setHeight(60);
    stage.setWidth(60);
    stage.show();
}

}
