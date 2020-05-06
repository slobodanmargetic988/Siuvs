package slobodan.siuvs2.shared;

public class SiuvsException extends Exception {

    public SiuvsException(String message) {
        super(message);
    }

    public SiuvsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SiuvsException(Throwable cause) {
        super(cause);
    }

}