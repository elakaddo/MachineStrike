package etu.ecole.ensi.machine_strike;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * Classe MachineStrikeController est un controleur qui gère le jeu Machine Strike
 * */
public class MachineStrikeController {
    @FXML private ImageView imgPlayerOne;
    @FXML private ImageView imgPlayerTwo;
    @FXML private ImageView backgroundGif;
    @FXML private GridPane gridMachineStrike;
    @FXML private ScrollPane scrollPanePieces;
    @FXML private Label playerLabel, stepsLabel,victoryPointsLabel;
    @FXML private Label winnerLabel;
    @FXML private HBox hboxWinner;

    private Player playerOne, playerTwo, actifPlayer;

    private int nbMoves = 0;
    private Piece selectedPiece = null;
    private int hoverOnRow = -1;
    private int hoverOnCol = -1;


    @FXML
    public void initialize() {
        initPlayers();
        fillGridMachineStrike();
        updatePlayerStatus();
    }

    /**
     * Méthode pour intialiser les joueurs
     * */
    private void initPlayers(){
        this.playerOne = new Player("Mohamed", 0);
        this.playerTwo = new Player("Nader", 0.9);
        this.actifPlayer = playerOne;
    }


    /**
     * Méthode qui affecte des pièce aux joueurs
     * */
    private void setPlayersPieces(){
        double playerOneColor = playerOne.getAdjutColorValue();
        double playerTwoColor = playerTwo.getAdjutColorValue();
        playerOne.addPiece(new Piece("Scrapper", "Gunner", playerOneColor, 0,0));
        playerOne.addPiece(new Piece("Burrower", "Melee", playerOneColor, 0,2));
        playerOne.addPiece(new Piece("Clawstrider", "Melee", playerOneColor, 0,4));
        playerOne.addPiece(new Piece("Widemaw", "Pull", playerOneColor, 0,6));

        playerTwo.addPiece(new Piece("Charger", "Dash", playerTwoColor,7,0));
        playerTwo.addPiece(new Piece("Burrower", "Melee", playerTwoColor, 7,2));
        playerTwo.addPiece(new Piece("Clawstrider", "Melee", playerTwoColor, 7,4));
        playerTwo.addPiece(new Piece("Widemaw", "Pull", playerTwoColor, 7,6));

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
                System.out.println("Piece is null TEST - OK");
                this.selectedPiece = getSelectedPiece(actifPlayer, GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                if (this.selectedPiece!=null)
                    changeTerrainsDominated(this.selectedPiece);
            }else {
                System.out.println("Piece is not null TEST - OK");
                if (!existPieceInCoordinates(GridPane.getRowIndex(n),GridPane.getColumnIndex(n))){
                    this.selectedPiece = deplacer(selectedPiece, GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                    System.out.println("Piece is moved - OK");
                }
                else{
                    if (this.actifPlayer.getPieceWhitCoordinates(GridPane.getRowIndex(n),GridPane.getColumnIndex(n))==null){
                        killPieceFrom(selectedPiece);
                    }else{
                        revertColorToTerrains();
                    }
                    this.selectedPiece = null;

                }
                if (nbMoves == 2)
                    switchPlayers();
            }
        }

    }

