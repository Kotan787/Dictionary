public interface IMyDictionary//интерфейс для класса словаря
{
    //   public void swapValueToKey();
    boolean readFromFile(String path);

    boolean writeToFile(String path);

    void dictionaryPutString(String string, boolean invert);

    void deleteKey(String key);

    String searchKey(String key);
}
