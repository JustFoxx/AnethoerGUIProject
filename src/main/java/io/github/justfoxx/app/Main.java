package io.github.justfoxx.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;

public class Main extends Application {
    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        logger.error("Hello World! This is a test.");
        launch(args);
    }

    int i = 0;
    int add = 1;

    @Override
    public void start(Stage stage) throws Exception {
        Label count = new Label(" "+i);
        Label text2 = new Label("Level " + (add-1));
        Label text3 = new Label("Cost " + Math.pow(2,add));
        Label errorText = new Label("You can't afford this!");
        errorText.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
        count.setStyle("-fx-font-size: 50px;");
        text2.setStyle("-fx-font-size: 20px;");
        text3.setStyle("-fx-font-size: 20px;");
        errorText.setVisible(false);
        //buttons
        Button cookie = new Button("\uD83C\uDF6A");
        cookie.setStyle("-fx-font-size: 50px; -fx-text-fill: chocolate;");
        cookie.setOnMouseClicked(e -> {
            i += add;
            count.setText(" "+i);
            errorText.setVisible(false);
        });
        Button button2 = new Button("Upgrade!");
        button2.setStyle("-fx-font-size: 20px;");
        button2.setOnMouseClicked(e -> {
            if (i >= Math.pow(2,add)) {
                i -= Math.pow(2,add);
                add++;
                text2.setText("Level " + (add-1));
                text3.setText("Cost " + Math.pow(2,add));
                count.setText(" "+i);
            } else {
                errorText.setVisible(true);
            }
        });
        //grid
        GridPane gridPane = new GridPane();
        gridPane.add(cookie, 0, 0, 1, 1);
        gridPane.add(count, 1, 0, 1, 1);
        gridPane.add(text2, 0, 1, 1, 1);
        gridPane.add(text3, 0, 2, 1, 1);
        gridPane.add(button2, 0, 3, 1, 1);
        gridPane.add(errorText, 0, 4, 1, 1);
        //show window
        Scene scene = new Scene(gridPane, 600, 300);
        stage.setScene(scene);
        stage.setTitle("Cookie Clicker");
        stage.show();
    }
}
