package etu.ecole.ensi.machine_strike;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.util.Objects;

public class Piece {
    private ImageView image;
    private String namePiece;
    private String PieceType;
    private int power;
    private int range;
    private int mouvement_range;
    private int health;

    private int row;
    private int col;

    private boolean IsAlive = true;
    private double colorAdjut;

    public Piece(String namePiece , String PieceType, double colorAdjut, int row, int col) {
        this.namePiece = namePiece;
        this.PieceType = PieceType;
        this.colorAdjut = colorAdjut;
        this.row = row;
        this.col = col;
    }

    /**
     * Méthode qui affecte une image à une case de la grille
     * */
    public void setImageToPiece(){
        Image i = new Image(Objects.requireNonNull(getClass().getResource(this.getPathToImg())).toString());
        this.image = new ImageView(i);
        this.image.setFitHeight(70);
        this.image.setFitWidth(70);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(this.colorAdjut); // changer la couleur de la pièce
        this.image.setEffect(colorAdjust);
        Circle clip = new Circle(35, 35, 35);
        this.image.setClip(clip);
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

    public ImageView getImagePiece() {

        setImageToPiece();
        this.image.setMouseTransparent(true);
        return this.image;
    }

    public void life_status(boolean isalive){
        this.IsAlive= isalive;
    }

    public boolean getIsAlive(){
        return this.IsAlive;
    }


    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }



    public void setPositionXY(int positionX, int positionY) {
        this.row = positionX;
        this.col = positionY;
    }
    public int getPositionRow() {
        return row;
    }
    public int getPositionCol( ) {
        return col;
    }

}
