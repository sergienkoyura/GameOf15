package com.example.gameof15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileWriter;
import java.io.IOException;

public class WinController {

    @FXML
    private Button ButtonSaveResult;

    @FXML
    private Label LabelResult;

    private Stage stage;
    private static boolean saved = false;

    public void setSave(boolean s) {
        saved = s;
    }

    public void setLabelResult(String text) {
        LabelResult.setText("Переміщення: " + text);
    }

    @FXML
    private void resetGameWin(ActionEvent e) throws IOException {
        //перехід в вікно запиту про тип перемішування
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/askReset.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    @FXML
    private void exitGame(ActionEvent e) throws IOException {
        //очищаємо файл із збереженням гри та виходимо з гри
        FileWriter fw = new FileWriter("saver.txt");
        fw.flush();
        fw.close();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveResult(ActionEvent e) throws IOException {

        //викликаємо вікно запиту про ім'я гравця
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/saveInput.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.stage);
        stage.centerOnScreen();
        //передаємо к-сть переміщень в SaveInputController
        SaveInputController gc = loader.getController();
        gc.setMoves(Integer.parseInt(LabelResult.getText().substring(LabelResult.getText().indexOf(" ") + 1)));
        stage.showAndWait();

        stage.close();
        //якщо збереження відбулось - вимикаємо кнопку збереження
        if (saved) {
            ButtonSaveResult.setDisable(true);
        }
    }
}