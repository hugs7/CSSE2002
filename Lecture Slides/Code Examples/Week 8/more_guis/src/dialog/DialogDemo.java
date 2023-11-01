package dialog;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Optional;


public class DialogDemo extends javafx.application.Application {

    public void doButton() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.initStyle(StageStyle.DECORATED);
        inputDialog.setHeaderText("Header");
        inputDialog.setGraphic(null);

        ImageView iv = new ImageView(new Image("horse_caller.png"));
        iv.setFitHeight(40);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        inputDialog.setGraphic(iv);

        Optional<String> result = inputDialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(result.get());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Called to start doing application stuff.
    public void start(Stage stage) {
        stage.setTitle("Dialog Demo");
        Button button = new Button("Do it");
        button.setOnAction(new EventHandler<ActionEvent>() {
                               public void handle(ActionEvent e) {
                                   doButton();
                               }   // end of handle method
                           }   // end of class
        );

        GridPane grid = new GridPane();
        grid.add(button, 0, 0);

        Scene scene = new Scene(grid);  // Can only hold one "control".
        stage.setScene(scene);
        stage.show();
    }
}
