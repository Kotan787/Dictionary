import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<String, String> readfromfile(String path,Integer language) // прочитать словарь из файла
    {
        Map<String,String> result = new HashMap<>();
        try
        {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader buf = new BufferedReader(fr);
            String line = buf.readLine();
            dictionaryPutString(result, line,language);
            while (line !=null)
            {
                dictionaryPutString(result, line, language);
                line = buf.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Файл успешно прочитан");
        return result;
    }
    public static void dictionaryPutString(Map map, String string, Integer language) //метод добавляющий строку в словарь если она удовлетворяет условиям одного из языков
    {
        String key ="";
        String value ="";

        if (string.contains(" - ")) {
            key = string.substring(0,string.indexOf('-')-1);
            value = string.substring(string.indexOf('-')+2, string.length());
            switch (language) {

                case 1:
                   if( key.matches("^[a-zA-Z]{4}$") && value.matches("^\\d{3}$"))
                   {
                       map.putIfAbsent(key, value);
                       System.out.println("Строка \"" + string + "\" добалвена в словарь");
                   }
                   else
                       System.out.println("Строка \"" + string + "\" не удовлетворяет условиям словаря");
                    break;

                case 2:
                    if( key.matches("^\\d{3}$") && value.matches("^[a-zA-Z]{4}$"))
                    {
                        map.putIfAbsent(key,value);
                        System.out.println("Строка \"" + string + "\" добалвена в словарь");
                    }
                    else
                        System.out.println("Строка \"" + string + "\" не удовлетворяет условиям словаря");
                    break;

                default:
                    System.out.println("Язык не найден");
                break;
            }
        }
        else
            System.out.println("Строка \"" + string + "\" не удовлетворяет условиям словаря");
    }
    public static  void printDictionary(Map dictionary) //вывод словаря
    {
        System.out.println("---");
        dictionary.forEach((key,value) -> {System.out.println(key + " - " + value);});
        System.out.println("---");
    }

    public static void main(String[] args) {

        try {


            Scanner scan = new Scanner(System.in);
            boolean exit = false;

            Map<String, String>[] library = new Map[3];
            for (int i = 0; i < library.length; i++) {
                library[i] = new HashMap<>();
            }

            MyDictionary mD = new MyDictionary("^[a-zA-Z]{4}$","^\\d{5}$");



            String path = "";
            int languageNum = 0;

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
                        languageNum = 0;
                        System.out.println("Введите путь к файлу: ");
                        path = scan.next();
                        System.out.println("Введите номер словаря котоырй хотим заполнить: ");
                        languageNum = scan.nextInt();

                        library[languageNum] = readfromfile(path, languageNum);
                        printDictionary(library[languageNum]);

                        break;
                    case 2:
                        languageNum = 0;
                        System.out.println("Введите номер словаря котоырй хотим просмотреть: ");
                        languageNum = scan.nextInt();

                        System.out.println("Словарь " + languageNum + ":");
                        printDictionary(library[languageNum]);

                        break;
                    case 3:
                        languageNum = 0;
                        System.out.println("Введите номер словаря в котоыром хотим удалить запись: ");
                        languageNum = scan.nextInt();
                        printDictionary(library[languageNum]);
                        System.out.println("Введите ключ по которому необходимо удалить запись в словаре: ");
                        String kdelete = scan.next();

                        if (library[languageNum].containsKey(kdelete)) {
                            library[languageNum].remove(kdelete);
                            System.out.println("Введенный ключ успешно удален!");
                        } else {
                            System.out.println("Введенный ключ не найден в выбранном словаре!");
                        }

                        break;
                    case 4:
                        languageNum = 0;
                        System.out.println("Введите номер словаря в котоыром хотим найти запись: ");
                        languageNum = scan.nextInt();
                        printDictionary(library[languageNum]);
                        System.out.println("Введите ключ по которому необходимо найти запись в словаре: ");
                        String ksearch = scan.next();

                        if (library[languageNum].containsKey(ksearch)) {
                            System.out.println("Значение по запрошенному ключу: " + library[languageNum].get(ksearch));

                        } else {
                            System.out.println("Введенный ключ не найден в выбранном словаре!");
                        }
                        break;
                    case 5:
                        languageNum = 0;
                        System.out.println("Введите номер словаря в котоырй хотим добавить запись: ");
                        languageNum = scan.nextInt();
                        printDictionary(library[languageNum]);
                        System.out.println("Введите ключ: ");
                        String keyAdd = scan.next();
                        System.out.println("Введите значение: ");
                        String valueAdd = scan.next();
                        keyAdd += " - " + valueAdd;
                        dictionaryPutString(library[languageNum], keyAdd, languageNum);

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