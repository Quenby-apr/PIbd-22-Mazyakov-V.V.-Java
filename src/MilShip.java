import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MilShip extends Vehicle implements Comparable<MilShip>, Iterator<String>, Iterable<String> {
    protected int pictureWidth = 1010;
    protected int pictureHeight = 620;
    private int currentIndex;
    protected final String separator = ";";
    String[] strs = new String[3];

    public MilShip(int maxSpeed, float weight, Color mainColor) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        strs[0]=Integer.toString(maxSpeed);
        strs[1]=Float.toString(weight);
        int color=mainColor.getRGB();
        strs[2]=Integer.toString(color);
    }

    public MilShip(String info) {
        strs= info.split(String.valueOf(separator));
        if (strs.length == 3) {
            maxSpeed = Integer.parseInt(strs[0]);
            weight = Float.parseFloat(strs[1]);
            mainColor = new Color(Integer.parseInt(strs[2]));
        }
    }

    public String toString() {
        return maxSpeed + separator + weight + separator + mainColor.getRGB();
    }

    protected MilShip(int maxSpeed, float weight, Color mainColor, int shipWidth, int shipHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.shipWidth = shipWidth;
        this.shipHeight = shipHeight;
    }

    public void drawTransport(Graphics g) {
        g.setColor(mainColor);
        int[] body_x = {startPosX, startPosX - 25, startPosX + 230, startPosX + 205};
        int[] body_y = {startPosY, startPosY - 25, startPosY - 25, startPosY};
        g.fillPolygon(body_x, body_y, 4);

        g.setColor(mainColor);
        int[] rad_x = {startPosX + 44, startPosX + 44, startPosX + 65, startPosX + 75};
        int[] rad_y = {startPosY - 25, startPosY - 50, startPosY - 50, startPosY - 25};
        g.fillPolygon(rad_x, rad_y, 4);

        int[] deck_x = {startPosX + 80, startPosX + 80, startPosX + 85, startPosX + 90, startPosX + 95, startPosX + 95};
        int[] deck_y = {startPosY - 25, startPosY - 50, startPosY - 60, startPosY - 60, startPosY - 50, startPosY - 25};
        g.fillPolygon(deck_x, deck_y, 6);

        g.fillRect(startPosX + 53, startPosY - 60, 3, 10);
        g.fillOval(startPosX + 48, startPosY - 75, 15, 15);
        g.fillRect(startPosX + 80, startPosY - 75, 15, 15);
        g.fillRect(startPosX + 95, startPosY - 50, 25, 25);
        g.fillRect(startPosX + 107, startPosY - 56, 3, 6);
    }

    public void moveTransport(Direction direct) {
        int leftbody = 30;//выступ левой части
        int topbody = 76;//выступ основной части корабля
        float step = maxSpeed * 100 / weight;
        int pictureWidth = 1010;
        int pictureHeight = 620;
        switch (direct) {
            // вправо
            case Right:
                if (startPosX + step < pictureWidth - shipWidth) {
                    startPosX += step;
                }
                break;
            //влево
            case Left:
                if (startPosX - step > leftbody) {
                    startPosX -= step;
                }
                break;
            //вверх
            case Up:
                if (startPosY - step > topbody) {
                    startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (startPosY + step < pictureHeight - shipHeight) {
                    startPosY += step;
                }
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof MilShip)){
            return false;
        }
        MilShip ship = (MilShip) o;
        return equals(ship);
    }

    public boolean equals(MilShip other) {
        if (other == null) {
            return false;
        }
        if (!this.getClass().getSimpleName().equals(other.getClass().getSimpleName())) {
            return false;
        }
        if (maxSpeed != other.maxSpeed) {
            return false;
        }
        if (weight != other.weight) {
            return false;
        }
        if (mainColor != other.mainColor) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(MilShip truck) {
        if (maxSpeed != truck.maxSpeed) {
            return Integer.compare(maxSpeed, truck.maxSpeed);
        }
        if (weight != truck.weight) {
            return Float.compare(weight, truck.weight);
        }
        if (mainColor != truck.mainColor) {
            return Integer.compare(mainColor.getRGB(), truck.getMainColor().getRGB());
        }
        return 0;
    }

    @Override
    public Iterator<String> iterator() {
        currentIndex = -1;
        return this;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < strs.length-1;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        currentIndex++;
        return strs[currentIndex];
    }
}
