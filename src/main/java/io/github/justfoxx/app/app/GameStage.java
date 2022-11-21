package io.github.justfoxx.app.app;

import io.github.justfoxx.app.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameStage {
    private static int getCost() {
        return (int) Math.pow(1.6,AccountStage.account.level*1.6);
    }

    public static void init(Stage stage) throws Exception {
        //stage events
        stage.onCloseRequestProperty().setValue(e -> {
            try {
                Main.databaseBuilder.saveData();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        //text
        Label count = new Label(" "+AccountStage.account.i);
        Label text2 = new Label("Level " + (AccountStage.account.level-1));
        Label text3 = new Label("Cost  " + getCost() + "\uD83C\uDF6A");
        count.setStyle("-fx-font-size: 50px;");
        text2.setStyle("-fx-font-size: 20px;");
        text3.setStyle("-fx-font-size: 20px;");
        //buttons
        Button cookie = new Button("\uD83C\uDF6A");
        Button button2 = new Button("Upgrade!");
        button2.setDisable(true);
        button2.setStyle("-fx-font-size: 20px;");
        cookie.setStyle("-fx-font-size: 50px; -fx-text-fill: chocolate;");

        cookie.setOnMouseClicked(e -> {
            AccountStage.account.i += AccountStage.account.level;
            count.setText(" "+AccountStage.account.i);
            if (AccountStage.account.i >= getCost()) {
                button2.setDisable(false);
            }
        });
        button2.setOnMouseClicked(e -> {
            AccountStage.account.i -= getCost();
            AccountStage.account.level++;
            text2.setText("Level " + (AccountStage.account.level-1));
            text3.setText("Cost  " + getCost() + "\uD83C\uDF6A");
            count.setText(" "+AccountStage.account.i);
            if (AccountStage.account.i < getCost()) {
                button2.setDisable(true);
            }
        });
        //grid
        GridPane gridPane = new GridPane();
        gridPane.add(cookie, 0, 0, 1, 1);
        gridPane.add(count, 1, 0, 1, 1);
        gridPane.add(text2, 0, 1, 1, 1);
        gridPane.add(text3, 0, 2, 1, 1);
        gridPane.add(button2, 0, 3, 1, 1);
        //show window
        Scene scene = new Scene(gridPane, 600, 300);
        stage.setScene(scene);
        stage.setTitle("Cookie Clicker");
        stage.show();
    }
}
