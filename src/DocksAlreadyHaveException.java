public class DocksAlreadyHaveException extends Exception {
    public DocksAlreadyHaveException() {
        super("В доке уже есть такой корабль");
    }
}
