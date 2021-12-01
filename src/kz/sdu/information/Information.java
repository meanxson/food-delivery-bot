package kz.sdu.information;

import java.io.*;
import java.util.Scanner;

public class Information {

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

    //FIXME fix name
    public static String editText (String text, String username) {
        String data = "";
        while (!text.isEmpty()) {
            if (text.contains("\\n")) {
                data += text.substring(0, text.indexOf("\\n")) + "\n";
                text = text.substring(text.indexOf("\\n") + 2);
            } else {
                data += text;
                text = "";
            }
        }
        data = data.replace("@username", "@" + username);
        return data;
    }
}
