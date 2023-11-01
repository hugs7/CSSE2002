import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowGui extends javafx.application.Application {


public static void main(String[] args) {
    launch(args);
}

// Called to start doing application stuff
public void start(Stage stage) {
    stage.setTitle("Flow");
    FlowPane fp = new FlowPane();

    final int BUTTON_COUNT = 15;
    Button[] button = new Button[BUTTON_COUNT];
    for (int i=0; i < BUTTON_COUNT; ++i) {
        button[i] = new Button("" + i);
        fp.getChildren().add(button[i]);
    }
	
    Scene scene = new Scene(fp);
    stage.setScene(scene);
    stage.show();
}

}
