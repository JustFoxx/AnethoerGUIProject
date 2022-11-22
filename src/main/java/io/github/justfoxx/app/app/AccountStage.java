package io.github.justfoxx.app.app;

import io.github.justfoxx.app.Main;
import io.github.justfoxx.app.db.Data;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AccountStage {
    public static Data.Account account;
    public static void init(Stage stage) throws Exception {
        //text

        Label welcome = new Label("Welcome!");
        Label choosePlayer = new Label("Choose a player!");
        //choose player

        List<Button> buttons = new ArrayList<>();
        if(Main.data.accounts.size() > 0) {
            for (int i = 0; i < Main.data.accounts.size(); i++) {
                var button = new Button(Main.data.accounts.get(i).name);
                int finalI = i;
                button.setOnMouseClicked(e -> {
                    account = Main.data.accounts.get(finalI);
                    try {
                        GameStage.init(stage);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttons.add(button);
            }
            newPlayer(stage, buttons);
        } else {
            newPlayer(stage, buttons);
        }
        //make it better

        welcome.setStyle("-fx-font-size: 30px;");
        welcome.setAlignment(Pos.CENTER);
        choosePlayer.setStyle("-fx-font-size: 20px;");
        choosePlayer.setAlignment(Pos.CENTER);
        //grid

        var hbox = new HBox();
        var gridPane = new GridPane();
        gridPane.add(welcome, 0, 0, 2 ,1);
        gridPane.add(choosePlayer, 0, 1, 2, 1);
        GridPane.setHalignment(choosePlayer, HPos.CENTER);
        GridPane.setHalignment(welcome, HPos.CENTER);
        for (int i = 0; i < buttons.size(); i++) {
            gridPane.add(buttons.get(i), 0, i+2, 1, 1);
            //delete button
            if(i == buttons.size()-1) continue;
            var deleteButton = new Button("\uD83D\uDDD1️");
            int finalI = i;
            deleteButton.setOnMouseClicked(e -> {
                Main.data.accounts.remove(finalI);
                try {
                    init(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            gridPane.add(deleteButton, 1, i+2, 1, 1);
        }
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(gridPane);
        hbox.setSpacing(10);
        //stage

        Scene scene = new Scene(hbox, 600, 300);
        stage.setScene(scene);
        stage.setTitle("Cookie Clicker");
        stage.show();
    }

    private static void newPlayer(Stage stage, List<Button> buttons) throws Exception {
        var button = new Button("Create a new player!");
        button.onMouseClickedProperty().setValue(e -> {

            try {
                CreatingAccountStage.init(stage);
                //GameStage.init(stage);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        buttons.add(button);
    }
}
