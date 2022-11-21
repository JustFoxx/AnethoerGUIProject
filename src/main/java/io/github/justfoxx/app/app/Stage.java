package io.github.justfoxx.app.app;

import javafx.application.Application;

public class Stage extends Application {
    public static void init(String[] args) {
        launch(args);
    }
    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {
        AccountStage.init(primaryStage);
    }
}
