package com.example.gameof15;

import java.io.*;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GameController {

    @FXML
    private GridPane GridPaneButtons;

    @FXML
    private Button Button1;

    @FXML
    private Button Button10;

    @FXML
    private Button Button11;

    @FXML
    private Button Button12;

    @FXML
    private Button Button13;

    @FXML
    private Button Button14;

    @FXML
    private Button Button15;

    @FXML
    private Button Button16;

    @FXML
    private Button Button2;

    @FXML
    private Button Button3;

    @FXML
    private Button Button4;

    @FXML
    private Button Button5;

    @FXML
    private Button Button6;

    @FXML
    private Button Button7;

    @FXML
    private Button Button8;

    @FXML
    private Button Button9;

    @FXML
    private ImageView ImageReset;

    @FXML
    public AnchorPane MainPage;

    @FXML
    private ImageView ImageExit;

    @FXML
    private Label LabelMoves;

    @FXML
    private Label LabelTextMove;

    @FXML
    private ImageView ImageStop;

    @FXML
    private ImageView ImageRating;

    @FXML
    private ImageView ImageResetManually;

    @FXML
    private ImageView ImageAuto;

    private Stage stage;
    private static int num = 0;
    private static boolean checkerLast = false;


    public void loadGame(String loader) {
        ArrayList<Button> buttons = new ArrayList<>();
        initButtons(buttons, Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8);
        initButtons(buttons, Button9, Button10, Button11, Button12, Button13, Button14, Button15, Button16);

        //розділяємо список кнопок та к-сть переміщень (1.2.3...16/0)
        String[] res = loader.split("/");
        //розділяємо кнопки
        String[] buttonsNums = res[0].split("\\.");

        //заповнюємо список кнопок за їх номерами
        ArrayList<Button> newButtons = new ArrayList<>();
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
                if (Integer.parseInt(buttonsNums[i]) == Integer.parseInt(buttons.get(j).getText())) {
                    newButtons.add(buttons.get(j));
                    break;
                }

        //заповнюємо поле кнопок
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++, count++) {
                Node tempNode = newButtons.get(count);
                GridPaneButtons.getChildren().remove(tempNode);
                GridPaneButtons.add(tempNode, j, i);
            }
        }
        //заповнюємо поле з к-стю переміщень
        LabelMoves.setText(res[1]);
    }

    public void reset() {
        int counterIsSolvable;
        ArrayList<Button> buttons = new ArrayList<>();
        initButtons(buttons, Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8);
        initButtons(buttons, Button9, Button10, Button11, Button12, Button13, Button14, Button15, Button16);
        do {
            ArrayList<Integer> nums = new ArrayList<>();
            ArrayList<Button> newButtons = new ArrayList<>();

            Random rand = new Random();
            // запис унікальних чисел в список для виставлення кнопок
            for (int i = 0; i < 15; i++) {
                int num = rand.nextInt(15) + 1;
                if (nums.contains(num))
                    i--;
                else {
                    nums.add(num);
                    for (int j = 0; j < 15; j++) {
                        if (buttons.get(j).getText().equals(Integer.toString(num))) {
                            newButtons.add(buttons.get(j));
                            break;
                        }
                    }
                }
            }
            // додаємо в список пусту клітинку останньою для правильності розстановки
            newButtons.add(Button16);

            /*цикл заповнення поля для гри:
             * знаходимо поточний елемент на полі, видаляємо його
             * ставимо поточний елемент на порядкове місце */
            int count = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++, count++) {
                    Node tempNode = newButtons.get(count);
                    GridPaneButtons.getChildren().remove(tempNode);
                    GridPaneButtons.add(tempNode, j, i);
                }
            }
            /* перевірка на правильність розстановки:
             * якщо поточне число більше за наступне, збільшуємо counter на 1
             * перевіряємо кожну пару
             * якшо кількість таких пар парна - п'ятнашки можна розв'язати
             * інакше - запускаємо алгоритм ще раз */
            counterIsSolvable = 0;
            for (int i = 0; i < 15; i++) {
                for (int j = i + 1; j < 15; j++) {
                    if (nums.get(i) > nums.get(j)) {
                        counterIsSolvable++;
                    }
                }
            }
        } while (!(counterIsSolvable % 2 == 0));
        // обнуляємо к-сть переміщень та статичні атрибути
        LabelMoves.setText("0");
        num = 0;
        checkerLast = false;
    }

    public void resetManually() {
        ArrayList<Button> buttons = new ArrayList<>();
        ArrayList<Button> newButtons = new ArrayList<>();

        initButtons(buttons, Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8);
        initButtons(buttons, Button9, Button10, Button11, Button12, Button13, Button14, Button15, Button16);
        for (int i = 0; i < 16; i++)
            newButtons.add(buttons.get(i));

        /*цикл заповнення поля для гри:
         * знаходимо поточний елемент на полі, видаляємо його
         * ставимо поточний елемент на порядкове місце */
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++, count++) {
                Node tempNode = newButtons.get(count);
                GridPaneButtons.getChildren().remove(tempNode);
                GridPaneButtons.add(tempNode, j, i);
            }
        }

        LabelTextMove.setText("Для завершення ->");
        LabelMoves.setText("0");
        LabelMoves.setOpacity(0);
        ImageStop.setOpacity(1);
        ImageStop.setDisable(false);
        ImageReset.setDisable(true);
        ImageResetManually.setDisable(true);
        ImageAuto.setDisable(true);
        ImageExit.setDisable(true);
    }

    @FXML
    private void stopReset(MouseEvent e) {
        // користувач має зробити щонайменше 10 переміщень для повернення в гру
        if (Integer.parseInt(LabelMoves.getText()) >= 10) {
            LabelTextMove.setText("Переміщення:");
            LabelMoves.setOpacity(1);
            LabelMoves.setText("0");
            ImageStop.setOpacity(0);
            ImageStop.setDisable(true);
            ImageReset.setDisable(false);
            ImageResetManually.setDisable(false);
            ImageAuto.setDisable(false);
            ImageExit.setDisable(false);
            num = 0;
            checkerLast = false;
        }
    }

    private void initButtons(ArrayList<Button> buttons, Button b1, Button b2, Button b3, Button b4, Button b5, Button b6, Button b7, Button b8) {
        // ініціалізуємо кнопки
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);
        buttons.add(b6);
        buttons.add(b7);
        buttons.add(b8);
    }

    @FXML
    private void autoResetGame(MouseEvent e) {
        reset();
    }

    @FXML
    private void manuallyResetGame(MouseEvent e) {
        resetManually();
    }

    @FXML
    private void showRating(MouseEvent e) throws IOException {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        // записуємо шлях моделі наступного вікна для його виведення на екран
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/GameOf15/rating.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // прибираємо оформлення вікна, забороняємо взаємодіяти з вікном на фоні
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.stage);
        stage.centerOnScreen();

        // викликаємо метод для ініціалізації вікна рейтингу
        ResultsController rc = loader.getController();
        rc.setRating();

        stage.showAndWait();
        stage.close();
    }

    private Button getNodeByRowColumnIndex(int row, int column, GridPane gridPane) {
        Button result = null;
        // створюємо список елементів поля (сітки) та знаходимо потрібний
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = (Button) node;
                break;
            }
        }
        return result;
    }

    @FXML
    private void gridIsClicked(MouseEvent e) throws IOException {
        /*цикл для знаходження встановлений на свої місця елементів
         * в змінну 'c' запишемо перший невстановлений на місце елемент*/
        int c = 1;
        boolean checker = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++, c++) {
                Button temp = getNodeByRowColumnIndex(i, j, GridPaneButtons);
                if (!temp.getText().equals(Integer.toString(c))) {
                    checker = false;
                    break;
                }
            }
            if (!checker)
                break;
        }

        // присвоюємо статичному атрибуту значення невстановленого елемента
        num = c;

        // знаходимо координати кліку миші
        int x = (int) e.getX() / 95;
        int y = (int) e.getY() / 95;
        int y16 = GridPane.getRowIndex(Button16);
        int x16 = GridPane.getColumnIndex(Button16);
        changeAlgorithm(x, y, x16, y16);

        /*перевірка для закінчення гри
         * ImageStop.isDisable() використовуємо для перевірки,
         * чи знаходимось зараз в режимі розстановки власноруч*/
        if (gameOver() && ImageStop.isDisable())
            winGame();
    }

    private void changeAlgorithm(int x, int y, int x16, int y16) {
        /*алгоритм працює наступним чином:
         * 1) визначаємо де знаходимось: порожня клітинка на рівні кліку по рядках чи стовбцях
         *   1.1) рухаємось по індексах в залежності від розташування п-ї клітинки (y < y16...)
         * 2) записуємо в список кнопки, які знаходяться між клітинкою та кнопкою на яку натиснули (включно)
         * 3) переміщуємо порожню клітинку по списку temp.size()-1 разів (ставимо її попереду списку)
         * 4) додаємо переміщення в залежності від к-стї переміщень порожньої клітинки
         * 5) додаємо в сітку відпрацьовані кнопки*/
        if (x == x16) {
            ArrayList<Button> temp = new ArrayList<>();
            if (y < y16) {
                for (int i = y; i <= y16; i++)
                    temp.add(getNodeByRowColumnIndex(i, x, GridPaneButtons));
                for (Node tempNode : temp) {
                    GridPaneButtons.getChildren().remove(tempNode);
                }
                int count = temp.size() - 1;
                // цикл декременту оскільки порожня клітинка остання в списку
                for (int i = count; i > 0; i--) {
                    Button t = temp.get(i);
                    temp.set(i, temp.get(i - 1));
                    temp.set(i - 1, t);
                    LabelMoves.setText(Integer.toString(Integer.parseInt(LabelMoves.getText()) + 1));
                }

            } else if (y > y16) {
                for (int i = y16; i <= y; i++)
                    temp.add(getNodeByRowColumnIndex(i, x, GridPaneButtons));
                for (Node tempNode : temp) {
                    GridPaneButtons.getChildren().remove(tempNode);
                }
                int count = temp.size() - 1;
                // цикл інкременту оскільки порожня клітинка перша в списку
                for (int i = 0; i < count; i++) {
                    Button t = temp.get(i);
                    temp.set(i, temp.get(i + 1));
                    temp.set(i + 1, t);
                    LabelMoves.setText(Integer.toString(Integer.parseInt(LabelMoves.getText()) + 1));
                }
            }

            int count = Math.min(y, y16);
            for (Button t : temp) {
                GridPaneButtons.add(t, x, count++);
            }

        } else if (y == y16) {
            ArrayList<Button> temp = new ArrayList<>();
            if (x < x16) {
                for (int i = x; i <= x16; i++)
                    temp.add(getNodeByRowColumnIndex(y, i, GridPaneButtons));
                for (Node tempNode : temp) {
                    GridPaneButtons.getChildren().remove(tempNode);
                }
                int count = temp.size() - 1;
                // цикл декременту оскільки порожня клітинка остання в списку
                for (int i = count; i > 0; i--) {
                    Button t = temp.get(i);
                    temp.set(i, temp.get(i - 1));
                    temp.set(i - 1, t);
                    LabelMoves.setText(Integer.toString(Integer.parseInt(LabelMoves.getText()) + 1));
                }
            } else {
                for (int i = x16; i <= x; i++)
                    temp.add(getNodeByRowColumnIndex(y, i, GridPaneButtons));
                for (Node tempNode : temp) {
                    GridPaneButtons.getChildren().remove(tempNode);
                }
                int count = temp.size() - 1;
                // цикл інкременту оскільки порожня клітинка перша в списку
                for (int i = 0; i < count; i++) {
                    Button t = temp.get(i);
                    temp.set(i, temp.get(i + 1));
                    temp.set(i + 1, t);
                    LabelMoves.setText(Integer.toString(Integer.parseInt(LabelMoves.getText()) + 1));
                }
            }
            int count = Math.min(x, x16);
            for (Button t : temp) {
                GridPaneButtons.add(t, count++, y);
            }
        }
    }

    private void winGame() throws IOException {
        //беремо контроль над поточним стейджем (оскільки метод викликається без участі користувача)
        stage = (Stage) MainPage.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/GameOf15/win.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //передаємо к-сть переміщень
        WinController wc = loader.getController();
        wc.setLabelResult(LabelMoves.getText());
    }

    private boolean gameOver() {
        // перевіряємо чи на місцях сітки упорядковано знаходяться кнопки
        boolean checker = false;
        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++, count++) {
                Button temp = getNodeByRowColumnIndex(i, j, GridPaneButtons);
                if (Integer.parseInt(temp.getText()) == count) {
                    checker = true;
                } else {
                    checker = false;
                    break;
                }
            }
            if (!checker) break;
        }
        return checker;
    }

    @FXML
    private void exitGame(MouseEvent e) throws IOException {
        // знаходимо порядок кнопок на полі
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.append(getNodeByRowColumnIndex(i, j, GridPaneButtons).getText()).append(".");
            }
        }
        result.deleteCharAt(result.length() - 1);

        // дописуємо кількість переміщень
        result.append("/").append(LabelMoves.getText());

        // записуємо інформацію у файл
        FileWriter fw = new FileWriter("saver.txt");
        fw.write(result.toString());
        fw.close();

        //закриваємо вікно (виходимо з програми)
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void autoWin(MouseEvent e) throws IOException {
        // ініціалізуємо кнопки в звичайному порядку
        ArrayList<Button> buttons = new ArrayList<>();
        initButtons(buttons, Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8);
        initButtons(buttons, Button9, Button10, Button11, Button12, Button13, Button14, Button15, Button16);

        //знаходимо першу невстановлену кнопку
        int c = 1;
        boolean checker = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++, c++) {
                Button temp = getNodeByRowColumnIndex(i, j, GridPaneButtons);
                if (!temp.getText().equals(Integer.toString(c))) {
                    checker = false;
                    break;
                }
            }
            if (!checker)
                break;
        }

        //ініціалізуємо координати порожньої клітинки та поточної кнопки (невстановленої)
        Button but = buttons.get(c - 1);
        int y16 = GridPane.getRowIndex(Button16);
        int x16 = GridPane.getColumnIndex(Button16);
        int y0 = GridPane.getRowIndex(but);
        int x0 = GridPane.getColumnIndex(but);
        int x = 0;
        int y = 0;

        //створюємо об'єкт класу Pair для зручної передачі координат
        Pair res = new Pair(x, y);

        //оголошуємо та ініціалізуємо булеві змінні для перевірки позицій (розстановок) кнопок
        boolean ch1 = false, ch2 = false, ch3 = false, ch4 = false, ch5 = false, ch6 = false, ch7 = false, ch8 = false, ch9 = false,
                ch10 = false, ch11 = false, ch12 = false, ch13 = false, ch14 = false, ch15 = false, ch16 = false, ch17 = false, ch18 = false,
                flag4_8 = false, flag10 = false, flag11 = false, flag12 = false, flag13 = false;
        boolean ch1_1, ch1_2, ch2_1, ch2_2, ch2_3, ch2_4, ch2_5, ch3_1, ch3_2, ch3_3, ch3_4, ch3_5, ch4_1, ch4_2, ch4_3, ch4_4, ch4_5, ch4_6, ch4_7, ch16_1;

        //за допомогою наступного алгоритму встановлюємо на місця кнопки: {4}, [8, 15]

        //оскільки алгоритм для встановлення 4 і 8 ідентичний, перевіряємо чи значення поточної кнопки менше 9
        if (c <= 8) {
            num = c <= 4 ? 4 : 8;

            /*здійснюємо перевірки позицій кнопок:
             * за деяких розташувань кнопок буде застосований певний алгоритм для їх встановлення на місце*/
            //перевірки позицій 1 чи 5 (залежно від num)
            ch1_1 = Integer.parseInt(buttons.get(num - 4).getText()) == Integer.parseInt(getNodeByRowColumnIndex((num - 4) / 4, (num - 4) % 4, GridPaneButtons).getText());
            ch1_2 = Integer.parseInt(buttons.get(num - 4).getText()) == Integer.parseInt(getNodeByRowColumnIndex((num - 4) / 4 + 1, (num - 4) % 4, GridPaneButtons).getText());
            //перевірки позицій 2 чи 6..
            ch2_1 = Integer.parseInt(buttons.get(num - 3).getText()) == Integer.parseInt(getNodeByRowColumnIndex((num - 3) / 4, (num - 3) % 4, GridPaneButtons).getText());
            ch2_2 = Integer.parseInt(buttons.get(num - 3).getText()) == Integer.parseInt(getNodeByRowColumnIndex((num - 3) / 4, (num - 3) % 4 - 1, GridPaneButtons).getText());
            //перевірки позицій 3 чи 7..
            ch3_1 = Integer.parseInt(buttons.get(num - 2).getText()) == Integer.parseInt(getNodeByRowColumnIndex((num - 2) / 4, (num - 2) % 4, GridPaneButtons).getText());
            ch3_2 = Integer.parseInt(buttons.get(num - 2).getText()) == Integer.parseInt(getNodeByRowColumnIndex((num - 2) / 4, (num - 2) % 4 - 1, GridPaneButtons).getText());
            //перевірки позицій 4 чи 8..
            ch4_1 = Integer.parseInt(buttons.get(num - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(num / 4, (num - 1) % 4, GridPaneButtons).getText());
            ch4_2 = Integer.parseInt(buttons.get(num - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(num / 4, (num - 1) % 4 - 1, GridPaneButtons).getText());
            ch4_3 = Integer.parseInt(buttons.get(num - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(num / 4 - 1, (num - 1) % 4 - 1, GridPaneButtons).getText());
            //перевірка позиції порожньої клітинки
            ch16_1 = Integer.parseInt(Button16.getText()) == Integer.parseInt(getNodeByRowColumnIndex(num / 4 - 1, (num - 1) % 4, GridPaneButtons).getText());

            ch1 = ch1_1 && ch2_1 && ch3_1 && ch4_1 && !ch16_1;
            ch2 = ch1_2 && ch2_1 && ch3_1 && ch4_1;
            ch3 = ch1_2 && ch2_2 && ch3_2 && ch4_1;
            ch4 = ch1_2 && ch2_2 && ch3_2 && ch4_2;
            ch5 = ch1_2 && ch2_2 && ch3_2 && ch4_3;

            //якщо хоча б одне з розташувань кнопок підходить для виконання алгоритму, змінюємо значення змінної на 'true'
            if (ch1 || ch2 || ch3 || ch4 || ch5)
                flag4_8 = true;
        } else {

            //статичній змінній num завжди присвоюємо значення першої невстановленої кнопки
            if (c > num) {
                num = c;
            }

            // для кнопки 9 застосовуємо звичайний алгоритм встановлення її на місце
            if (num == 9) {
                res = tipAlgorithm(y0, x0, y16, x16, num, 2);
            } else {
                //для виставлення 9
                ch1_1 = Integer.parseInt(buttons.get(9 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 0, GridPaneButtons).getText());
                ch1_2 = Integer.parseInt(buttons.get(9 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 0, GridPaneButtons).getText());
                // для виставлення 10 в [9,10]
                ch2_1 = Integer.parseInt(buttons.get(10 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 0, GridPaneButtons).getText());
                ch2_2 = Integer.parseInt(buttons.get(10 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 1, GridPaneButtons).getText());
                ch2_3 = Integer.parseInt(buttons.get(10 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 1, GridPaneButtons).getText());
                ch2_4 = Integer.parseInt(buttons.get(10 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 2, GridPaneButtons).getText());
                // для виставлення 10 в [9,11] та в [9,12]
                ch2_5 = Integer.parseInt(buttons.get(10 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 0, GridPaneButtons).getText());
                // для виставлення 11
                ch3_1 = Integer.parseInt(buttons.get(11 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 0, GridPaneButtons).getText());
                ch3_2 = Integer.parseInt(buttons.get(11 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 1, GridPaneButtons).getText());
                ch3_3 = Integer.parseInt(buttons.get(11 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 2, GridPaneButtons).getText());
                ch3_4 = Integer.parseInt(buttons.get(11 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 1, GridPaneButtons).getText());
                ch3_5 = Integer.parseInt(buttons.get(11 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 2, GridPaneButtons).getText());
                // для виставлення 12
                ch4_1 = Integer.parseInt(buttons.get(12 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 0, GridPaneButtons).getText());
                ch4_2 = Integer.parseInt(buttons.get(12 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 1, GridPaneButtons).getText());
                ch4_3 = Integer.parseInt(buttons.get(12 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 2, GridPaneButtons).getText());
                ch4_4 = Integer.parseInt(buttons.get(12 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 3, GridPaneButtons).getText());
                ch4_5 = Integer.parseInt(buttons.get(12 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 1, GridPaneButtons).getText());
                ch4_6 = Integer.parseInt(buttons.get(12 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 2, GridPaneButtons).getText());
                ch4_7 = Integer.parseInt(buttons.get(12 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 3, GridPaneButtons).getText());

                //якщо невстановлена кнопка '10'
                if (num == 10) {
                    //перевіряємо всі можливі розташування кнопок
                    ch1 = ch1_1 && ch2_1;
                    ch2 = ch1_1 && ch2_2;
                    ch3 = ch1_2 && ch2_2;
                    ch4 = ch1_2 && ch2_3;
                    ch5 = ch1_2 && ch2_4;
                    ch6 = ch1_1 && ch2_4;
                    /*якщо хоча б одне з розташувань кнопок підходить для виконання алгоритму, змінюємо значення змінної на 'true'
                     * інакше змінюємо статичну змінну на значення першої невстановленної кнопки
                     * наприклад, при встановленні кнопок на місця, вони можуть змінювати свою позицію
                     * залежно від алгоритму, тому в цьому випадку кнопка із значенням <= 9 знаходиться
                     * не на своїй позиції, і це розташування не задовольняє алгоритм*/
                    if (ch1 || ch2 || ch3 || ch4 || ch5 || ch6)
                        flag10 = true;
                    else
                        num = c;
                    //встановлюємо останню невпорядковану кнопку на місце
                    if (!flag10)
                        res = tipAlgorithm(y0, x0, y16, x16, num, 2);
                }
                //алгоритми для 11 та 12 ідентичні..
                else if (num == 11) {
                    ch1 = ch1_1 && ch2_3 && ch3_1;
                    ch2 = ch1_1 && ch2_3 && ch3_2;
                    ch3 = ch1_2 && ch2_3 && ch3_2;
                    ch4 = ch1_2 && ch2_5 && ch3_2;
                    ch5 = ch1_2 && ch2_5 && ch3_4;
                    ch6 = ch1_2 && ch2_3 && ch3_5;
                    ch7 = ch1_1 && ch2_3 && ch3_3;
                    ch8 = ch1_2 && ch2_3 && ch3_3;
                    ch9 = ch1_2 && ch2_5 && ch3_3;
                    ch10 = ch1_2 && ch2_5 && ch3_5;
                    if (ch1 || ch2 || ch3 || ch4 || ch5 || ch6 || ch7 || ch8 || ch9 || ch10)
                        flag11 = true;
                    else
                        num = c;
                    if (!flag11)
                        res = tipAlgorithm(y0, x0, y16, x16, num, 2);
                } else if (num == 12) {
                    ch1 = ch1_1 && ch2_3 && ch3_5 && ch4_1;
                    ch2 = ch1_1 && ch2_3 && ch3_5 && ch4_2;
                    ch3 = ch1_2 && ch2_3 && ch3_5 && ch4_2;
                    ch4 = ch1_2 && ch2_5 && ch3_5 && ch4_2;
                    ch5 = ch1_2 && ch2_5 && ch3_5 && ch4_5;
                    ch6 = ch1_2 && ch2_5 && ch3_3 && ch4_5;
                    ch7 = ch1_2 && ch2_5 && ch3_3 && ch4_6;
                    ch8 = ch1_2 && ch2_5 && ch3_2 && ch4_6;
                    ch9 = ch1_2 && ch2_5 && ch3_2 && ch4_7;
                    ch10 = ch1_2 && ch2_5 && ch3_4 && ch4_7;
                    ch11 = ch1_2 && ch2_3 && ch3_5 && ch4_7;
                    ch12 = ch1_1 && ch2_3 && ch3_5 && ch4_3;
                    ch13 = ch1_2 && ch2_3 && ch3_5 && ch4_3;
                    ch14 = ch1_2 && ch2_5 && ch3_4 && ch4_3;
                    ch15 = ch1_2 && ch2_5 && ch3_4 && ch4_6;
                    ch16 = ch1_1 && ch2_3 && ch3_5 && ch4_4;
                    ch17 = ch1_2 && ch2_3 && ch3_5 && ch4_4;
                    ch18 = ch1_2 && ch2_5 && ch3_4 && ch4_4;
                    if (ch1 || ch2 || ch3 || ch4 || ch5 || ch6 || ch7 || ch8 || ch9 || ch10 ||
                            ch11 || ch12 || ch13 || ch14 || ch15 || ch16 || ch17 || ch18)
                        flag12 = true;
                    else
                        num = c;
                    if (!flag12)
                        res = tipAlgorithm(y0, x0, y16, x16, num, 2);
                }
                //якщо найменше невстановлене число >12
                else {
                    //знаходимо позиції '13' та пустої клітинки
                    boolean ch13_1 = Integer.parseInt(buttons.get(13 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 1, GridPaneButtons).getText()),
                            ch13_2 = Integer.parseInt(buttons.get(13 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 2, GridPaneButtons).getText()),
                            ch16_2 = Integer.parseInt(buttons.get(16 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(3, 0, GridPaneButtons).getText()),
                            ch16_3 = Integer.parseInt(buttons.get(16 - 1).getText()) == Integer.parseInt(getNodeByRowColumnIndex(2, 2, GridPaneButtons).getText());

                    if (num == 13) {
                        ch1 = ch1_1 && ch2_3 && ch3_5 && ch4_7;
                        ch2 = ch1_2 && ch2_3 && ch3_5 && ch4_7;
                        ch3 = ch1_2 && ch2_5 && ch3_4 && ch4_6;
                        ch4 = ch1_2 && ch2_5 && ch3_2 && ch4_6;
                        ch5 = ch1_2 && ch2_5 && ch3_2 && ch4_5;
                        //якщо в останньому рядку кнопки впорядковані
                        if (ch13_1 && ch16_2)
                            res = tipAlgorithm(y0, x0, y16, x16, num, 3);
                        else {
                            /*оскільки алгоритм повернення упорядкованого останнього рядка ідентичний з упорядкуванням,
                             * введемо статичну змінну 'checkerLast' для перевірки руху алгоритму
                             * (поки не 'checkerLast' - упорядковуємо) */
                            if (ch1 || ch2 || ch3 || ch4 || ch5)
                                flag13 = true;
                            else {
                                num = c;
                                checkerLast = false;
                            }
                            if (!flag13)
                                res = tipAlgorithm(y0, x0, y16, x16, num, 2);
                            if (ch13_2 && ch16_3) {
                                checkerLast = true;
                            }
                        }
                    }
                    //якщо '14' та '15' не на місцях
                    else {
                        res = tipAlgorithm(y0, x0, y16, x16, num, 3);
                    }
                }
            }

        }

        /*наступні алгоритми для кожного з чисел працюють наступним чином:
         * для кожного розташування кнопок є декілька станів (ch1, ch2..),
         * для них прописані дії, щоб переходити з одного стану в інший
         * (в результаті - до кінцевого (впорядкованого))*/

        //якщо не на місці 4 або 8, перевіряємо кожний стан та переходимо з одного в інший
        if (flag4_8) {
            if (ch1) {
                if (x16 != 0) {
                    res.setX(0);
                    res.setY(y16);
                } else {
                    res.setX(x16);
                    res.setY(y0 - 1);
                }
            } else if (ch2) {
                if (x16 == 0 && y16 == y0 - 1) {
                    res.setX(2);
                    res.setY(y16);
                } else {
                    res = tipAlgorithm(y0, x0, y16, x16, c, 0);
                }
            } else if (ch3) {
                if (x16 != 2) {
                    res.setX(2);
                    res.setY(y16);
                } else {
                    if (y16 != y0) {
                        res.setX(x16);
                        res.setY(y0);
                    } else {
                        res.setX(3);
                        res.setY(y16);
                    }
                }
            } else if (ch4) {
                if (x16 != 3) {
                    if (y16 == y0) {
                        res.setX(x16);
                        res.setY(y16 + 1);
                    } else {
                        if (y16 == y0 - 1) {
                            res.setX(x16);
                            res.setY(y0);
                        } else {
                            res.setX(3);
                            res.setY(y16);
                        }
                    }
                } else {
                    if (y16 != y0 - 1) {
                        res.setX(x16);
                        res.setY(y0 - 1);
                    } else {
                        res.setX(2);
                        res.setY(y16);
                    }
                }
            } else {
                if (x16 != 3) {
                    res.setX(3);
                    res.setY(y16);
                } else {
                    if (y16 != (num - 1) / 4) {
                        res.setX(x16);
                        res.setY((num - 1) / 4);
                    } else {
                        res.setX(0);
                        res.setY(y16);
                    }
                }
            }
        }
        //якщо не на місці 10, перевіряємо кожний стан та переходимо з одного в інший
        else if (flag10) {
            if (ch1) {
                res = tipAlgorithm(y0, x0, y16, x16, num, 2);
            } else if (ch2) {
                if (x16 < x0) {
                    res.setX(x16);
                    res.setY(y16 - 1);
                } else {
                    res = tipAlgorithm(y0, x0, y16, x16, num, 2);
                }
            } else if (ch3) {
                if (x16 == 0) {
                    res.setX(1);
                    res.setY(y16);
                } else {
                    res.setX(x16);
                    res.setY(3);
                }
            } else if (ch4) {
                if (x16 == 1) {
                    res.setX(2);
                    res.setY(y16);
                } else {
                    if (y16 == 3) {
                        res.setY(2);
                        res.setX(x16);
                    } else {
                        res.setX(1);
                        res.setY(y16);
                    }
                }
            } else if (ch5) {
                if (x16 == 1) {
                    res.setX(0);
                    res.setY(y16);
                } else {
                    res.setX(x16);
                    res.setY(3);
                }
            } else {
                res = tipAlgorithm(y0, x0, y16, x16, num, 2);
            }
        }
        //якщо не на місці 11, перевіряємо кожний стан та переходимо з одного в інший
        else if (flag11) {
            if (ch1) {
                if (y16 == 2) {
                    res.setX(x16);
                    res.setY(3);
                } else {
                    res.setX(0);
                    res.setY(y16);
                }
            } else if (ch2) {
                if (x16 == 0) {
                    res.setX(0);
                    res.setY(y16 - 1);
                } else {
                    if (y16 == 2) {
                        res.setX(x16);
                        res.setY(3);
                    } else {
                        res.setX(0);
                        res.setY(y16);
                    }
                }
            } else if (ch3) {
                res.setX(1);
                res.setY(y16);
            } else if (ch4) {
                res.setX(x16);
                res.setY(3);
            } else if (ch5) {
                if (x16 == 1) {
                    res.setX(2);
                    res.setY(y16);
                } else {
                    if (y16 == 3) {
                        res.setX(x16);
                        res.setY(2);
                    } else {
                        res.setX(0);
                        res.setY(y16);
                    }
                }
            } else if (ch6) {
                res = tipAlgorithm(y0, x0, y16, x16, c, 2);
            } else if (ch7) {
                if (x16 < 2) {
                    if (x16 != 0) {
                        res.setX(0);
                        res.setY(y16);
                    } else {
                        res.setX(x16);
                        res.setY(2);
                    }
                } else {
                    res = tipAlgorithm(y0, x0, y16, x16, num, 2);
                }
            } else if (ch8) {
                res.setX(2);
                res.setY(y16);
            } else if (ch9) {
                if (x16 != 2) {
                    res = tipAlgorithm(y0, x0, y16, x16, c, 2);
                } else {
                    res.setX(x16);
                    res.setY(3);
                }
            } else {
                if (x16 == 2) {
                    res.setX(1);
                    res.setY(y16);
                } else {
                    if (y16 == 3) {
                        res.setX(x16);
                        res.setY(2);
                    } else {
                        res.setX(0);
                        res.setY(y16);
                    }
                }
            }
        }
        //якщо не на місці 12, перевіряємо кожний стан та переходимо з одного в інший
        else if (flag12) {
            if (ch1 || ch2 || ch12 || ch16) {
                if (y16 == 2) {
                    res.setX(x16);
                    res.setY(3);
                } else {
                    if (x16 == 0) {
                        res.setX(x16);
                        res.setY(2);
                    } else {
                        res.setX(0);
                        res.setY(y16);
                    }
                }
            } else if (ch3) {
                res.setX(1);
                res.setY(y16);
            } else if (ch13) {
                res.setX(2);
                res.setY(y16);
            } else if (ch17) {
                res.setX(3);
                res.setY(y16);
            } else if (ch4 || ch14 || ch18) {
                res = tipAlgorithm(y0, x0, y16, x16, num, 2);
            } else if (ch5) {
                if (x16 == 1) {
                    res.setX(2);
                    res.setY(y16);
                } else {
                    res.setX(x16);
                    res.setY(2);
                }
            } else if (ch6) {
                res.setX(1);
                res.setY(y16);
            } else if (ch7) {
                if (x16 != 1) {
                    res = tipAlgorithm(y0, x0, y16, x16, c, 2);
                } else {
                    if (y16 == 2) {
                        res.setX(x16);
                        res.setY(3);
                    } else {
                        res.setX(3);
                        res.setY(y16);
                    }
                }
            } else if (ch8) {
                if (y16 == 3) {
                    res.setX(x16);
                    res.setY(2);
                } else {
                    res.setX(1);
                    res.setY(y16);
                }
            } else if (ch9) {
                res.setX(x16);
                res.setY(3);
            } else if (ch10) {
                if (x16 != 2) {
                    res.setX(2);
                    res.setY(y16);
                } else {
                    if (y16 == 3) {
                        res.setX(x16);
                        res.setY(2);
                    } else {
                        res.setX(0);
                        res.setY(y16);
                    }
                }
            } else if (ch11) {
                res.setX(x16);
                res.setY(3);
            } else if (ch15) {
                if (x16 == 2) {
                    res.setX(3);
                    res.setY(y16);
                } else {
                    if (y16 == 3) {
                        res.setX(x16);
                        res.setY(2);
                    } else {
                        res.setX(0);
                        res.setY(y16);
                    }
                }
            }
        }
        /*якщо не на місці 13, перевіряємо кожний стан та переходимо з одного в інший
         * алгоритм спрацьовує для останнього рядку (впорядковує одразу 3 елементи)*/
        else if (flag13) {
            if (ch1) {
                if (x16 != 0) {
                    res.setX(0);
                    res.setY(y16);
                } else {
                    if (y16 == 3) {
                        res.setX(x16);
                        res.setY(2);
                    }
                }
            } else if (ch2) {
                if (checkerLast) {
                    res.setX(x16);
                    res.setY(3);
                    checkerLast = false;
                } else {
                    res.setX(3);
                    res.setY(y16);
                }
            } else if (ch3) {
                if (checkerLast) {
                    if (x16 == 1) {
                        res.setX(3);
                        res.setY(y16);
                    } else {
                        if (y16 == 3) {
                            res.setX(x16);
                            res.setY(2);
                        } else {
                            res.setX(0);
                            res.setY(y16);
                        }
                    }
                } else {
                    if (y16 == 2) {
                        res.setX(x16);
                        res.setY(3);
                    } else {
                        if (x16 == 3) {
                            res.setX(1);
                            res.setY(y16);
                        } else {
                            res.setX(x16);
                            res.setY(2);
                        }
                    }
                }
            } else if (ch4) {
                if (checkerLast) {
                    res.setX(x16);
                    res.setY(3);
                } else {
                    res.setX(2);
                    res.setY(y16);
                }
            } else {
                if (checkerLast) {
                    res.setX(1);
                    res.setY(y16);
                } else {
                    if (x16 == 2) {
                        if (y16 == 2) {
                            res.setX(3);
                            res.setY(y16);
                        } else {
                            res.setX(x16);
                            res.setY(2);
                        }
                    } else {
                        if (y16 == 2) {
                            res.setX(x16);
                            res.setY(3);
                        } else {
                            res.setX(2);
                            res.setY(y16);
                        }
                    }
                }
            }
        }
        /*якщо невпорядковані кнопки в діапазонах [1,3] та [5, 7]
         * та кнопки '4' або '8' не знаходяться на позиції (x = 3, y = row+1),
         * де row - шуканий рядок для кнопки,
         * ставимо їх на свої місця за допомогою наступного алгоритму:*/
        else {
            if (c < 4) {
                res = tipAlgorithm(y0, x0, y16, x16, c, 0);
            } else if (c == 4) {
                res = tipAlgorithm(y0, x0, y16, x16, c, 1);
            } else if (c < 8) {
                res = tipAlgorithm(y0, x0, y16, x16, c, 1);
            } else if (c == 8) {
                res = tipAlgorithm(y0, x0, y16, x16, c, 2);
            }
        }

        //передаємо параметри об'єкта класу Pair
        changeAlgorithm(res.getX(), res.getY(), x16, y16);

        //перевіряємо, чи всі кнопки на місцях
        if (gameOver())
            winGame();
    }


    private Pair tipAlgorithm(int y0, int x0, int y16, int x16, int c, int row) {
        /*Алгоритм створений для встановлення кнопок на свої місця, працює наступним чином:
         * y0, х0 - координати невстановленої кнопки, у16, х16 - порожньої клітинки,
         * с - значення невстановленої кнопки, row - рядок невстановленої кнопки (за значенням, наприклад для '6' row = 1)
         * 1) знаходимо поточне місце невстановленої та пустої клітинки (відносно рядку -> відносно один одного)
         * 2) присвоюємо координати клітинки для переміщення в х та у, повертаємо їх через об'єкт Pair*/
        int x;
        int y;

        if (y0 > row) {
            if (y16 > y0) {
                if (x16 == x0) {
                    if (x0 == 0) {
                        x = 1;
                        y = y16;
                    } else if (x0 == 3) {
                        x = x0 - 1;
                        y = y16;
                    } else {
                        x = x0 + 1;
                        y = y16;
                    }
                } else {
                    if (x0 == 3) {
                        if (x16 < (c - 1) % 4) {
                            if (y0 - 1 > row || ((c == 4 && y0 - 1 == row))) {
                                x = x16;
                                y = y0 - 1;
                            } else {
                                x = x16 + 1;
                                y = y16;
                            }
                        } else {
                            x = x16;
                            y = y0 - 1;
                        }
                    } else {
                        if (x16 < x0) {
                            x = x0 + 1;
                            y = y16;
                        } else {
                            if (x0 < (c - 1) % 4) {
                                x = x16;
                                y = y0;
                            } else {
                                x = x16;
                                y = y0 - 1;
                            }
                        }
                    }
                }
            } else if (y0 == y16) {
                if (x0 < (c - 1) % 4) {
                    if (x16 < x0) {
                        if (y16 == 3) {
                            y = y16 - 1;
                        } else {
                            y = y16 + 1;
                        }
                        x = x16;
                    } else {
                        x = x0;
                        y = y0;
                    }
                } else if (x16 < (c - 1) % 4) {
                    if (c == 4 || c == 8) {
                        x = x16;
                        y = y16 - 1;
                    } else {
                        if (y16 < 3) {
                            x = x16;
                            y = y16 + 1;
                        } else {
                            x = 3;
                            y = y16;
                        }
                    }
                } else {
                    x = x16;
                    y = y16 - 1;
                }
            } else {
                if (y16 == row && x0 < (c - 1) % 4) {
                    if (c == 8) {
                        if (x16 != x0) {
                            x = x0;
                            y = y16;
                        } else {
                            x = x0;
                            y = y0;
                        }
                    } else {
                        y = y16 + 1;
                        x = x16;
                    }
                } else if (x0 < (c - 1) % 4) {
                    if (y0 == 3) {
                        if (x16 != x0) {
                            if (y16 < row) {
                                y = y0 - 1;
                                x = x16;
                            } else {
                                x = x0;
                                y = y16;
                            }
                        } else {
                            x = x0;
                            y = y0;
                        }
                    } else {
                        y = y0;
                        x = x16;
                    }

                } else {
                    if (x16 != x0) {
                        y = y16;
                        x = x0;
                    } else {
                        x = x0;
                        y = y0;
                    }
                }
            }
        } else {
            if (x16 >= x0) {
                if (c != 4 && c != 8) {
                    if (y16 != row) {
                        x = x0 - 1;
                        y = y16;
                    } else {
                        x = x16;
                        y = y0 + 1;
                    }
                } else {
                    if (y16 > row) {
                        if (x16 == x0) {
                            x = x0 + 1;
                            y = y16;
                        } else {
                            y = y16 - 1;
                            x = x16;
                        }
                    } else if (y16 == row) {
                        x = x0;
                        y = y0;
                    } else {
                        x = x16;
                        y = y0;
                    }
                }
            } else {
                if (c != 4 && c != 8) {
                    if (x16 < (c - 1) % 4) {
                        x = x16 + 1;
                        y = y16;
                    } else {
                        y = y0;
                        x = (y16 != y0) ? x16 : x0;
                    }
                } else {
                    if (y16 == row) {
                        x = x16;
                        y = y16 + 1;
                    } else {
                        x = x0 + 1;
                        y = y16;
                    }
                }
            }
        }

        return new Pair(x, y);
    }

    @FXML
    private void initialize() {
        //ініціалізуємо підказки на кнопки
        ArrayList<Tooltip> tt = new ArrayList<>();
        tt.add(new Tooltip("Натисніть для зупинки перемішування\n(щонайменше 10 переміщень)"));
        tt.add(new Tooltip("Натисніть для перемішування власноруч"));
        tt.add(new Tooltip("Натисніть для перемішування автоматично"));
        tt.add(new Tooltip("Натисніть для підказки"));
        tt.add(new Tooltip("Натисніть для перегляду рейтингу"));
        tt.add(new Tooltip("Натисніть для виходу з програми"));
        for (Tooltip t : tt) {
            t.setFont(Font.font("Calibri"));
            t.setStyle("-fx-font-size: 20");
        }
        Tooltip.install(ImageStop, tt.get(0));
        Tooltip.install(ImageResetManually, tt.get(1));
        Tooltip.install(ImageReset, tt.get(2));
        Tooltip.install(ImageAuto, tt.get(3));
        Tooltip.install(ImageRating, tt.get(4));
        Tooltip.install(ImageExit, tt.get(5));
    }
}