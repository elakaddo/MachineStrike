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
        try{
            Image i = new Image(String.valueOf(getClass().getResource(this.getPathToImg())));
            this.image = new ImageView(i);
            this.image.setFitHeight(85);
            this.image.setFitWidth(85);
        }catch(Exception e){
            e.printStackTrace();
        }
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
