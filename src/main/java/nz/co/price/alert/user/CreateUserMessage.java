package nz.co.price.alert.user;

public class CreateUserMessage {
    private String username;

    public CreateUserMessage(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
