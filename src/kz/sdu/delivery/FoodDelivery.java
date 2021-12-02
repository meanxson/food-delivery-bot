package kz.sdu.delivery;

import kz.sdu.information.Information;

import java.util.ArrayList;
import java.util.List;

public class FoodDelivery implements ITrade {
    private final List<String> categories = new ArrayList<>();
    private final List<List<String>> subcategories = new ArrayList<>();

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
                    temp = new ArrayList<>();
                }
            }
        }
    }

    @Override
    public void add(String item) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void change(String item, String toItem) {

    }

    @Override
    public void total() {

    }

    public List<String> getCategories() {
        return categories;
    }

    public List<List<String>> getSubcategories() {
        return subcategories;
    }
}
