package org.test;

import org.test.entity.Client;
import org.test.entity.Setting;
import org.test.entity.dto.JsonDTO;
import org.test.interfaces.JsonReader;
import org.test.interfaces.MenuBuilder;
import org.test.interfaces.MenuBuilderImpl;
import org.test.service.JsonReaderImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);

        JsonReader jsonReader = new JsonReaderImpl();
        System.out.println("Введите путь к настройкам в формате .json");
        input = scanner.nextLine();
        JsonDTO jsonDTO = jsonReader.getJsonData(input);
        MenuBuilder menuBuilder = new MenuBuilderImpl(jsonDTO);




        while (true){
            input = scanner.nextLine();
            System.out.println(menuBuilder.tapButton(input));
        }

    }
}