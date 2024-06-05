package etu.ecole.ensi.machine_strike;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Classe Tile représente une case dans la grille GridPane
 * */
public class Tile {

    private ImageView image;
    private final String nameImage;

    /**
     * Constructeur avec initialisation de nom de la case
     * */
    public Tile(String nameCase) {
        this.nameImage = nameCase;
    }

    /**
     * Méthode qui récupère le nom de la case
     * @return nom de la case
     * */
    public String getNameImage() {
        return nameImage;
    }

    /**
     * Méthode qui récupère le chemin vers la case
     * @return nom de la case
     * */
    public String getPathToImg() {
        String pathToTiles = "Images/Terrains/Tiles/";
        String extentionImg = ".jpg";
        return pathToTiles + this.getNameImage() + extentionImg;
    }

    /**
     * Méthode qui affecte une image à une case de la grille
     * */
    public void setImageToTile(){
        Image i = new Image(Objects.requireNonNull(getClass().getResource(this.getPathToImg())).toString());
        this.image = new ImageView(i);
    }

    /**
     * Méthode qui récupère le chemin vers la case
     * @return nom de la case
     * */
    public ImageView getImageOfTiles(){
        setImageToTile();
        return this.image;
    }


}
