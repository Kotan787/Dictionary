import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try {
            //какое-то изменение

            Scanner scan = new Scanner(System.in);
            boolean exit = false;

            Map<String, String>[] library = new Map[3];
            for (int i = 0; i < library.length; i++) {
                library[i] = new HashMap<>();
            }

            MyDictionary mD = new MyDictionary("^[a-zA-Z]{4}$","^\\d{5}$");



            String path = "";

            while (!exit) {
                System.out.println(
                        "Выберете пункт меню:" + "\n"
                                + "1. Чтение из файла" + "\n"
                                + "2. Просмотр словаря" + "\n"
                                + "3. Удаление записи по ключу в словаре" + "\n"
                                + "4. Поиск записи по ключу в словаре" + "\n"
                                + "5. Добавление записи по ключу удовлетворяющей условиям в словарь" + "\n"
                                + "6. Выход" + "\n");
                int menuinput = scan.nextInt();

                switch (menuinput) {
                    case 1:
                        path = "";
                        System.out.println("Введите путь к файлу: ");
                        path = scan.next();
                        System.out.println("Введите номер словаря котоырй хотим заполнить: ");

                        mD.readfromfile(path);
                        mD.printDictionary();

                        break;
                    case 2:
                        System.out.println("Словарь: ");
                        mD.printDictionary();
                        break;
                    case 3:
                        mD.printDictionary();
                        System.out.println("Введите ключ по которому необходимо удалить запись в словаре: ");
                        String kdelete = scan.next();

//                        if (library[languageNum].containsKey(kdelete)) {
//                            library[languageNum].remove(kdelete);
//                            System.out.println("Введенный ключ успешно удален!");
//                        } else {
//                            System.out.println("Введенный ключ не найден в выбранном словаре!");
//                        }

                        break;
                    case 4:
//                        languageNum = 0;
//                        System.out.println("Введите номер словаря в котоыром хотим найти запись: ");
//                        languageNum = scan.nextInt();
//                        mD.printDictionary(library[languageNum]);
//                        System.out.println("Введите ключ по которому необходимо найти запись в словаре: ");
//                        String ksearch = scan.next();
//
//                        if (library[languageNum].containsKey(ksearch)) {
//                            System.out.println("Значение по запрошенному ключу: " + library[languageNum].get(ksearch));
//
//                        } else {
//                            System.out.println("Введенный ключ не найден в выбранном словаре!");
//                        }
//                        break;
                    case 5:
//                        languageNum = 0;
//                        System.out.println("Введите номер словаря в котоырй хотим добавить запись: ");
//                        languageNum = scan.nextInt();
//                        mD.printDictionary(library[languageNum]);
//                        System.out.println("Введите ключ: ");
//                        String keyAdd = scan.next();
//                        System.out.println("Введите значение: ");
//                        String valueAdd = scan.next();
//                        keyAdd += " - " + valueAdd;
//                        mD.dictionaryPutString(library[languageNum], keyAdd, languageNum);

                        break;
                    case 6:
                        exit = true;
                        break;

                }
            }
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
    }
}