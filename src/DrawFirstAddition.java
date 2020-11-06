import java.awt.*;

public class DrawFirstAddition implements IAddition {
    private CountCannon count;
    private Color col;

    public void setCount(int num) {
        count = CountCannon.getCount(num);
    }

    public DrawFirstAddition(int num, Color color) {
        setCount(num);
        col = color;
    }

    public void drawAdditional(Graphics g, int startPosX, int startPosY) {
        if (count != null) {
            switch (count) {
                case Six:
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(6.0f));
                    g.setColor(col);
                    g.drawLine(startPosX + 125 + 30, startPosY - 20, startPosX + 140 + 30, startPosY - 30);
                    g.drawLine(startPosX + 125 + 45, startPosY - 20, startPosX + 140 + 45, startPosY - 30);
                case Four:
                    g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(6.0f));
                    g.setColor(col);
                    g.drawLine(startPosX + 125, startPosY - 20, startPosX + 140, startPosY - 30);
                    g.drawLine(startPosX + 125 + 75, startPosY - 20, startPosX + 140 + 75, startPosY - 30);
                case Two:
                    g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(6.0f));
                    g.setColor(col);
                    g.drawLine(startPosX + 125 + 15, startPosY - 20, startPosX + 140 + 15, startPosY - 30);
                    g.drawLine(startPosX + 125 + 60, startPosY - 20, startPosX + 140 + 60, startPosY - 30);
            }
        }
    }
}