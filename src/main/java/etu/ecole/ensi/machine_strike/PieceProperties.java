package etu.ecole.ensi.machine_strike;

/**
 * Classe qui contient toutes les propriétes de la piece
 * */
public class PieceProperties
{
    private final int mouvementRange;
    private final int power;
    private final int range;
    private final int health;

    public PieceProperties(int mouvementRange, int power, int range, int health) {
        this.mouvementRange = mouvementRange;
        this.power = power;
        this.range = range;
        this.health = health;
    }

    public int getMouvementRange() {
        return mouvementRange;
    }

    public int getPower() {
        return power;
    }

    public int getRange() {
        return range;
    }

    public int getHealth() {
        return health;
    }
}
