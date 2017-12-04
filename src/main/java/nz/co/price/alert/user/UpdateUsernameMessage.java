package nz.co.price.alert.user;

public final class UpdateUsernameMessage {
    private String username;

    public UpdateUsernameMessage(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
