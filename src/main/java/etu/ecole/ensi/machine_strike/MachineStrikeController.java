package etu.ecole.ensi.machine_strike;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * Classe MachineStrikeController est un controleur qui gère le jeu Machine Strike
 * */
public class MachineStrikeController {

    private Player playerOne = new Player("Mohamed", 0);
    private Player playerTwo = new Player("nader", 0.9);

    private Player actifPlayer = playerOne;
    private int nbMoves = 0;

    private Piece selectedPiece = null;

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
    public void clickGridPane(MouseEvent mouseEvent) {
        Node n = mouseEvent.getPickResult().getIntersectedNode();
        if(!(n instanceof GridPane)){
            System.out.println("Cliquez ici ");
            System.out.println("Cordinates : "+ GridPane.getRowIndex(n) + " : "+ GridPane.getColumnIndex(n));
            if(this.selectedPiece == null) {
                System.out.println("Clicked on Node");
                this.selectedPiece = getSelectedPiece(actifPlayer, GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
            }else {
                System.out.println("Else clicked on node");
                this.selectedPiece = deplacer(selectedPiece, GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                if (nbMoves == 2)
                    switchPlayers();
                }
            }
        }


    @FXML
    public void initialize() {
        fillGridMachineStrike();
    }

    private void fillGridMachineStrike() {
        /* REMPLIR LE TERRAIN DE LA GRILLE */
        fillTerrains();

        /* AJOUTER DES PIECES SUR LA GRILLE */
        addStartPiecesOfPlayers();
    }

    /**
     * Méthode qui remplit le plateau avec le terrain Grassland
     * @author Mohamed AKADDAR
     * */
    private void fillTerrains (){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                setTerrain("Hill", j, i);
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
    private Piece deplacer(Piece piece, int destRow, int destCol) {
        if (destRow == 0 && destCol == 7){
            return piece;
        }else{
            removePieceFromGrid(piece.getRow(), piece.getCol());
            piece.setPositionXY(destRow, destCol);
            addPieceOnGrid(piece);
            this.nbMoves++;
            return null;
        }
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
                    setTerrain("Hill", col, row);
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
    }

    public void playMoveFromTo(ActionEvent actionEvent) {
        System.out.println("Entred playMoveFromTo");
    }

    /**
     * Méthode qui ajoute une piece dans le plateau
     * @author nader
     * */
    public Piece getSelectedPiece(Player player, int row, int col){
        Piece p = player.getPieceWhitCoordinates(row, col);
        if (p != null) {
            return p;
        }
        return null;
    }

    public void switchPlayers(){
        if (this.actifPlayer == playerOne){
            this.actifPlayer = playerTwo;
        }else{
            this.actifPlayer = playerOne;
        }
        nbMoves=0;
    }
}