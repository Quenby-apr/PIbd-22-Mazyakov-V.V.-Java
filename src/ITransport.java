import java.awt.*;

public interface ITransport {
    public void setPosition(int x, int y);
    public void moveTransport(Direction direct);
    public void drawTransport(Graphics g);
}
