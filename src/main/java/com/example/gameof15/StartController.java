package com.example.gameof15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    private Stage stage;

    @FXML
    private void switchToGame(ActionEvent e) throws IOException {
        //перехід в вікно запиту про режим гри
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/askGame.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    private void exitGame(ActionEvent e){
        //закриття вікна
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }


}
