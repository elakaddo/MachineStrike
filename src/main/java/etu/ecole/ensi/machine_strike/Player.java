package etu.ecole.ensi.machine_strike;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Player {
    private ImageView image;
    private String namePlayer;
    private String playerType;

    public Player(String namePlayer , String playerType) {
        this.namePlayer = namePlayer;
        this.playerType = playerType;
        setImageToPlayer();
    }

    /**
     * Méthode qui affecte une image à une case de la grille
     * */
    public void setImageToPlayer(){
        Image i = new Image(Objects.requireNonNull(getClass().getResource(this.getPathToImg())).toString());
        this.image = new ImageView(i);
        this.image.setFitWidth(40);
        this.image.setFitHeight(40);
    }

    private String getPathToImg() {
        String pathToPieces = "Images/Pieces/"+getPlayerType()+"_type"+"/";
        String extensionImg = ".jpg";
        return pathToPieces + getPlayerType()+"_"+ this.getNamePlayer()+"_icon" + extensionImg;

    }

    private String getNamePlayer() {
        return namePlayer;
    }
    private String getPlayerType() {
        return playerType;
    }
    public ImageView getImagePlayer() {
        return this.image;
    }

}
