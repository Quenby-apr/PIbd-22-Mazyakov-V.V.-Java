import java.awt.*;

public abstract class Vehicle implements ITransport {
    protected int startPosX;
    protected int startPosY;
    protected int shipWidth;
    protected int shipHeight;

    public int maxSpeed;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public float weight;

    public float getWeight() {
        return weight;
    }

    public Color mainColor;

    public Color getMainColor() {
        return mainColor;
    }

    public void setPosition(int x, int y) {
        startPosX = x;
        startPosY = y;
    }

    public abstract void drawTransport(Graphics g);

    public abstract void moveTransport(Direction direction);

}
