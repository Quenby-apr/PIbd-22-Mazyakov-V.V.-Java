import javax.swing.*;
import java.awt.*;


public class PaintShip extends JPanel {
    private static ITransport ship;

    public Cruiser getShip() {
        return (Cruiser) ship;
    }

    public MilShip getMilShip() {
        return (MilShip) ship;
    }

    public void setShip(ITransport ship) {
        PaintShip.ship = ship;
    }

    public void paint(Graphics g) {
        if (ship != null) {
            ship.drawTransport(g);
        }
    }
}
