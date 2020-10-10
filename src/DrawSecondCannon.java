import java.awt.*;

public class DrawSecondCannon implements ICannon {
    private CountCannon count;

    public void setCount(int num) {
        count = CountCannon.getCount(num);
    }

    public DrawSecondCannon(int num) {
        setCount(num);
    }

    @Override
    public void drawAdditional(Graphics g, int startPosX, int startPosY) {
        switch (count) {
            case Six:
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3.0f));
                g.setColor(Color.GRAY);
                g.drawLine(startPosX + 125 + 13, startPosY - 35, startPosX + 125 + 26, startPosY - 45);
                g.drawLine(startPosX + 125 + 63, startPosY - 35, startPosX + 125 + 76, startPosY - 45);
                g.setColor(Color.RED);
                g.fillOval(startPosX + 125, startPosY - 40, 20, 20);
                g.fillOval(startPosX + 125 + 50, startPosY - 40, 20, 20);
                g.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(1.0f));
                g.drawOval(startPosX + 125, startPosY - 40, 20, 20);
                g.drawOval(startPosX + 125 + 50, startPosY - 40, 20, 20);
            case Four:
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3.0f));
                g.setColor(Color.GRAY);
                g.drawLine(startPosX + 125 + 28, startPosY - 35, startPosX + 125 + 41, startPosY - 45);
                g.drawLine(startPosX + 125 + 78, startPosY - 35, startPosX + 125 + 91, startPosY - 45);
                g.setColor(Color.RED);
                g.fillOval(startPosX + 125 + 15, startPosY - 40, 20, 20);
                g.fillOval(startPosX + 125 + 65, startPosY - 40, 20, 20);
                g.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(1.0f));
                g.drawOval(startPosX + 125 + 15, startPosY - 40, 20, 20);
                g.drawOval(startPosX + 125 + 65, startPosY - 40, 20, 20);
            case Two:
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3.0f));
                g.setColor(Color.GRAY);
                g.drawLine(startPosX + 125 + 43, startPosY - 35, startPosX + 125 + 56, startPosY - 45);
                g.drawLine(startPosX + 125 + 93, startPosY - 35, startPosX + 125 + 106, startPosY - 45);
                g.setColor(Color.RED);
                g.fillOval(startPosX + 125 + 30, startPosY - 40, 20, 20);
                g.fillOval(startPosX + 125 + 80, startPosY - 40, 20, 20);
                g.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(1.0f));
                g.drawOval(startPosX + 125 + 30, startPosY - 40, 20, 20);
                g.drawOval(startPosX + 125 + 80, startPosY - 40, 20, 20);
        }
    }
}
