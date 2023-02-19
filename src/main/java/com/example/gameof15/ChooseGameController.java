package com.example.gameof15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChooseGameController extends MainApplication {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void newGame(ActionEvent e) throws IOException {
        // завантажуємо вікно вибору рестарту
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/askReset.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void savedGame(ActionEvent e) throws IOException {
        // завантажуємо збережену гру, перевіряємо чи існує файл
        filesExists();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        FileReader fr = new FileReader("saver.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        //якщо файл порожній
        if (line == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/saveTarget.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.stage);
            stage.centerOnScreen();

            stage.showAndWait();
            stage.close();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/game.fxml"));
            root = loader.load();
            scene = new Scene(root);
            stage.setScene(scene);

            stage.centerOnScreen();
            stage.show();

            // викликаємо метод з GameController для завантаження поля
            GameController gc = loader.getController();
            gc.loadGame(line);
        }
        fr.close();
        br.close();
    }
}