    /**
     * Méthode qui renvoir true si une piece existe dans les cordonnées données en arguments sinon false
     * @param rowIndex lignes
     * @param columnIndex colonnes
     * @return l'existence d'une piece dans les cordonnées row, col
     * */
    private boolean existPieceInCoordinates(Integer rowIndex, Integer columnIndex) {
        Boolean testPlayerOne = playerOne.getPieceWhitCoordinates(rowIndex, columnIndex) == null;
        Boolean testPlayerTwo = playerTwo.getPieceWhitCoordinates(rowIndex, columnIndex) == null;
        if (testPlayerOne && testPlayerTwo){
            return false;
        }else{
            return true;
        }
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
     * Méthode qui remplit la liste des pièces dans le ScrollPane
     * */
    private void fillScrollPane(){
        for(Piece p : this.playerTwo.getListePieces()){
            HBox hbox = new HBox();
            hbox.getChildren().add(p.getImagePiece(false));
            this.scrollPanePieces = new ScrollPane(hbox);

        }
    }

    /**
     * Méthode qui déplace une pièce vers une case
     * @author Mohamed AKADDAR
     * */
    private Piece deplacer(Piece piece, int destRow, int destCol) {
        if (calculerDistanceManhattan(piece.getRow(), piece.getCol(), destRow, destCol)<= piece.getMouvementRange()){
            if (piece.getRow() == destRow && piece.getCol() == destCol){
                revertColorToTerrains();
                return piece;
            }
            removePieceFromGrid(piece.getRow(), piece.getCol());
            piece.setPositionXY(destRow, destCol);
            addPieceOnGrid(piece);
            this.nbMoves++;
            updatePlayerStatus();
            revertColorToTerrains();
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
        this.gridMachineStrike.add(piece.getImagePiece(true), piece.getCol(), piece.getRow());
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
        updatePlayerStatus();
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
                    node.setOpacity(0.5);
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
                node.setOpacity(1);
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

    /**
     * Méto
     * */
    private void updatePlayerStatus() {
        playerLabel.setText(actifPlayer.getName());
        stepsLabel.setText(String.valueOf(2 - this.nbMoves));
        victoryPointsLabel.setText(String.valueOf(this.actifPlayer.getVictoryPoints()));
    }

    /**
     * Méthode qui tue une pièce d'un joueur par une piece donnée
     * @param piece la piece qui tue
     **/
    private void killPieceFrom(Piece piece){
        for (var node : gridMachineStrike.getChildren()) {
            if(GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null ){
                int row = GridPane.getRowIndex(node);
                int col = GridPane.getColumnIndex(node);
                if (calculerDistanceManhattan(piece.getRow(), piece.getCol(), row, col)<= piece.getMouvementRange())
                {
                    if (this.actifPlayer.getPieceWhitCoordinates(row, col) == null){
                        if (this.getOpponentPlayer().getPieceWhitCoordinates(row, col) != null){
                            Piece p = this.getOpponentPlayer().getPieceWhitCoordinates(row, col);
                            removePieceFromGrid(row, col);
                            this.getOpponentPlayer().removePiece(p);
                            this.actifPlayer.setVictoryPoints(actifPlayer.getVictoryPoints() + p.getRange());
                            this.nbMoves++;
                            updatePlayerStatus();
                            revertColorToTerrains();
                            if (gameOver() || (actifPlayer.getVictoryPoints() >= actifPlayer.getVictoryPointsToWin())){
                                setWinner();
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private Boolean gameOver(){
        if (getOpponentPlayer().getListePieces().isEmpty()){
            return true;
        }
        return false;
    }

    private void setWinner(){
        winnerLabel.setText(this.actifPlayer.getName());
        hboxWinner.setVisible(true);
        switchDisableGrid();
    }

    /**
     * Méthode qui renvoie l'adversaire
     * @return OpponentPlayer
     * */
    private Player getOpponentPlayer() {
        return (this.actifPlayer == playerOne) ? playerTwo : playerOne;
    }

    /**
     * Méthode pour quitter le jeu
     * @param actionEvent l'evenement du click sur le bouton Quitter
     * */
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Méthode pour re-commecer à jouer
     * @param actionEvent l'evenement du click sur le bouton Play Again
     * */
    public void playAgain(ActionEvent actionEvent) {
        cleanGrid();
        initialize();
        switchDisableGrid();
        updatePlayerStatus();
        hboxWinner.setVisible(false);

    }

    /**
     * Méthode qui supprime toutes les pièces quand un joueur gagne
     * */
    private void cleanGrid(){
        for (int i = 0; i<8; i++) {
            for (int j = 0; i<8; i++) {
                Piece p = playerOne.getPieceWhitCoordinates(i, j);
                if (p!=null)
                    removePieceFromGrid(p.getRow(), p.getCol());
                p = playerTwo.getPieceWhitCoordinates(i, j);
                if (p!=null)
                    removePieceFromGrid(p.getRow(), p.getCol());
            }
        }
    }

    /**
     * Méthode qui modifie l'activité et l'opacité de la grille
     * */
    private void switchDisableGrid()
    {
        if(!gridMachineStrike.isDisabled()){
            gridMachineStrike.setDisable(true);
            gridMachineStrike.setOpacity(0.5);
        }else{
            gridMachineStrike.setDisable(false);
            gridMachineStrike.setOpacity(1);
        }
    }
}