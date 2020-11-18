import javax.swing.*;
import java.awt.*;

public class PaintDocks extends JPanel {
    private DocksCollection docksCollection;
    private String definedDocks = null;

    public PaintDocks(DocksCollection docksCollection) {
        this.docksCollection = docksCollection;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (definedDocks != null) {
            if (docksCollection != null) {
                docksCollection.get(definedDocks).draw(g2);
            }
        }
    }

    public void setDefinedDocks(String definedDocks) {
        this.definedDocks = definedDocks;
    }
}

