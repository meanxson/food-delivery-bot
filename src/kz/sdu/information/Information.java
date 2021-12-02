package kz.sdu.information;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Information {

    public static String readFile(String path) {
        String data = "";
        try {
            Scanner reader = new Scanner(new File(path));
            while (reader.hasNextLine()) {
                data = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    public static String getStartInform(String username) {
        String data = "Error";
        try {
            File file = new File("resources/start.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                data = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return editText(data, username);
    }

    public static String editText (String text, String username) {
        StringBuilder data = new StringBuilder();
        while (!text.isEmpty()) {
            if (text.contains("\\n")) {
                data.append(text, 0, text.indexOf("\\n")).append("\n");
                text = text.substring(text.indexOf("\\n") + 2);
            } else {
                data.append(text);
                text = "";
            }
        }
        data = new StringBuilder(data.toString().replace("@username", "@" + username));
        return data.toString();
    }

    public static List<String> getList(@NotNull String s, @NotNull String divisor) {
        List<String> data = new ArrayList<>();
        while (!s.isEmpty()) {
            if (s.contains(divisor)) {
                data.add(s.substring(0, s.indexOf(divisor)));
                s = s.substring(s.indexOf(divisor) + divisor.length());
            } else {
                data.add(s);
                s = "";
            }
        }
        return data;
    }
}
