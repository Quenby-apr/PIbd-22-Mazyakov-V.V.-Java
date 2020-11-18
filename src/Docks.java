import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Docks<T extends ITransport, IAddition> {
    private final List<T> places;
    //private final Object[] places;
    private final int countPlaces;
    private final int pictureWidth;
    private final int pictureHeight;
    private final int placeSizeWidth = 280;
    private final int placeSizeHeight = 80;

    public Docks(int picWidth, int picHeight) {
        this.pictureWidth = picWidth;
        this.pictureHeight = picHeight;
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        countPlaces = width*height;
        places = new ArrayList<>();

        //places = new Object[width * height];
    }

    public boolean add(T ship) {
        if (places.size() < countPlaces)
        {
            places.add(ship);
            return true;
        }
        return false;
    }

    public T delete(int index) {
        if (index >= 0 && index < countPlaces && places.get(index) != null)
        {
            T ship = places.get(index);
            places.remove(index);
            return ship;
        }
        return null;
    }


    public void draw(Graphics g) {
        drawMarking(g);
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i) != null) {
                T place = (T) places.get(i);
                place.setPosition(((i / 7) % 3) * placeSizeWidth + placeSizeWidth / 10, (i % 7) * placeSizeHeight + placeSizeHeight - 2, pictureWidth, pictureHeight);
                System.out.println("рисует");
                place.drawTransport(g);
                if (place instanceof Cruiser) {
                    ((Cruiser) place).drawShip(g);
                }
            } else {
                return;
            }
        }
    }

    private void drawMarking(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color LightSkyBlue = new Color(135, 206, 250);
        g2.setColor(LightSkyBlue);
        g2.fillRect(0, 0, 950, 1000);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3.0f));
        for (int i = 0; i < pictureWidth / placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / placeSizeHeight + 1; ++j) {//линия рамзетки места
                g2.drawLine(i * placeSizeWidth, j * placeSizeHeight, (int) (i *
                        placeSizeWidth + placeSizeWidth / 1.3), j * placeSizeHeight);
            }
            g2.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth,
                    (pictureHeight / placeSizeHeight) * placeSizeHeight);
        }
    }
    public T get(int index) {
        if (index >= 0 && index < places.size()) {
            return places.get(index);
        }
        return null;
    }
}
