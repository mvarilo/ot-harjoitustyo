package mvarilo.towerdef;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {

    @FXML
    private Label label;
    private Button button;
    private Label gold;

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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        towerDefense = new TowerDefense();
    }

}
