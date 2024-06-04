package etu.ecole.ensi.machine_strike;

import javafx.fxml.FXML;
import javafx.scene.control.Label;



/**
 * Classe MachineStrikeController est un controleur qui gère le jeu Machine Strike
 * */
public class MachineStrikeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}