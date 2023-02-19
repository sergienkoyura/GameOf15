package com.example.gameof15;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage){
        try {
            filesExists();
            stage.initStyle(StageStyle.UNDECORATED);
            Image icon = new Image("icon15.png");
            stage.getIcons().add(icon);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/GameOf15/start.fxml")));
            Scene startScene = new Scene(root);

            stage.setScene(startScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //перевірка на існування файлів
    protected void filesExists() throws IOException {
        String fS = "saver.txt";
        String fR = "rating.txt";
        File fileSaver = new File(fS), fileRating = new File(fR);
        if (!fileSaver.exists())
            fileSaver.createNewFile();
        if (!fileRating.exists())
            fileRating.createNewFile();
    }

    public static void main(String[] args) {
        launch(args);
    }
}