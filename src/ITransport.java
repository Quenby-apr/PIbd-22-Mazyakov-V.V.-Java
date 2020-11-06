import java.awt.*;

public interface ITransport {
    public void setPosition(int x, int y, int pictureWidth, int pictureHeight);

    public void moveTransport(Direction up);

    public void drawTransport(Graphics g);
}
