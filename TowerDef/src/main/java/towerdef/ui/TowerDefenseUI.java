package towerdef.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import towerdef.dao.Dao;
import towerdef.domain.Board;
import towerdef.domain.Enemy;
import towerdef.domain.Tile;
import towerdef.domain.Shot;
import towerdef.domain.Tower;
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

    private Dao dao;
    private TowerDefense towerDefense;
    private int tileSize = 20;

    private AnimationTimer timer;

    @FXML
    private void handleButtonTower(ActionEvent event) {
        if (!towerDefense.isGameOver()) {
            if (towerDefense.buyTower()) {
                System.out.println("You bought a tower, you have " + towerDefense.getMoney() + " gold left");
                label.setText("You bought a tower, you have " + towerDefense.getMoney() + " gold left");
            } else {
                System.out.println("You did not have enough money to buy a tower");
                label.setText("You did not have enough money to buy a tower");
            }
            update();
        }
    }

    @FXML
    private void handleButtonWave(ActionEvent event) {
        if (!towerDefense.isGameOver()) {
            if (towerDefense.isWaveOver()) {
                towerDefense.newWave();
                timer.start();
                label.setText("");
            } else {
                label.setText("Wave is already in progress.");
            }
            update();
        }
    }

    @FXML
    private void drawTower(MouseEvent event) {
        if (!towerDefense.isGameOver()) {

            try {
                if (towerDefense.buyTower(event.getY() - 5, event.getX() - 5)) {
                    label.setText("You bought a tower, you have " + towerDefense.getMoney() + " gold left");
                } else if (towerDefense.getMoney() < 5) {
                    label.setText("You did not have enough money for a tower");
                } else {
                    label.setText("Can't place tower there");
                }
                update();
            } catch (Exception e) {
                label.setText("Can't place tower there");
            }
        }
    }

    private void drawBoard() {
        Board board = towerDefense.getBoard();

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                drawTile(i, board, j);
            }
        }

        drawTowers();
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

        dao = new Dao("./autosave.sav");
        towerDefense = loadGame();
        update();

        timer = new AnimationTimer() {

            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                double deltaTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;

                towerDefense.update(deltaTime);
                update(deltaTime);

                if (towerDefense.isWaveOver()) {
                    label.setText("Wave is over");
                    saveGame();
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

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawBoard();
        drawCombat();
        drawEnemies();
    }

    private void update(double deltaTime) {
        hp.setText("HP: " + towerDefense.getHealth());
        gold.setText("Gold: " + towerDefense.getMoney());
        wave.setText("Wave: " + towerDefense.getWaveNumber() + "/99");

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawBoard();
        drawEnemies();
        drawCombat(deltaTime);
    }

    private void drawEnemies() {
        for (Enemy enemy : towerDefense.getEnemies()) {
            gc.setFill(Color.BLACK);
            gc.setStroke(Color.BLACK);
            gc.strokeOval(enemy.getPositionX(), enemy.getPositionY(), 5, 5);
            gc.fillOval(
                    enemy.getPositionX(),
                    enemy.getPositionY(),
                    5,
                    5);
        }
    }

    private void drawCombat() {
        for (Tower tower : towerDefense.getTowers()) {
            ArrayList<Shot> shots = tower.drawShots();
            for (Iterator<Shot> it = shots.iterator(); it.hasNext();) {
                if (towerDefense.isWaveOver()) {
                    Shot next = it.next();
                    it.remove();
                } else {
                    Shot shot = it.next();
                    gc.setLineWidth(1);
                    gc.setStroke(Color.RED);
                    gc.strokeLine(shot.getStartY() + 5, shot.getStartX() + 5,
                            shot.getEndX(), shot.getEndY());
                    if (tower.timeoutShot()) {
                        it.remove();
                    }
                }
            }
        }
    }

    private void drawCombat(double deltaTime) {
        for (Tower tower : towerDefense.getTowers()) {
            ArrayList<Shot> shots = tower.drawShots();
            for (Iterator<Shot> it = shots.iterator(); it.hasNext();) {
                if (towerDefense.isWaveOver()) {
                    Shot next = it.next();
                    it.remove();
                } else {
                    Shot shot = it.next();
                    tower.update(deltaTime);
                    gc.setLineWidth(1);
                    gc.setStroke(Color.RED);
                    gc.strokeLine(shot.getStartY() + 5, shot.getStartX() + 5,
                            shot.getEndX(), shot.getEndY());
                    if (tower.timeoutShot()) {
                        it.remove();
                    }
                }
            }
        }
    }

    private void drawTowers() {
        for (Tower tower : towerDefense.getTowers()) {
            gc.setFill(Color.DARKVIOLET);
            gc.setStroke(Color.DARKVIOLET);
            gc.strokeOval(tower.getPositionY(), tower.getPositionX(), 10, 10);
            gc.fillOval(tower.getPositionY(), tower.getPositionX(), 10, 10);
        }
    }

    private TowerDefense loadGame() {
        try {
            towerDefense = dao.load();
            return towerDefense;
        } catch (IOException | ClassNotFoundException ex) {
            return new TowerDefense();
        }
    }

    private void saveGame() {
        try {
            dao.save(towerDefense);
        } catch (IOException ex) {
            System.out.println("Save failed");
        }
    }

}
