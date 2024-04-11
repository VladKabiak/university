package Models;

public class Message {
    private final String message;
    private final String receiver;

    public Message(String message, String receiver) {
        this.message = message;
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public String getReceiver() {
        return receiver;
    }
}
