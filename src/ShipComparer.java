import java.util.Comparator;

public class ShipComparer implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle x, Vehicle second) {
        if (!x.getClass().getSimpleName().equals(second.getClass().getSimpleName())) {
            return x.getClass().getSimpleName().compareTo(second.getClass().getSimpleName());
        }

        int result;
        switch (x.getClass().getSimpleName()) {
            case "MilShip" : {
                result = compareMilShip((MilShip) x, (MilShip) second);
                return result;
            }
            case "Cruiser" : {
                result = comparerCruiser((Cruiser) x, (Cruiser) second);
                return result;
            }
        }
        return 100;
    }

    private int compareMilShip(MilShip x, MilShip y) {
        if (x.getMaxSpeed() != y.getMaxSpeed()) {
            return Integer.compare(x.getMaxSpeed(), y.getMaxSpeed());
        }
        if (x.getWeight() != y.getWeight()) {
            return Float.compare(x.getWeight(), y.getWeight());
        }
        if (x.getMainColor() != y.getMainColor()) {
            return Integer.compare(x.getMainColor().getRGB(), y.getMainColor().getRGB());
        }
        return 0;
    }

    private int comparerCruiser(Cruiser x, Cruiser y) {
        int result = compareMilShip(x, y);
        if (result != 0) {
            return result;
        }

        if (x.getDopColor() != y.getDopColor()) {
            return Integer.compare(x.getDopColor().getRGB(), y.getDopColor().getRGB());
        }
        if (x.isMissileSystem() != y.isMissileSystem()) {
            return Boolean.compare(x.isMissileSystem(), y.isMissileSystem());
        }
        if (x.isAntiaircraftComplex() != y.isAntiaircraftComplex()) {
            return Boolean.compare(x.isAntiaircraftComplex(), y.isAntiaircraftComplex());
        }
        if (x.isControlSystem() != y.isControlSystem()) {
            return Boolean.compare(x.isControlSystem(), y.isControlSystem());
        }
        if (x.getAdditions() != null && y.getAdditions() != null
                && !(x.getAdditions().toString().equals(y.getAdditions().toString()))) {
            return x.getAdditions().toString().compareTo(y.getAdditions().toString());
        }
        if (x.getAdditions() == null && y.getAdditions() != null) {
            return 1;
        }
        if (x.getAdditions() != null && y.getAdditions() == null) {
            return -1;
        }
        return 0;
    }
}
