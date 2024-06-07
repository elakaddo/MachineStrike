package etu.ecole.ensi.machine_strike;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Iterator;
import java.util.Objects;

/**
 * Classe MachineStrikeController est un controleur qui gère le jeu Machine Strike
 * */
public class MachineStrikeController {

    private Player playerOne = new Player("Mohamed", 0);
    private Player playerTwo = new Player("nader", 0.9);

    public int selectedRow=-1;
    public int selectedCol=-1;

    /* SET Pieces for everyone to test */
    private void setPlayersPieces(){
        playerOne.addPiece(new Piece("Charger", "Dash", playerOne.getAdjutColorValue(), 0,0));
        playerTwo.addPiece(new Piece("Charger", "Dash", playerTwo.getAdjutColorValue(),7,3));
    }

    @FXML
    GridPane gridMachineStrike;
    @FXML
    Button move;



    @FXML
    public void initialize() {
        fillGridMachineStrike();
    }

    private void fillGridMachineStrike() {
        /* REMPLIR LE TERRAIN DE LA GRILLE */
        fillTerrains();

        /* AJOUTER DES PIECES SUR LA GRILLE */
        addStartPiecesOfPlayers();

        this.gridMachineStrike.getChildren().forEach(item -> {
            item.setOnMouseClicked(e -> {
                System.out.println("Clicked on Node");
                setSelectedRowCol(GridPane.getRowIndex(item), GridPane.getColumnIndex(item));
            });
        });
    }

    /**
     * Méthode qui remplit le plateau avec le terrain Grassland
     * @author Mohamed AKADDAR
     * */
    private void fillTerrains (){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                setTerrain("Grassland", j, i);
            }
        }
    }

    /**
     * Méthode qui ajoute les pièces de départ dans le plateau
     * @author Mohamed AKADDAR
     * */
    private void addStartPiecesOfPlayers(){
        setPlayersPieces();
        for(Piece p : this.playerOne.getListePieces()){
            addPieceOnGrid(p);
        }

        for(Piece p : this.playerTwo.getListePieces()){
            addPieceOnGrid(p);
        }

    }

    /**
     * Méthode qui met un terrain sur le plateau
     * */
    public void setTerrain(String name, int col, int row){
        Tile t = new Tile(name);
        gridMachineStrike.add(t.getImageOfTiles(), col, row);
    }


    /**
     * Méthode qui déplace une pièce vers une case
     * @author Mohamed AKADDAR
     * */
    private void deplacer(Piece piece, int destRow, int destCol) {

    }

    /**
     * Méthode qui supprime une pièce dans le plateau
     * @author Mohamed AKADDAR
     * */
    private void removePieceFromGrid(int row, int col){
        for (var node : gridMachineStrike.getChildren()) {
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null ) {
                if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                    // STORE PIECE BEFORE DELETE
                    Piece p = playerOne.getPieceWhitCoordinates(row, col);
                    this.gridMachineStrike.getChildren().remove(node);
                    setTerrain("Grassland", col, row);
                    break;
                }
            }
        }
    }
    /**
     * Méthode qui ajoute une piece dans le plateau
     * @author Mohamed AKADDAR
     * */
    private void addPieceOnGrid(Piece piece){
        this.gridMachineStrike.add(piece.getImagePiece(), piece.getCol(), piece.getRow());
    }

    private void setSelectedRowCol(Integer rowIndex, Integer columnIndex) {
        this.selectedRow = rowIndex;
        this.selectedCol = columnIndex;
        removePieceFromGrid(selectedRow, selectedCol);
        removePieceFromGrid(selectedRow, selectedCol);
    }

    public void playMoveFromTo(ActionEvent actionEvent) {
        System.out.println("Entred playMoveFromTo");
    }
}