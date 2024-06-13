package etu.ecole.ensi.machine_strike;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * Classe MachineStrikeController est un controleur qui gère le jeu Machine Strike
 * */
public class MachineStrikeController {
    @FXML private ImageView imgPlayerOne;
    @FXML private ImageView imgPlayerTwo;
    @FXML private ImageView backgroundGif;
    @FXML private GridPane gridMachineStrike;
    private Player playerOne = new Player("Mohamed", 0);
    private Player playerTwo = new Player("nader", 0.9);
    private Player actifPlayer = playerOne;
    private int nbMoves = 0;
    private Piece selectedPiece = null;
    private int hoverOnRow = -1;
    private int hoverOnCol = -1;



    /* SET Pieces for everyone to test */
    private void setPlayersPieces(){
        playerOne.addPiece(new Piece("Scrapper", "Gunner", playerOne.getAdjutColorValue(), 0,0));
        playerTwo.addPiece(new Piece("Charger", "Dash", playerTwo.getAdjutColorValue(),7,3));
    }

    /**
     * @author AKADDAR Mohamed
     * Méthode qui gère le clique sur une case dans la grille
     * @param mouseEvent l'evemenemt de clique
     * */
    @FXML
    public void clickGridPane(MouseEvent mouseEvent) {
        Node n = mouseEvent.getPickResult().getIntersectedNode();
        if(!(n instanceof GridPane)){
            if(this.selectedPiece == null) {
                System.out.println("Clicked on Node");
                this.selectedPiece = getSelectedPiece(actifPlayer, GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                if (this.selectedPiece!=null)
                    changeTerrainsDominated(this.selectedPiece);
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

    /**
     * Méthode qui fait le remplissage de la grille
     * @author AKADDAR Mohamed
     * */
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
     * @author AKADDAR Mohamed
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
        if (calculerDistanceManhattan(piece.getRow(), piece.getCol(), destRow, destCol)<= piece.getMouvementRange()){
            if (piece.getRow() == destRow && piece.getCol() == destCol){
                revertColorToTerrains();
                System.out.println("No change");
                return piece;
            }
            removePieceFromGrid(piece.getRow(), piece.getCol());
            piece.setPositionXY(destRow, destCol);
            addPieceOnGrid(piece);
            this.nbMoves++;
            revertColorToTerrains();
            System.out.println("change position to : " + destRow + "," + destCol);
            return null;
        }
        return piece;

    }

    /**
     * Méthode qui supprime une pièce dans le plateau
     * @author Mohamed AKADDAR
     * */
    private void removePieceFromGrid(int row, int col){
        for (var node : gridMachineStrike.getChildren()) {
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null ) {
                if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
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

    /**
     * Méthode qui ajoute une piece dans le plateau
     * @author AKADDAR Mohamed
     * */
    public Piece getSelectedPiece(Player player, int row, int col){
        Piece p = player.getPieceWhitCoordinates(row, col);
        if (p != null) {
            return p;
        }
        return null;
    }


    /**
     * Méthode qui switche le tour des joueurs
     * @author AKADDAR Mohamed
     * */
    public void switchPlayers(){
        if (this.actifPlayer == playerOne){
            this.actifPlayer = playerTwo;
        }else{
            this.actifPlayer = playerOne;
        }
        nbMoves=0;
    }

    /**
     * Méthode qui change la couleur des terrains dominés par une pièce
     * @author AKADDAR Mohamed
     * @param dist la distance hamming
     * */
    public void changeTerrainsDominated(Piece p){
        for (var node : gridMachineStrike.getChildren()) {
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null ){
                int destRow = GridPane.getRowIndex(node);
                int destCol = GridPane.getColumnIndex(node);
                int distance = calculerDistanceManhattan(p.getRow(), p.getCol(), destRow, destCol);
                if (distance <= p.getMouvementRange()) {
                    node.setOpacity(0.6);
                }
            }
        }
    }

    /**
     * Méthode qui rend les terrain à leur cooleur d'origine
     * @author AKADDAR Mohamed
     * */
    private void revertColorToTerrains() {
        for (var node : gridMachineStrike.getChildren()) {
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null ){
                node.setOpacity(1 );
            }
        }
    }

    /**
     * Méthode qui calcule la distance Hamming
     * @author AKADDAR Mohamed
     * @param  row source
     * @param  col source
     * @param  rowDest de destination
     * @param  colDest de destination
     * @return la distance hamming
     * */
    private int calculerDistanceManhattan(int row, int col, int rowDest, int colDest) {
        return Math.abs(rowDest - row) + Math.abs(colDest - col);
    }

    /**
     * Méthode qui gére l'affichage des grands icons des pieces quand on met la souris sur elle.
     * @author Nader HADJOUDJ
     * @param mouseEvent evenement d'entrée de la souris.
     * */
    public void entredOnPane(MouseEvent mouseEvent) {
        Node n = mouseEvent.getPickResult().getIntersectedNode();
        if(!(n instanceof GridPane)){
            if(GridPane.getRowIndex(n) != null && GridPane.getColumnIndex(n)!= null) {
                if (GridPane.getRowIndex(n) != hoverOnRow || GridPane.getColumnIndex(n) != hoverOnCol) {
                    hoverOnRow = GridPane.getRowIndex(n);
                    hoverOnCol = GridPane.getColumnIndex(n);
                    Piece p = playerOne.getPieceWhitCoordinates( hoverOnRow, hoverOnCol);
                    if(p != null){
                        imgPlayerOne.setFitHeight(550);
                        imgPlayerOne.setFitWidth(300);
                        this.imgPlayerOne.setImage(new Image(getClass().getResourceAsStream(p.getPathToImg(false))));
                    }
                    else{
                        Piece p2= playerTwo.getPieceWhitCoordinates(hoverOnRow, hoverOnCol);
                        if(p2 != null) {
                            imgPlayerOne.setFitHeight(550);
                            imgPlayerOne.setFitWidth(300);
                            this.imgPlayerOne.setImage(new Image(getClass().getResourceAsStream(p2.getPathToImg(false))));

                        }
                        if(p2 == null) {
                            imgPlayerOne.setFitHeight(464);
                            imgPlayerOne.setFitWidth(273);
                        }
                    }

                }
            }
        }
    }
}