import java.awt.*;


public class Cruiser extends MilShip implements Comparable<MilShip> {
    public Color dopColor;

    public Color getDopColor() {
        return dopColor;
    }

    public boolean missileSystem;

    public boolean isMissileSystem() {
        return missileSystem;
    }

    public boolean antiaircraftComplex;

    public boolean isAntiaircraftComplex() {
        return antiaircraftComplex;
    }

    public boolean controlSystem;

    public boolean isControlSystem() {
        return controlSystem;
    }

    private IAddition additions;

    private int countCannon;
    private int typeCannon;
    String[] strs = new String[8];

    public Cruiser(int maxSpeed, float weight, Color mainColor, Color dopColor,
                   boolean missileSystem, boolean antiaircraftComplex, boolean controlSystem, int countCannon, int typeCannon) {
        super(maxSpeed, weight, mainColor, 215, 35);
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.dopColor = dopColor;
        this.missileSystem = missileSystem;
        this.antiaircraftComplex = antiaircraftComplex;
        this.controlSystem = controlSystem;
        this.countCannon = countCannon;
        this.typeCannon = typeCannon;
        switch (typeCannon) {
            case 1:
                additions = new DrawFirstAddition(countCannon, dopColor);
                break;
            case 2:
                additions = new DrawSecondAddition(countCannon, dopColor);
                break;
            case 3:
                additions = new DrawThirdAddition(countCannon, dopColor);
                break;
        }
        strs[0]=Integer.toString(maxSpeed);
        strs[1]=Float.toString(weight);
        int color1 = mainColor.getRGB();
        int color2 = dopColor.getRGB();
        strs[2]=Integer.toString(color1);
        strs[3]=Integer.toString(color2);
        strs[4]=Boolean.toString(missileSystem);
        strs[5]=Boolean.toString(antiaircraftComplex);
        strs[6]=Boolean.toString(controlSystem);
        strs[7]=Integer.toString(countCannon);
        strs[8]=Integer.toString(typeCannon);
    }

    public Cruiser(String info) {
        super(info);
        String[] strs = info.split(separator);
        if (strs.length == 9) {
            maxSpeed = Integer.parseInt(strs[0]);
            weight = Float.parseFloat(strs[1]);
            mainColor = new Color(Integer.parseInt(strs[2]));
            dopColor = new Color(Integer.parseInt(strs[3]));
            missileSystem = Boolean.parseBoolean((strs[4]));
            antiaircraftComplex = Boolean.parseBoolean(strs[5]);
            controlSystem = Boolean.parseBoolean(strs[6]);
            countCannon = Integer.parseInt(strs[7]);
            if (strs[8].contains("0")) {
                additions = null;
            } else {
                typeCannon = Integer.parseInt(strs[8]);
                switch (typeCannon) {
                    case 1:
                        additions = new DrawFirstAddition(countCannon, dopColor);
                        break;
                    case 2:
                        additions = new DrawSecondAddition(countCannon, dopColor);
                        break;
                    case 3:
                        additions = new DrawThirdAddition(countCannon, dopColor);
                        break;
                }
            }
        }
    }

    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRGB() + separator + dopColor.getRGB() + separator + missileSystem + separator + antiaircraftComplex + separator + controlSystem + separator + countCannon + separator + typeCannon;
    }

    public void drawShip(Graphics g) {
        if (additions != null) {
            additions.drawAdditional(g, startPosX, startPosY);
        }
        super.drawTransport(g);
        if (antiaircraftComplex) {
            g.setColor(dopColor);
            g.fillRect(startPosX - 20, startPosY - 30, 19, 5);
            g.fillRect(startPosX + 21, startPosY - 30, 19, 5);
        }
        if (controlSystem) {
            g.setColor(mainColor);
            g.fillRect(startPosX, startPosY - 40, 20, 15);
            g.fillRect(startPosX + 6, startPosY - 50, 12, 10);
            g.fillOval(startPosX + 6, startPosY - 60, 12, 10);
        }
    }

    public void setDopColor(Color color) {
        dopColor = color;
    }

    public void setAdditions(IAddition additions) {
        this.additions = additions;
    }

    public IAddition getAdditions() {
        return additions;
    }

    public void setTypeCannon(int typeCannon) {
        this.typeCannon = typeCannon;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }
        if (!(o instanceof Cruiser)) {

            return false;
        }
        Cruiser ship = (Cruiser) o;
        return equals(ship);
    }

    public boolean equals(Cruiser other) {
        if (other == null) {
            return false;
        }
        if (!this.getClass().getSimpleName().equals(other.getClass().getSimpleName())) {
            return false;
        }
        if (maxSpeed != other.maxSpeed) {
            return false;
        }
        if (weight != other.weight) {
            return false;
        }
        if (mainColor != other.mainColor) {
            return false;
        }
        if (dopColor != other.dopColor) {
            return false;
        }
        if (missileSystem != other.missileSystem) {
            return false;
        }
        if (antiaircraftComplex != other.antiaircraftComplex) {
            return false;
        }
        if (controlSystem != other.controlSystem) {
            return false;
        }

        if (additions != null && other.additions != null && !(additions.toString().equals(other.additions.toString()))) {
            return false;
        }
        if (additions == null ^ other.additions == null) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(MilShip milShip) {
        Cruiser ship = (Cruiser) milShip;
        if (dopColor != ship.dopColor) {
            return Integer.compare(dopColor.getRGB(), ship.getDopColor().getRGB());
        }
        if (controlSystem != ship.controlSystem) {
            return Boolean.compare(controlSystem, ship.controlSystem);
        }
        if (antiaircraftComplex != ship.antiaircraftComplex) {
            return Boolean.compare(antiaircraftComplex, ship.antiaircraftComplex);
        }
        if (missileSystem != ship.missileSystem) {
            return Boolean.compare(missileSystem, ship.missileSystem);
        }
        if (additions == null && ship.additions != null) {
            return 1;
        }
        if (additions != null && ship.additions == null) {
            return -1;
        }
        return 0;
    }
}