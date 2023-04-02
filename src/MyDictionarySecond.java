import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyDictionarySecond extends MyDictionaryAny implements IMyDictionary {
    Map<String, String> dictionary = new HashMap<String, String>();
    String kR = "^\\d{5}$";
    String vR = "^[a-zA-Z]{4}$";

    public Map<String, String> swapValueToKey() // поменять местами ключи и значения
    {
        Map<String, String> result = new HashMap<>();

        for (Map.Entry entry : dictionary.entrySet()) {
            result.put(entry.getValue().toString(), entry.getKey().toString());
        }
        return result;
    }

    public String mapToString(Map<String, String> map) {
        String stringDictionary = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringDictionary += entry.getKey() + " - " + entry.getValue() + "\n";
        }
        return stringDictionary;
    }

    public String toString() {
        String stringDictionary = "";
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            stringDictionary += entry.getKey() + " - " + entry.getValue() + "\n";
        }
        return stringDictionary;
    }

    public boolean writeToFile(String path) {
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(mapToString(swapValueToKey()));
            writer.flush();
            System.out.println("Файл  успешно записан: " + path);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean readFromFile(String path) // прочитать словарь из файла
    {
        File file = new File(path);
        try (FileReader fr = new FileReader(file); BufferedReader buf = new BufferedReader(fr)) {
            String line = buf.readLine();
            while (line != null) {
                dictionaryPutString(line, true);
                line = buf.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл с данным именем (" + path + ") не найден");
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            printDictionary();
        }
        System.out.println("Файл успешно прочитан");
        return true;
    }

    public void dictionaryPutString(String string, boolean invert) //метод добавляющий строку в словарь если она удовлетворяет условиям одного из языков
    {
        String key;
        String value;

        if (string.contains(" - ")) {
            int index = string.indexOf('-');
            if (invert) {
                value = string.substring(0, index - 1);
                key = string.substring(index + 2);

            } else {
                key = string.substring(0, index - 1);
                value = string.substring(index + 2);
            }

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
        System.out.println(mapToString(dictionary));
        System.out.println("---");
    }

    public void clearDictionary() {
        dictionary.clear();
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
