package com.example.gameof15;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SaveTargetController {

    @FXML
    private void exitSave(MouseEvent e) {
        //закриття вікна
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }
}
