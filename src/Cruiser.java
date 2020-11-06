import java.awt.*;

public class Cruiser extends MilShip {
    public Color DopColor;

    public Color getDopColor() {
        return DopColor;
    }

    public boolean MissileSystem;

    public boolean isMissileSystem() {
        return MissileSystem;
    }

    public boolean AntiaircraftComplex;

    public boolean isAntiaircraftComplex() {
        return AntiaircraftComplex;
    }

    public boolean ControlSystem;

    public boolean isControlSystem() {
        return ControlSystem;
    }

    private int CountCannon;
    private int TypeCannon;
    DrawFirstAddition dfc;
    DrawSecondAddition dsc;
    DrawThirdAddition dtc;

    public Cruiser(int maxSpeed, float weight, Color mainColor, Color dopColor,
                   boolean missileSystem, boolean antiaircraftComplex, boolean controlSystem, int countCannon, int typeCannon) {
        super(maxSpeed, weight, mainColor, 215, 35);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        DopColor = dopColor;
        MissileSystem = missileSystem;
        AntiaircraftComplex = antiaircraftComplex;
        ControlSystem = controlSystem;
        CountCannon = countCannon;
        TypeCannon = typeCannon;
        switch (typeCannon) {
            case 1:
                dfc = new DrawFirstAddition(countCannon, dopColor);
                break;
            case 2:
                dsc = new DrawSecondAddition(countCannon, dopColor);
                break;
            case 3:
                dtc = new DrawThirdAddition(countCannon, dopColor);
                break;
        }

    }

    public void drawShip(Graphics g) {
        switch (TypeCannon) {
            case 1:
                dfc.drawAdditional(g, startPosX, startPosY);
                break;
            case 2:
                dsc.drawAdditional(g, startPosX, startPosY);
                break;
            case 3:
                dtc.drawAdditional(g, startPosX, startPosY);
                break;
        }

        super.drawTransport(g);
        if (AntiaircraftComplex) {
            g.setColor(DopColor);
            g.fillRect(startPosX - 20, startPosY - 30, 19, 5);
            g.fillRect(startPosX + 21, startPosY - 30, 19, 5);
        }
        if (ControlSystem) {
            g.setColor(mainColor);
            g.fillRect(startPosX, startPosY - 40, 20, 15);
            g.fillRect(startPosX + 6, startPosY - 50, 12, 10);
            g.fillOval(startPosX + 6, startPosY - 60, 12, 10);
        }
    }
}