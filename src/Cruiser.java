import java.awt.*;

public class Cruiser {
    private int startPosX;

    public int getStartPosX() {
        return startPosX;
    }

    /// Левая нижняя координата корабля
    private int startPosY;

    public int getStartPosY() {
        return startPosY;
    }

    private int shipWidth = 215;
    private int shipHeight = 35;
    /// Максимальная скорость
    public int MaxSpeed;

    public int getMaxSpeed() {
        return MaxSpeed;
    }

    /// Вес корабля
    public float Weight;

    public float getWeight() {
        return Weight;
    }

    /// Основной цвет
    public Color MainColor;

    public Color getMainColor() {
        return MainColor;
    }

    /// Дополнительный цвет
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
    DrawCannon dc;

    public Cruiser(int maxSpeed, float weight, Color mainColor, Color dopColor,
                   boolean missileSystem, boolean antiaircraftComplex, boolean controlSystem, int countCannon) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        MissileSystem = missileSystem;
        AntiaircraftComplex = antiaircraftComplex;
        ControlSystem = controlSystem;
        CountCannon = countCannon;
        dc = new DrawCannon(countCannon);
    }

    public void setPosition(int x, int y) {
        startPosX = x;
        startPosY = y;
    }

    public void moveTransport(Direction direct) {
        int leftbody = 30;//выступ левой части
        int topbody = 76;//выступ основной части корабля
        float step = MaxSpeed * 100 / Weight;
        int pictureWidth = 1010;
        int pictureHeight = 620;
        switch (direct) {
            // вправо
            case Right:
                if (startPosX + step < pictureWidth - shipWidth) {
                    startPosX += step;
                }
                break;
            //влево
            case Left:
                if (startPosX - step > leftbody) {
                    startPosX -= step;
                }
                break;
            //вверх
            case Up:
                if (startPosY - step > topbody) {
                    startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (startPosY + step < pictureHeight - shipHeight) {
                    startPosY += step;
                }
                break;
        }
    }

    public void drawShip(Graphics g) {
        dc.drawCannon(g, startPosX, startPosY);
        g.setColor(DopColor);
        int[] body_x = {startPosX, startPosX - 25, startPosX + 230, startPosX + 205};
        int[] body_y = {startPosY, startPosY - 25, startPosY - 25, startPosY};
        g.fillPolygon(body_x, body_y, 4);

        g.setColor(MainColor);
        int[] rad_x = {startPosX + 44, startPosX + 44, startPosX + 65, startPosX + 75};
        int[] rad_y = {startPosY - 25, startPosY - 50, startPosY - 50, startPosY - 25};
        g.fillPolygon(rad_x, rad_y, 4);

        int[] deck_x = {startPosX + 80, startPosX + 80, startPosX + 85, startPosX + 90, startPosX + 95, startPosX + 95};
        int[] deck_y = {startPosY - 25, startPosY - 50, startPosY - 60, startPosY - 60, startPosY - 50, startPosY - 25};
        g.fillPolygon(deck_x, deck_y, 6);


        if (AntiaircraftComplex) {
            g.setColor(MainColor);
            g.fillRect(startPosX - 20, startPosY - 30, 19, 5);
            g.fillRect(startPosX + 21, startPosY - 30, 19, 5);
        }
        if (ControlSystem) {
            g.setColor(MainColor);
            g.fillRect(startPosX, startPosY - 40, 20, 15);
            g.fillRect(startPosX + 6, startPosY - 50, 12, 10);
            g.fillOval(startPosX + 6, startPosY - 60, 12, 10);
        }

        g.setColor(MainColor);
        g.fillRect(startPosX + 53, startPosY - 60, 3, 10);
        g.fillOval(startPosX + 48, startPosY - 75, 15, 15);
        g.fillRect(startPosX + 80, startPosY - 75, 15, 15);
        g.fillRect(startPosX + 95, startPosY - 50, 25, 25);
        g.fillRect(startPosX + 107, startPosY - 56, 3, 6);

    }
}