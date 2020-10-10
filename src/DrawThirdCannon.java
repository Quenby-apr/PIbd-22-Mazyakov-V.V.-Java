import java.awt.*;

public class DrawThirdCannon implements ICannon {
    private CountCannon count;

    public void setCount(int num) {
        count = CountCannon.getCount(num);
    }

    public DrawThirdCannon(int num) {
        setCount(num);
    }

    @Override
    public void drawAdditional(Graphics g, int startPosX, int startPosY) {
        switch (count) {
            case Six:
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(6.0f));
                g.setColor(Color.BLACK);
                g.drawLine(startPosX + 135 + 20, startPosY - 30, startPosX + 135 + 10 + 20, startPosY - 35);
                g.drawLine(startPosX + 125 + 70, startPosY - 30, startPosX + 125 + 10 + 70, startPosY - 35);
                g.fillOval(startPosX + 150 + 10, startPosY - 45, 10, 10);
                g.fillOval(startPosX + 190 + 10, startPosY - 45, 10, 10);
            case Two:
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(6.0f));
                g.setColor(Color.BLACK);
                g.drawLine(startPosX + 125 + 50, startPosY - 30, startPosX + 125 + 10 + +50, startPosY - 35);
                g.fillOval(startPosX + 170 + 10, startPosY - 45, 10, 10);
                break;
            case Four:
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(6.0f));
                g.setColor(Color.BLACK);
                g.drawLine(startPosX + 135 + 30, startPosY - 30, startPosX + 135 + 30 + 10, startPosY - 35);
                g.drawLine(startPosX + 125 + 60, startPosY - 30, startPosX + 125 + 60 + 10, startPosY - 35);
                g.fillOval(startPosX + 160 + 10, startPosY - 45, 10, 10);
                g.fillOval(startPosX + 180 + 10, startPosY - 45, 10, 10);
        }
        g.setColor(Color.RED);
        int[] body_x = {startPosX + 135, startPosX + 145, startPosX + 205, startPosX + 215};
        int[] body_y = {startPosY - 25, startPosY - 35, startPosY - 35, startPosY - 25};
        g.fillPolygon(body_x, body_y, 4);
        switch (count) {
            case Six:
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(6.0f));
                g.setColor(Color.BLACK);
                g.drawLine(startPosX + 135 + 20, startPosY - 30, startPosX + 135 + 20, startPosY - 35);
                g.drawLine(startPosX + 125 + 70, startPosY - 30, startPosX + 125 + 70, startPosY - 35);
                g.fillOval(startPosX + 150, startPosY - 45, 10, 10);
                g.fillOval(startPosX + 190, startPosY - 45, 10, 10);
            case Two:
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(6.0f));
                g.setColor(Color.BLACK);
                g.drawLine(startPosX + 125 + 50, startPosY - 30, startPosX + 125 + 50, startPosY - 35);
                g.fillOval(startPosX + 170, startPosY - 45, 10, 10);
                break;
            case Four:
                g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(6.0f));
                g.setColor(Color.BLACK);
                g.drawLine(startPosX + 135 + 30, startPosY - 30, startPosX + 135 + 30, startPosY - 35);
                g.drawLine(startPosX + 125 + 60, startPosY - 30, startPosX + 125 + 60, startPosY - 35);
                g.fillOval(startPosX + 160, startPosY - 45, 10, 10);
                g.fillOval(startPosX + 180, startPosY - 45, 10, 10);
        }
    }


}
