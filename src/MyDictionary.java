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

    public Map<String,String> GetDictionary()
    {
        return dictionary;
    }
    public void SetDictionary(Map<String,String> map)
    {
        dictionary = map;
    }

    public void swapValueToKey()
    {
        Map<String,String> result = new HashMap<>();

        String tStr;
        tStr = kR;
        kR = vR;
        vR = tStr;

        for (Map.Entry entry: dictionary.entrySet())
        {
            result.put(entry.getValue().toString(),entry.getKey().toString());
        }

    }
    public void readfromfile(String path) // прочитать словарь из файла
    {
        try
        {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader buf = new BufferedReader(fr);
            String line = buf.readLine();
            dictionaryPutString( line);
            while (line !=null)
            {
                dictionaryPutString( line);
                line = buf.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Файл успешно прочитан");
    }
    public void dictionaryPutString(String string) //метод добавляющий строку в словарь если она удовлетворяет условиям одного из языков
    {
        String key ="";
        String value ="";

        if (string.contains(" - ")) {
            key = string.substring(0,string.indexOf('-')-1);
            value = string.substring(string.indexOf('-')+2, string.length());

            if( key.matches(kR) && value.matches(vR))
            {
                dictionary.putIfAbsent(key, value);
                System.out.println("Строка \"" + string + "\" добалвена в словарь");
            }
            else
                System.out.println("Строка \"" + string + "\" не удовлетворяет условиям словаря");
        }
        else
            System.out.println("Строка \"" + string + "\" не удовлетворяет условиям словаря");
    }
    public  void printDictionary() //вывод словаря
    {
        System.out.println("---");
        dictionary.forEach((key,value) -> {System.out.println(key + " - " + value);});
        System.out.println("---");
    }
}
