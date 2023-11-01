package dialog;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File; //New imports
import java.io.IOException;
import javafx.stage.FileChooser;
import java.util.Scanner;


/*
 * Online resources used in creating this file:
 * https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
 */


public class FileChooserDemo extends javafx.application.Application {

    private int counter = 0;
    private Stage stage;
    private FileChooser fileChooser = new FileChooser();

    private void respondToButton() {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
    }

    private void openFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                stage.setTitle(scanner.nextLine());
            }
        } catch (IOException ex) {
            System.err.println("There's something wrong with accessing your file.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Called to start doing application stuff.
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("File Chooser Demo");
        Label label = new Label("Hello");
        Button button = new Button("Useless Button");
        button.setOnAction(new EventHandler<ActionEvent>() {
                               public void handle(ActionEvent e) {
                                   respondToButton();
                               }   // end of handle method
                           }   // end of class
        );

        GridPane grid = new GridPane();
        grid.add(label, 0, 0);   // (col, row)
        grid.add(button, 1, 0);
        Scene scene = new Scene(grid);  // Can only hold one "control".
        stage.setScene(scene);
        stage.show();
    }
}
