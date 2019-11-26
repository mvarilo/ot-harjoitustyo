package towerdef.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import towerdef.domain.TowerDefense;

public class TowerDefenseUI implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Button waveButton;
    @FXML
    private Label hp;
    @FXML
    private Label gold;
    @FXML
    private Canvas canvas;
    @FXML
    private Label wave;
    private GraphicsContext gc;

    private TowerDefense towerDefense;

    @FXML
    private void handleButtonTower(ActionEvent event) {
        if (towerDefense.buyTower()) {
            System.out.println("You bought a tower, you have " + towerDefense.getMoney() + " gold left");
            label.setText("You bought a tower, you have " + towerDefense.getMoney() + " gold left");
        } else {
            System.out.println("You didn't have enough money to buy a tower");
            label.setText("You didn't have enough money to buy a tower");
        }
        update();
    }

    @FXML
    private void handleButtonWave(ActionEvent event) {
        if (towerDefense.isWaveOver()) {
            towerDefense.newWave();
        } else {
            label.setText("Wave is already in progress.");
        }
        update();
    }

    @FXML
    private void drawTower(MouseEvent event) {
        if (towerDefense.buyTower(event.getX(), event.getY())) {
            gc.strokeOval(event.getX(), event.getY(), 10, 10);
            gc.fillOval(event.getX(), event.getY(), 10, 10);
            label.setText("You bought a tower, you have " + towerDefense.getMoney() + " gold left");
        } else {
            label.setText("You didn't have enough money to buy a tower");
        }
        update();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();

        towerDefense = new TowerDefense();
        update();

    }

    private void update() {
        hp.setText("HP: " + towerDefense.getHealth());
        gold.setText("Gold: " + towerDefense.getMoney());
        wave.setText("Wave: " + towerDefense.getWaveNumber() + "/10");
    }

}
