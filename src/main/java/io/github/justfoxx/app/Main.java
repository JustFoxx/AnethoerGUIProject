package io.github.justfoxx.app;

import io.github.justfoxx.app.app.Stage;
import io.github.justfoxx.app.db.Data;
import io.github.justfoxx.app.db.DatabaseBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;

public class Main extends Application {
    public static final Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);
    public static Data data;
    public static DatabaseBuilder databaseBuilder = new DatabaseBuilder();
    public static void main(String[] args) throws Exception {
        databaseBuilder.load();
        try {
            databaseBuilder.readData();
        } catch (Exception e) {
            try {
                databaseBuilder.createData();
            } catch (Exception exception) {
                throw new Exception(exception);
            }
        }
        data = databaseBuilder.data;
        Stage.init(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {}
}
