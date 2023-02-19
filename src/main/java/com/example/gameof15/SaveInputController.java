package com.example.gameof15;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SaveInputController {

    @FXML
    private TextField LabelName;

    private Stage stage;
    private static int moves = 0;

    public void setMoves(int m) {
        moves = m;
    }

    @FXML
    private void exitSaver(ActionEvent e) {
        //закриття вікна
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void save(ActionEvent e) throws IOException {
        //якщо рядок для вводу непустий
        String text = LabelName.getText();
        LabelName.setText(text.trim());
        if (!LabelName.getText().isEmpty()) {
            //створюємо список гравців за іменем та к-стю ходів
            ArrayList<String> rAl = new ArrayList<>();
            rAl.add(LabelName.getText() + " " + moves);
            String line;
            FileReader fr = new FileReader("rating.txt");
            BufferedReader br = new BufferedReader(fr);
            //зчитуємо існуючі рядки файлу рейтингу
            while ((line = br.readLine()) != null)
                rAl.add(line);
            /*застосовуємо лямбда-вираз для реалізації функціонального інтерфейсу Comparator (метод compare)
             * сортуємо всі рядки списку за к-стю ходів*/
            rAl.sort((o1, o2) -> {
                String[] r1 = o1.split(" ");
                String[] r2 = o2.split(" ");
                return Integer.parseInt(r1[1]) - Integer.parseInt(r2[1]);
            });
            // записуємо посортований список в очищений файл
            FileWriter fw = new FileWriter("rating.txt", false);
            for (String l : rAl) {
                fw.write(l + System.getProperty("line.separator"));
            }
            fr.close();
            fw.close();
            br.close();
            //для коректного виклику методу з іншого класу необхідно завантажувати loader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/win.fxml"));
            Parent root = loader.load();
            /* викликаємо метод з WinController, в який передаємо інформацію про те,
             * що поточного гравця успішно збережено*/

            WinController wc = loader.getController();
            wc.setSave(true);

            //закриваємо вікно
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
