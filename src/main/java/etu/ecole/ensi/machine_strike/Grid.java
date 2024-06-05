package etu.ecole.ensi.machine_strike;

/**
 * Classe Grid permet de gérer la grille de jeu Machine Strike
 * */
public class Grid {
    /** Nombre de lignes de la grille */
    private int rows;

    /** Nombre de colonnes de la grille */
    private int cols;

    /**
     *  Méthode qui mofidie le nombre de lignes de la grille
     * @param rows Nombre de lignes de la grille
     * */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     *  Méthode qui mofidie le nombre de colonnes de la grille
     * @param cols Nombre de colonnes de la grille
     * */
    public void setCols(int cols) {
        this.rows = rows;
    }
}
