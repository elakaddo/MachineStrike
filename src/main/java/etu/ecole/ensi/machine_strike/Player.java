package etu.ecole.ensi.machine_strike;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Player représente le joueur
 * @author Mohamed AKADDAR
 * */
public class Player {

    /** Le nom de joueur */
    private String name;

    /** La liste des pièces */
    private ArrayList<Piece> listePieces;

    /** Le nombre de pièces */
    private int nbPieces = 0;

    /** Le nombre maximal de pièces à posséder */
    private final int nbMaxPieces = 10;

    /** Le nombre maximal de pièces de même type */
    private final int nbMaxPiecesSameType = 4;

    /** Les points de victoires */
    private int victoryPoints = 0;

    /** Les points de victoires à atteindre */
    private final int victoryPointsToWin = 7;

    /** Une mesure pour changer la couleur de piece de tel joueur */
    private double adjutColorValue;

    /**
     * Constructeur
     * @param name le nom de joueur
     * */
    public Player(String name, double adjustColorValue){
        this.name = name;
        this.listePieces = new ArrayList<>();
        this.adjutColorValue = adjustColorValue;
    }

    /**
     * Méthode qui retourne la liste des pièces
     * @return la liste des pièce
     * */
    public List<Piece> getListePieces() {
        return listePieces;
    }

    /**
     * Méthode qui ajoute ue pièce à la liste des pièces de joueur
     * @param piece la pièce à ajouter au joueur
     * */
    public void addPiece(Piece piece){
        listePieces.add(piece);
        nbPieces++;
    }

    /**
     * Méthode qui ajoute ue pièce à la liste des pièces de joueur
     * @param piece la pièce à ajouter au joueur
     * */
    public void removePiece(Piece piece){
        listePieces.remove(piece);
        nbPieces--;
    }

    /**
     * Méthode qui récupère le nombre maximal de pieces
     * @return le nombre maximal de pieces
     * */
    public int getNbMaxPieces() {
        return nbMaxPieces;
    }

    /**
     * Méthode qui récupère les points de victoires
     * @return les points de victoires
     * */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * Méthode qui modifie les points de victoires
     * @param victoryPoints la nouvelle valeur des points de victoire
     * */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * Méthode qui récupère les points de victoires à atteindre pour gagner
     * @return les points de victoires à atteindre pour gagner
     * */
    public int getVictoryPointsToWin() {
        return victoryPointsToWin;
    }

    /**
     * Méthode qui récupère le nombre de pieces actuel
     * @return nombre de pièces possédés actuellement par le joueur
     * */
    public int getNbPieces() {
        return nbPieces;
    }

    /**
     * Méthode qui modifie le nombre de pieces actuel
     * @param nbPieces le nombre de pièces possédés actuellement par le joueur
     * */
    public void setNbPieces(int nbPieces) {
        this.nbPieces = nbPieces;
    }

    /**
     * Méthode qui récupère le nom de joueur
     * @return le nom de joueur
     * */
    public String getName() {
        return name;
    }

    /**
     * Méthode qui modifie le nom de joueur
     * @param name  le nom de joueur
     * */
    public void setName(String name) {
        this.name = name;
    }

    public double getAdjutColorValue() {
        return adjutColorValue;
    }

    public Piece getPieceWhitCoordinates(int row, int col){
        for(Piece p : this.getListePieces()){
            if (p.getRow() == row && p.getCol()==col){
                return p;
            }
        }
        return null;
    }
}
