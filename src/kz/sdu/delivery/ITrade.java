package kz.sdu.delivery;

public interface ITrade {
    void add(String item);
    void remove(int index);
    void change(String item, String toItem);
    void total();
}
