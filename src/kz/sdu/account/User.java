package kz.sdu.account;

import kz.sdu.account.delivery.ITrade;

public class User implements ITrade {
    private String username;
    private final Long ID;

    public User(String username, Long ID) {
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
}
