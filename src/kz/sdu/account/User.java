package kz.sdu.account;

public class User extends Person  {
    private String username;
    private final Long ID;

    public User(String username, Long ID) {
        super("", "");
        this.username = username;
        this.ID = ID;
    }

    public User(String username, Long ID, String name, String surname) {
        super(name, surname);
        this.username = username;
        this.ID = ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Long getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", ID=" + ID +
                '}';
    }

}
