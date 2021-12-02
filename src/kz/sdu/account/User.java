package kz.sdu.account;

import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private String username;
    private final Long ID;
    private List<String> basket = new ArrayList<>();
    private int totalBasketCost = 0;

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

    public List<String> getBasket() {
        return basket;
    }

    /**
     * Example text:
     * Chicken Burger - 1000 tg.
     */
    public void addToBasket(String item) {
        this.basket.add(item);
        int start = item.indexOf("- ") + 2,
                end = item.indexOf(" tg");
        totalBasketCost += Integer.parseInt(item.substring(start, end));
    }

    public int getTotalBasketCost() {
        return totalBasketCost;
    }

    public void clearBasket() {
        basket = new ArrayList<>();
        totalBasketCost = 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", ID=" + ID +
                '}';
    }

}
