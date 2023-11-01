import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderGui extends javafx.application.Application {

public static void main(String[] args) {
    launch(args);
}

// Called to start doing application stuff
public void start(Stage stage) {
    stage.setTitle("Borders");
    TextArea t1 = new TextArea("top");
    TextArea t2 = new TextArea("centre");
    TextArea t3 = new TextArea("left");
    TextArea t4 = new TextArea("right");
    TextArea t5 = new TextArea("bottom");

    // This block is just to make the window start a sensible size.
    t1.setPrefRowCount(4);
    t1.setPrefColumnCount(12);
    t2.setPrefRowCount(4);
    t2.setPrefColumnCount(4);
    t3.setPrefRowCount(4);
    t3.setPrefColumnCount(4);
    t4.setPrefRowCount(4);
    t4.setPrefColumnCount(4);
    t5.setPrefRowCount(4);
    t5.setPrefColumnCount(12);
    
    BorderPane bp = new BorderPane();
    bp.setTop(t1);
    bp.setCenter(t2);
    bp.setLeft(t3);
    bp.setRight(t4);
    bp.setBottom(t5);

    Scene scene = new Scene(bp);
    stage.setScene(scene);
//   stage.setHeight(60);
//   stage.setWidth(60);
    stage.show();
}

}
