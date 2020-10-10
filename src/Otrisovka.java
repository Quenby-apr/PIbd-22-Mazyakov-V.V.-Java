import javax.swing.*;
import java.awt.*;


public class Otrisovka extends JPanel {
    private static Cruiser ship;
    private static MilShip milShip;

    public void setMilShip(MilShip milShip) {
        Otrisovka.milShip = milShip;
    }

    public Cruiser getShip() {
        return ship;
    }

    public void setShip(Cruiser ship) {
        Otrisovka.ship = ship;
    }

    public void paint(Graphics g) {
        if (ship != null) {
            ship.drawShip(g);
        }
        if (milShip != null) {
            milShip.drawTransport(g);
        }
    }
}
