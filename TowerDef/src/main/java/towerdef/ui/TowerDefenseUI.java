package towerdef.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import towerdef.domain.Board;
import towerdef.domain.Tile;
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
    private int tileSize = 20;

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
            label.setText("You can't place a tower there");
        }
        update();
    }

    private void drawBoard() {
        Board board = towerDefense.getBoard();

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                drawTile(i, board, j);
            }
        }
    }

    private void drawTile(int i, Board board, int j) {
        if (null != board.getTile(i, j)) {
            switch (board.getTile(i, j)) {
                case SPAWN:
                    gc.setFill(Color.RED);
                    break;
                case BASE:
                    gc.setFill(Color.GREEN);
                    break;
                case ROAD:
                    gc.setFill(Color.BURLYWOOD);
                    break;
                case WALL:
                    break;
                case TOWER:
                    break;
                case EMPTY:
                    break;
                default:
                    break;
            }
        }
        if (board.getTile(i, j) != Tile.WALL && board.getTile(i, j) != Tile.TOWER) {
            gc.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
        }
//        if (board.getTile(i, j) == Tile.TOWER) {
//            gc.strokeOval(i, j, 10, 10);
//            gc.fillOval(i, j, 10, 10);
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();

        towerDefense = new TowerDefense();

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double deltaTime = (currentNanoTime - startNanoTime) / 1000000000.0;
                towerDefense.update(deltaTime);
                update();
            }
        }.start();

    }

    private void update() {
        hp.setText("HP: " + towerDefense.getHealth());
        gold.setText("Gold: " + towerDefense.getMoney());
        wave.setText("Wave: " + towerDefense.getWaveNumber() + "/10");
        drawBoard();
    }

}
