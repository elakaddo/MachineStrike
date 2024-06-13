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
    private int mouvementRange;
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
        if(namePiece.equals("Charger")){
            mouvementRange= 3;
        }
        else if (namePiece.equals("Thunderjaw")){
            mouvementRange=3;
        }
        else if (namePiece.equals("Scorcher")){
            mouvementRange=2;
        }
        else if (namePiece.equals("Scrapper")){
            mouvementRange=2;
            power= 3;
            range= 2;
            health=5;
        }
        else if (namePiece.equals("Longleg")){
            mouvementRange=2;
            power= 1;
            range= 2;
            health=6;
        }
        else if (namePiece.equals("Behemoth")){
            mouvementRange=2;
            power= 3;
            range= 2;
            health=10;
        }
        else if (namePiece.equals("Bellowback")){
            mouvementRange=2;
            power= 3;
            range= 2;
            health=7;
        }
        else if (namePiece.equals("Elemental_Clawstrider")){
            mouvementRange=2;
            power= 3;
            range= 2;
            health=8;
        }
        else if (namePiece.equals("Ravager")){
            mouvementRange=2;
            power= 2;
            range= 2;
            health=9;
        }
        else if (namePiece.equals("Redeye_Watcher")){
            mouvementRange=2;
            power= 2;
            range= 2;
            health=5;
        }
        else if (namePiece.equals("Rockbreaker")){
            mouvementRange=3;
            power= 3;
            range= 2;
            health=9;
        }
        else if (namePiece.equals("Burrower")){
            mouvementRange= 2;
            power= 2;
            range= 1;
            health=4;
        }
        else if (namePiece.equals("Clawstrider")){
            mouvementRange= 2;
            power= 3;
            range= 2;
            health=8;
        }
        else if (namePiece.equals("Leaplasher")){
            mouvementRange= 4;
            power= 1;
            range= 1;
            health=3;
        }
        else if (namePiece.equals("Scrounger")){
            mouvementRange= 3;
            power= 2;
            range= 1;
            health=5;
        }
        else if (namePiece.equals("Apex_Clawstrider")){
            mouvementRange= 2;
            power= 3;
            range= 1;
            health=8;
        }
        else if (namePiece.equals("Clamberjaw")){
            mouvementRange= 3;
            power= 3;
            range= 1;
            health=8;
        }
        else if (namePiece.equals("Fireclaw")){
            mouvementRange= 3;
            power= 4;
            range= 2;
            health=10;
        }
        else if (namePiece.equals("Frostclaw")){
            mouvementRange= 2;
            power= 4;
            range= 2;
            health=10;
        }
        else if (namePiece.equals("Rollerback")){
            mouvementRange= 3;
            power= 3;
            range= 2;
            health=5;
        }
        else if (namePiece.equals("Shell-Walker")){
            mouvementRange= 2;
            power= 2;
            range= 1;
            health=7;
        }
        else if (namePiece.equals("Slaughterspine")){
            mouvementRange= 2;
            power= 4;
            range= 1;
            health=15;
        }
        else if (namePiece.equals("Spikesnout")){
            mouvementRange= 2;
            power= 2;
            range= 1;
            health=5;
        }
        else if (namePiece.equals("Stalker")){
            mouvementRange= 3;
            power= 4;
            range= 2;
            health=5;
        }
        else if (namePiece.equals("Tracker_Burrower")){
            mouvementRange= 2;
            power= 2;
            range= 1;
            health=4;
        }
        else if (namePiece.equals("Widemaw")){
            mouvementRange= 2;
            power= 3;
            range= 2;
            health=7;
        }
        else if (namePiece.equals("Shellsnapper")){
            mouvementRange= 2;
            power= 3;
            range= 3;
            health=10;
        }
        else if (namePiece.equals("Snapmaw")){
            mouvementRange= 2;
            power= 3;
            range= 3;
            health=7;
        }
        else if (namePiece.equals("Fanghorn")){
            mouvementRange= 2;
            power= 2;
            range= 2;
            health=5;
        }
        else if (namePiece.equals("Grazer")){
            mouvementRange= 2;
            power= 1;
            range= 1;
            health=4;
        }
        else if (namePiece.equals("Lancehorn")){
            mouvementRange= 2;
            power= 2;
            range= 2;
            health=5;
        }
        else if (namePiece.equals("Bristleback")){
            mouvementRange= 3;
            power= 2;
            range= 1;
            health= 4;
        }
        else if (namePiece.equals("Plowhorn")){
            mouvementRange= 2;
            power= 2;
            range= 1;
            health= 5;
        }
        else if (namePiece.equals("Glinthawk")){
            mouvementRange= 3;
            power= 2;
            range= 3;
            health= 5;
        }
        else if (namePiece.equals("Skydrifter")){
            mouvementRange= 2;
            power= 2;
            range= 3;
            health= 6;
        }
        else if (namePiece.equals("Dreadwing")){
            mouvementRange= 2;
            power= 3;
            range= 3;
            health= 9;
        }
        else if (namePiece.equals("Sunwing")){
            mouvementRange= 3;
            power= 2;
            range= 3;
            health= 7;
        }






    }

    /**
     * Méthode qui affecte une image à une case de la grille
     * */
    public void setImageToPiece(){
        Image i = new Image(Objects.requireNonNull(getClass().getResource(this.getPathToImg(true))).toString());
        this.image = new ImageView(i);
        this.image.setFitHeight(70);
        this.image.setFitWidth(70);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(this.colorAdjut); // changer la couleur de la pièce
        this.image.setEffect(colorAdjust);
        Circle clip = new Circle(35, 35, 35);
        this.image.setClip(clip);
    }

    public String getPathToImg(Boolean isIcon) {
        String pathToPieces = "Images/Pieces/"+ getPieceType()+"_type"+"/";
        String extensionImg = ".jpg";
        if (isIcon)
            return pathToPieces + getPieceType()+"_"+ this.getNamePiece()+"_icon" + extensionImg;
        return pathToPieces + getPieceType()+"_"+ this.getNamePiece()+ extensionImg;

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
    public int getMouvementRange(){
        return mouvementRange;
    }
    public int getHealth(){
        return health;
    }

    public int getPower() {
        return power;
    }
    public int getRange() {
        return range;
    }
}
