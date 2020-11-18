import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DocksCollection {
    private final Map<String, Docks<MilShip, IAddition>> docksStages;
    private final int frameWidth;
    private final int frameHeight;

    public DocksCollection(int frameWidth, int frameHeight) {
        docksStages = new HashMap<>();
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    public Set<String> keySet() {
        return docksStages.keySet();
    }

    public void addDocks(String name) {
        if (!docksStages.containsKey(name)) {
            docksStages.put(name, new Docks<>(frameWidth, frameHeight));
        }
    }

    public void deleteDocks(String name) {
        docksStages.remove(name);
    }

    public Docks<MilShip, IAddition> get(String name) {
        if (docksStages.containsKey(name)) {
            return docksStages.get(name);
        }
        return null;
    }

    public MilShip get(String name, int index) {
        if (docksStages.containsKey(name)) {
            return docksStages.get(name).get(index);
        }
        return null;
    }
}
