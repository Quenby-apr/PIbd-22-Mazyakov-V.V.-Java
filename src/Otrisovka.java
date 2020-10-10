import javax.swing.*;
import java.awt.*;


public class Otrisovka extends JPanel {
    private static Cruiser ship;

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
    }
}
