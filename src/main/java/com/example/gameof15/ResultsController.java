package com.example.gameof15;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class ResultsController extends MainApplication{

    @FXML
    private ImageView ImageExitRating;

    @FXML
    private ImageView ImageResetRating;

    @FXML
    private Label LabelMoves1;

    @FXML
    private Label LabelMoves2;

    @FXML
    private Label LabelMoves3;

    @FXML
    private Label LabelName1;

    @FXML
    private Label LabelName2;

    @FXML
    private Label LabelName3;

    public void setRating() throws IOException {
        //перевірка на існування файлу
        filesExists();
        FileReader fr = new FileReader("rating.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        //створюємо список із рядків файлу
        ArrayList<String> al = new ArrayList<>();
        while((line = br.readLine())!=null){
            al.add(line);
        }

        //розташовуємо перші 3 елементи списку в таблицю
        if(al.size()>=1){
            String[] temp = al.get(0).split(" ");
            LabelName1.setText(temp[0]);
            LabelMoves1.setText(temp[1]);
        }
        if(al.size()>=2){
            String[] temp = al.get(1).split(" ");
            LabelName2.setText(temp[0]);
            LabelMoves2.setText(temp[1]);
        }
        if(al.size()>=3){
            String[] temp = al.get(2).split(" ");
            LabelName3.setText(temp[0]);
            LabelMoves3.setText(temp[1]);
        }
        fr.close();
        br.close();
    }

    @FXML
    private void exitRating(MouseEvent e){
        //закриття вікна
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void resetRating(MouseEvent e) throws IOException {
        //обнулення рейтингу
        LabelMoves1.setText("-");
        LabelMoves2.setText("-");
        LabelMoves3.setText("-");
        LabelName1.setText("-");
        LabelName2.setText("-");
        LabelName3.setText("-");

        //очищення файлу
        FileWriter fw = new FileWriter("rating.txt");
        fw.flush();
        fw.close();
    }

    @FXML
    private void initialize(){
        //додаємо підказки на кнопки
        ArrayList<Tooltip> tt = new ArrayList<>();
        tt.add(new Tooltip("Натисніть для скидання рейтингу"));
        tt.add(new Tooltip("Натисніть для повернення до гри"));
        for (Tooltip t: tt) {
            t.setFont(Font.font("Calibri"));
            t.setStyle("-fx-font-size: 20");
        }
        Tooltip.install(ImageResetRating, tt.get(0));
        Tooltip.install(ImageExitRating, tt.get(1));
    }
}
