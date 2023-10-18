package cloud.storage.common.enums;

public enum Message {
    LOGIN_SUCCESS("You have successfully logged in!"),
    LOGIN_ERROR("Incorrect login or password."),
    LOGIN_ALREADY("You are already logged in."),
    REGISTER_SUCCESS("You registered!"),
    REGISTER_ERROR("A user with the same name already exists."),

    ;
    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
