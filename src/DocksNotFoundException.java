public class DocksNotFoundException extends Exception {
    public DocksNotFoundException(int index) {
        super("Не найден корабль по индексу " + index);
    }
}
