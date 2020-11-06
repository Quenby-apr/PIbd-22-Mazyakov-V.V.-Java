import java.awt.*;

public class MilShip extends Vehicle {
    protected int pictureWidth = 1010;
    protected int pictureHeight = 620;

    public MilShip(int maxSpeed, float weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
    }

    protected MilShip(int maxSpeed, float weight, Color mainColor, int shipWidth, int shipHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.shipWidth = shipWidth;
        this.shipHeight = shipHeight;
    }

    public void drawTransport(Graphics g) {
        g.setColor(mainColor);
        int[] body_x = {startPosX, startPosX - 25, startPosX + 230, startPosX + 205};
        int[] body_y = {startPosY, startPosY - 25, startPosY - 25, startPosY};
        g.fillPolygon(body_x, body_y, 4);

        g.setColor(mainColor);
        int[] rad_x = {startPosX + 44, startPosX + 44, startPosX + 65, startPosX + 75};
        int[] rad_y = {startPosY - 25, startPosY - 50, startPosY - 50, startPosY - 25};
        g.fillPolygon(rad_x, rad_y, 4);

        int[] deck_x = {startPosX + 80, startPosX + 80, startPosX + 85, startPosX + 90, startPosX + 95, startPosX + 95};
        int[] deck_y = {startPosY - 25, startPosY - 50, startPosY - 60, startPosY - 60, startPosY - 50, startPosY - 25};
        g.fillPolygon(deck_x, deck_y, 6);

        g.fillRect(startPosX + 53, startPosY - 60, 3, 10);
        g.fillOval(startPosX + 48, startPosY - 75, 15, 15);
        g.fillRect(startPosX + 80, startPosY - 75, 15, 15);
        g.fillRect(startPosX + 95, startPosY - 50, 25, 25);
        g.fillRect(startPosX + 107, startPosY - 56, 3, 6);
    }

    public void moveTransport(Direction direct) {
        int leftbody = 30;//выступ левой части
        int topbody = 76;//выступ основной части корабля
        float step = maxSpeed * 100 / weight;
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
}
