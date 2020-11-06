import javax.swing.*;
import java.awt.*;

public class PaintDocks extends JPanel {
    private final Docks<ITransport, IAddition> docks;

    public PaintDocks(Docks<ITransport, IAddition> docks) {
        this.docks = docks;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (docks != null) {
            docks.draw(g2);
        }
    }
}
