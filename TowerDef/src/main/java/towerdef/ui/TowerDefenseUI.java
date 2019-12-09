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
import towerdef.domain.Enemy;
import towerdef.domain.Tile;
import towerdef.domain.TowerDefense;

/**
 * Piirtää pelin, kutsuu pelilogiikkaa ja hallinnoi käyttäjän syötöt
 */
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

    private AnimationTimer timer;

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
            timer.start();
            label.setText("");
        } else {
            label.setText("Wave is already in progress.");
        }
        update();
    }

    @FXML
    private void drawTower(MouseEvent event) {
        if (towerDefense.buyTower(event.getY(), event.getX())) {
            gc.setFill(Color.DARKVIOLET);
            gc.strokeOval(event.getX(), event.getY(), 10, 10);
            gc.fillOval(event.getX(), event.getY(), 10, 10);
            label.setText("You bought a tower, you have " + towerDefense.getMoney() + " gold left");
        } else {
            System.out.println("x" + event.getX());
            System.out.println("y" + event.getY());
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
        if ("SPAWN".equals(board.getTile(i, j).getType())) {
            gc.setFill(Color.RED);
        }
        if ("BASE".equals(board.getTile(i, j).getType())) {
            gc.setFill(Color.GREEN);
        }
        if ("ROAD".equals(board.getTile(i, j).getType())) {
            gc.setFill(Color.BURLYWOOD);
        }
        if ("WALL".equals(board.getTile(i, j).getType())) {

        }
        if (!"WALL".equals(board.getTile(i, j).getType()) && !"TOWER".equals(board.getTile(i, j).getType())) {
            gc.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();

        towerDefense = new TowerDefense();
        update();

        timer = new AnimationTimer() {

            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                double deltaTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                towerDefense.update(deltaTime);
                update();

                if (towerDefense.isWaveOver()) {
                    label.setText("Wave is over");
                    stop();
                }

                if (towerDefense.isGameOver()) {
                    label.setText("Game over!");
                    stop();
                }
            }
        };
    }

    private void update() {
        hp.setText("HP: " + towerDefense.getHealth());
        gold.setText("Gold: " + towerDefense.getMoney());
        wave.setText("Wave: " + towerDefense.getWaveNumber() + "/10");
        drawBoard();
        drawEnemies();
        drawCombat();
    }

    private void drawEnemies() {
        for (Enemy enemy : towerDefense.getEnemies()) {
            gc.setFill(Color.BLACK);
            gc.fillOval(
                    enemy.getPositionX(),
                    enemy.getPositionY(),
                    5,
                    5);
        }
    }

    private void drawCombat() {

    }

}
