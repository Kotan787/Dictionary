import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary {
    Map<String,String> dictionary = new HashMap<String,String>();
    String kR= "";
    String vR= "";
    public MyDictionary(String keyRegex, String valRegex)
    {
        kR=keyRegex;
        vR=valRegex;
    }

    public Map<String,String> swapValueToKey(Map<String,String> map)
    {
        Map<String,String> result = new HashMap<>();

        String tStr;
        tStr = kR;
        kR = vR;
        vR = tStr;

        for (Map.Entry entry: map.entrySet())
        {
            result.put(entry.getValue().toString(),entry.getKey().toString());
        }

        return map;
    }
    public Map<String, String> readfromfile(String path, Integer language) // прочитать словарь из файла
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
    public void dictionaryPutString(Map map, String string, Integer language) //метод добавляющий строку в словарь если она удовлетворяет условиям одного из языков
    {
        String key ="";
        String value ="";

        if (string.contains(" - ")) {
            key = string.substring(0,string.indexOf('-')-1);
            value = string.substring(string.indexOf('-')+2, string.length());

            if( key.matches(kR) && value.matches(vR))
            {
                map.putIfAbsent(key, value);
                System.out.println("Строка \"" + string + "\" добалвена в словарь");
            }
            else
                System.out.println("Строка \"" + string + "\" не удовлетворяет условиям словаря");
        }
        else
            System.out.println("Строка \"" + string + "\" не удовлетворяет условиям словаря");
    }
    public  void printDictionary(Map dictionary) //вывод словаря
    {
        System.out.println("---");
        dictionary.forEach((key,value) -> {System.out.println(key + " - " + value);});
        System.out.println("---");
    }
}
