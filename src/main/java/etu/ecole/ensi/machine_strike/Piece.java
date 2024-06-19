package etu.ecole.ensi.machine_strike;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Piece {
    private ImageView image;
    private String namePiece;
    private String PieceType;

    private static final Map<String, PieceProperties> PIECE_PROPERTIES_MAP = new HashMap<>();

    static {
        PIECE_PROPERTIES_MAP.put("Charger", new PieceProperties(3, 0, 0, 0));
        PIECE_PROPERTIES_MAP.put("Thunderjaw", new PieceProperties(3, 0, 0, 0));
        PIECE_PROPERTIES_MAP.put("Scorcher", new PieceProperties(2, 0, 0, 0));
        PIECE_PROPERTIES_MAP.put("Scrapper", new PieceProperties(2, 3, 2, 5));
        PIECE_PROPERTIES_MAP.put("Longleg", new PieceProperties(2, 1, 2, 6));
        PIECE_PROPERTIES_MAP.put("Behemoth", new PieceProperties(2, 3, 2, 10));
        PIECE_PROPERTIES_MAP.put("Bellowback", new PieceProperties(2, 3, 2, 7));
        PIECE_PROPERTIES_MAP.put("Elemental_Clawstrider", new PieceProperties(2, 3, 2, 8));
        PIECE_PROPERTIES_MAP.put("Ravager", new PieceProperties(2, 2, 2, 9));
        PIECE_PROPERTIES_MAP.put("Redeye_Watcher", new PieceProperties(2, 2, 2, 5));
        PIECE_PROPERTIES_MAP.put("Rockbreaker", new PieceProperties(3, 3, 2, 9));
        PIECE_PROPERTIES_MAP.put("Burrower", new PieceProperties(2, 2, 1, 4));
        PIECE_PROPERTIES_MAP.put("Clawstrider", new PieceProperties(2, 3, 2, 8));
        PIECE_PROPERTIES_MAP.put("Leaplasher", new PieceProperties(4, 1, 1, 3));
        PIECE_PROPERTIES_MAP.put("Scrounger", new PieceProperties(3, 2, 1, 5));
        PIECE_PROPERTIES_MAP.put("Apex_Clawstrider", new PieceProperties(2, 3, 1, 8));
        PIECE_PROPERTIES_MAP.put("Clamberjaw", new PieceProperties(3, 3, 1, 8));
        PIECE_PROPERTIES_MAP.put("Fireclaw", new PieceProperties(3, 4, 2, 10));
        PIECE_PROPERTIES_MAP.put("Frostclaw", new PieceProperties(2, 4, 2, 10));
        PIECE_PROPERTIES_MAP.put("Rollerback", new PieceProperties(3, 3, 2, 5));
        PIECE_PROPERTIES_MAP.put("Shell-Walker", new PieceProperties(2, 2, 1, 7));
        PIECE_PROPERTIES_MAP.put("Slaughterspine", new PieceProperties(2, 4, 1, 15));
        PIECE_PROPERTIES_MAP.put("Spikesnout", new PieceProperties(2, 2, 1, 5));
        PIECE_PROPERTIES_MAP.put("Stalker", new PieceProperties(3, 4, 2, 5));
        PIECE_PROPERTIES_MAP.put("Tracker_Burrower", new PieceProperties(2, 2, 1, 4));
        PIECE_PROPERTIES_MAP.put("Widemaw", new PieceProperties(2, 3, 2, 7));
        PIECE_PROPERTIES_MAP.put("Shellsnapper", new PieceProperties(2, 3, 3, 10));
        PIECE_PROPERTIES_MAP.put("Snapmaw", new PieceProperties(2, 3, 3, 7));
        PIECE_PROPERTIES_MAP.put("Fanghorn", new PieceProperties(2, 2, 2, 5));
        PIECE_PROPERTIES_MAP.put("Grazer", new PieceProperties(2, 1, 1, 4));
        PIECE_PROPERTIES_MAP.put("Lancehorn", new PieceProperties(2, 2, 2, 5));
        PIECE_PROPERTIES_MAP.put("Bristleback", new PieceProperties(3, 2, 1, 4));
        PIECE_PROPERTIES_MAP.put("Plowhorn", new PieceProperties(2, 2, 1, 5));
        PIECE_PROPERTIES_MAP.put("Glinthawk", new PieceProperties(3, 2, 3, 5));
        PIECE_PROPERTIES_MAP.put("Skydrifter", new PieceProperties(2, 2, 3, 6));
        PIECE_PROPERTIES_MAP.put("Dreadwing", new PieceProperties(2, 3, 3, 9));
        PIECE_PROPERTIES_MAP.put("Sunwing", new PieceProperties(3, 2, 3, 7));
    }

    private int power;
    private int range;
    private int mouvementRange;
    private int health;

    private int row;
    private int col;

    private double colorAdjut;

    public Piece(String namePiece , String PieceType, double colorAdjut, int row, int col) {
        this.namePiece = namePiece;
        this.PieceType = PieceType;
        this.colorAdjut = colorAdjut;
        this.row = row;
        this.col = col;

        PieceProperties properties = PIECE_PROPERTIES_MAP.get(namePiece);
        if (properties != null) {
            this.mouvementRange = properties.getMouvementRange();
            this.power = properties.getPower();
            this.range = properties.getRange();
            this.health = properties.getHealth();
        } else {
            throw new IllegalArgumentException("Unknown piece name: " + namePiece);
        }
    }

    /**
     * Méthode qui affecte une image à une case de la grille
     * @author HADJOUDJ Nader
     * */
    public void setImageToPiece(Boolean isIcon){

        Image i = new Image(Objects.requireNonNull(getClass().getResource(this.getPathToImg(isIcon))).toString());
        this.image = new ImageView(i);
        if (isIcon) {
            this.image.setFitHeight(70);
            this.image.setFitWidth(70);
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(this.colorAdjut); // changer la couleur de la pièce
            this.image.setEffect(colorAdjust);
            Circle clip = new Circle(35, 35, 35);
            this.image.setClip(clip);
        }
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

    public ImageView getImagePiece(Boolean isIcon) {
        setImageToPiece(isIcon);
        this.image.setMouseTransparent(true);
        return this.image;
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
