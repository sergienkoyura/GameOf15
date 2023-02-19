package com.example.gameof15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void resetAuto(ActionEvent e) throws IOException {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        //завантажуємо ігрове вікно
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/game.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        //перемішуємо поле автоматично
        GameController gc = loader.getController();
        gc.reset();
    }

    @FXML
    private void resetManually(ActionEvent e) throws IOException {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        //завантажуємо ігрове вікно
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/game.fxml"));
        root = loader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        //перемішуємо поле вручну
        GameController gc = loader.getController();
        gc.resetManually();
    }
}
