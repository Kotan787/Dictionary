import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyDictionaryFirst implements IMyDictionary {
    Map<String, String> dictionary = new HashMap<String, String>();
    String kR = "^[a-zA-Z]{4}$";
    String vR = "^\\d{5}$";

    //свойства для выдачи словаря
    public Map<String, String> GetDictionary() {
        return dictionary;
    }


    public void writeToFile(String path) {

    }

    public void readFromFile(String path) // прочитать словарь из файла
    {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader buf = new BufferedReader(fr);
            String line = buf.readLine();
            dictionaryPutString(line);
            while (line != null) {
                dictionaryPutString(line);
                line = buf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Файл успешно прочитан");
    }

    public void dictionaryPutString(String string) //метод добавляющий строку в словарь если она удовлетворяет условиям одного из языков
    {
        String key = "";
        String value = "";

        if (string.contains(" - ")) {
            key = string.substring(0, string.indexOf('-') - 1);
            value = string.substring(string.indexOf('-') + 2, string.length());

            if (key.matches(kR) && value.matches(vR)) {
                dictionary.putIfAbsent(key, value);
                System.out.println("Строка \"" + string + "\" добалвена в словарь");
            } else
                System.out.println("Строка \"" + string + "\" не удовлетворяет условиям словаря по ключу или значению");
        } else
            System.out.println("Строка \"" + string + "\" не удовлетворяет условиям словаря, неверная запись");
    }

    public void printDictionary() //вывод словаря
    {
        System.out.println("Словарь: ");
        System.out.println("---");
        dictionary.forEach((key, value) -> {
            System.out.println(key + " - " + value);
        });
        System.out.println("---");
    }

    public void deleteKey(String key) {
        if (dictionary.containsKey(key)) {
            dictionary.remove(key);
            System.out.println("Введенный ключ успешно удален!");
        } else {
            System.out.println("Введенный ключ не найден в выбранном словаре!");
        }
    }

    public String searchKey(String key) // поиск по ключу строки
    {
        if (dictionary.containsKey(key)) {
            return dictionary.get(key);
        } else {
            System.out.println("Введенный ключ не найден в выбранном словаре!");
            return "";
        }
    }

}
