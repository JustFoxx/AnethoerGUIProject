package io.github.justfoxx.app.app;

import io.github.justfoxx.app.Main;
import io.github.justfoxx.app.db.Data;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CreatingAccountStage {
    public static void init(Stage stage) throws Exception {
        //stuff
        Label main = new Label("Create new account!");
        var textField = new TextField();
        var newButton = new Button("Create");
        newButton.setOnMouseClicked(e -> {
            if(textField.getText().isEmpty()) {
                return;
            }
            var account = new Data.Account();
            account.name = textField.getText();
            Main.data.accounts.add(account);
            AccountStage.account = account;
            try {
                AccountStage.init(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        //make it better
        main.setStyle("-fx-font-size: 30px;");
        main.setAlignment(Pos.CENTER);
        GridPane.setHalignment(main, HPos.CENTER);

        textField.setPromptText("Enter your name");
        textField.setFocusTraversable(false);
        //grid
        var hbox = new HBox();
        var gridPane = new GridPane();
        gridPane.add(main, 0, 0, 2, 1);
        gridPane.add(textField, 0, 1, 1, 1);
        gridPane.add(newButton, 1, 1, 1, 1);
        hbox.setSpacing(10);
        hbox.getChildren().add(gridPane);
        hbox.setAlignment(Pos.CENTER);

        //scene
        Scene scene = new Scene(hbox, 600, 300);
        stage.setTitle("Creating Account");
        stage.setScene(scene);
    }
}
