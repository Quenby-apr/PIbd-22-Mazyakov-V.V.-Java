public class DocksOverflowException extends Exception {
    public DocksOverflowException() {
        super("В доке нет свободных мест");
    }
}
