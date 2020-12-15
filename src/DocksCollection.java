import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DocksCollection {
    private final Map<String, Docks<MilShip, IAddition>> docksStages;
    private final int frameWidth;
    private final int frameHeight;
    private final String separator = ":";

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

    public boolean saveData(String filename) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            fileWriter.write("DocksCollection\n");
            for (Map.Entry<String, Docks<MilShip, IAddition>> level : docksStages.entrySet()) {
                fileWriter.write("Dock" + separator + level.getKey() + "\n");
                MilShip ship;
                for (int i = 0; (ship = level.getValue().get(i)) != null; i++) {
                    if (ship.getClass().getSimpleName().equals("MilShip")) {
                        fileWriter.write("MilitaryShip" + separator);
                    } else if (ship.getClass().getSimpleName().equals("Cruiser")) {
                        fileWriter.write("Cruiser" + separator);
                    }
                    fileWriter.write(ship.toString() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadData(String filename) throws Exception {
        if (!(new File(filename).exists())) {
            throw new FileNotFoundException("Файл " + filename + " не найден!");
        }
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            if (scanner.nextLine().contains("DocksCollection")) {
                docksStages.clear();
            } else {
                throw new IllegalArgumentException("Файл " + filename + " содержит неверные значения");
            }
            MilShip ship = null;
            String key = "";
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("Dock:")) {
                    key = line.split(separator)[1];
                    docksStages.put(key, new Docks<>(frameWidth, frameHeight));
                } else if (line.contains(separator)) {
                    if (line.contains("MilitaryShip")) {
                        ship = new MilShip(line.split(separator)[1]);
                    } else if (line.contains("Cruiser")) {
                        ship = new Cruiser(line.split(separator)[1]);
                    }
                    if (!(docksStages.get(key).add(ship))) {
                        throw new IndexOutOfBoundsException();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean saveDocks(String filename, String key) {
        if (docksStages.containsKey(key)) {
            if (!filename.contains(".txt")) {
                filename += ".txt";
            }
            try (FileWriter fileWriter = new FileWriter(filename, false)) {
                fileWriter.write("Docks" + separator + key + "\n");
                MilShip ship;
                for (int i = 0; (ship = docksStages.get(key).get(i)) != null; i++) {
                    if (ship.getClass().getSimpleName().equals("MilShip")) {
                        fileWriter.write("MilitaryShip" + separator);
                    } else if (ship.getClass().getSimpleName().equals("Cruiser")) {
                        fileWriter.write("Cruiser" + separator);
                    }
                    fileWriter.write(ship.toString() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean loadDocks(String filename) {
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String key;
            String line;
            line = scanner.nextLine();
            if (line.contains("Docks:")) {
                key = line.split(separator)[1];
                if (docksStages.containsKey(key)) {
                    docksStages.get(key).clearList();
                } else {
                    docksStages.put(key, new Docks<>(frameWidth, frameHeight));
                }
            } else {
                throw new IllegalArgumentException("Файл " + filename + " содержит неверные значения");
            }
            MilShip ship = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(separator)) {
                    if (line.contains("MilitaryShip")) {
                        ship = new MilShip(line.split(separator)[1]);
                    } else if (line.contains("Cruiser")) {
                        ship = new Cruiser(line.split(separator)[1]);
                    }
                    if (!(docksStages.get(key).add(ship))) {
                        throw new IndexOutOfBoundsException();
                    }
                }
            }
        } catch (IOException | DocksOverflowException e) {
            e.printStackTrace();
        }
        return true;
    }
}
