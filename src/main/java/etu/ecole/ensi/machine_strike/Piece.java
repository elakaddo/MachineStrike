package etu.ecole.ensi.machine_strike;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Piece {
    private ImageView image;
    private String namePiece;
    private String PieceType;
    private int power;
    private int range;
    private int mouvement_range;
    private int health;
    private int positionX;
    private int positionY;
    private boolean IsAlive = true;




    public Piece(String namePiece , String PieceType) {
        this.namePiece = namePiece;
        this.PieceType = PieceType;
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
        String pathToPieces = "Images/Pieces/"+ getPieceType()+"_type"+"/";
        String extensionImg = ".jpg";
        return pathToPieces + getPieceType()+"_"+ this.getNamePiece()+"_icon" + extensionImg;

    }

    private String getNamePiece() {
        return namePiece;
    }
    private String getPieceType() {
        return PieceType;
    }
    public ImageView getImagePlayer() {
        return this.image;
    }

    public void life_status(boolean isalive){
        this.IsAlive= isalive;
    }

    public boolean getIsAlive(){
        return this.IsAlive;
    }
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }
    public void setPositionXY(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

}
