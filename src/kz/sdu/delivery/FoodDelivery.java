package kz.sdu.delivery;

import kz.sdu.account.delivery.ITrade;
import kz.sdu.information.Information;

import java.util.ArrayList;
import java.util.List;

public class FoodDelivery implements ITrade {
    List<String> categories = new ArrayList<>();
    List<List<String>> subcategories = new ArrayList<>();

    public FoodDelivery() {
        {
            String categories = Information.readFile("resources/categories.txt");
            while (!categories.isEmpty()) {
                if (categories.indexOf(',') != -1) {
                    this.categories.add(categories.substring(0, categories.indexOf(',')));
                    categories = categories.substring(categories.indexOf(',') + 2);
                } else {
                    this.categories.add(categories.trim());
                    categories = "";
                }
            }
        }
        {
            String subcategories = Information.readFile("resources/subcategories.txt");
            List<String> categories = new ArrayList<>();
            while (!subcategories.isEmpty()) {
                if (subcategories.indexOf(',') != -1) {
                    categories.add(subcategories.substring(0, subcategories.indexOf(',')));
                    subcategories = subcategories.substring(subcategories.indexOf(',') + 2);
                } else {
                    categories.add(subcategories.trim());
                    subcategories = "";
                }
            }
            List<String> temp = new ArrayList<>();
            for (String category :
                    categories) {
                if (!category.equals("next")) {
                    temp.add(category);
                } else {
                    this.subcategories.add(temp);
                }
            }
        }

    }

    @Override
    public void add() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void change() {

    }

    @Override
    public void total() {

    }
}
