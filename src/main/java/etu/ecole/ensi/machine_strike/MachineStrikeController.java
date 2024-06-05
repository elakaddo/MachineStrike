package etu.ecole.ensi.machine_strike;


import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

/**
 * Classe MachineStrikeController est un controleur qui gère le jeu Machine Strike
 * */
public class MachineStrikeController {

    @FXML
    GridPane gridMachineStrike;

    @FXML
    public void initialize() {
        fillGridMachineStrike();
    }

    private void fillGridMachineStrike() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile t = new Tile("Grassland");
                this.gridMachineStrike.add(t.getImageOfTiles(), i, j);
                if (i==j){
                    // placer un joueur
                    Piece p = new Piece("Charger", "Dash");
                    this.gridMachineStrike.add(p.getImagePlayer(), i, j);


                }
            }
        }

    }
}