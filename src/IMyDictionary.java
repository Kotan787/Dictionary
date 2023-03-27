public interface IMyDictionary//интерфейс для класса словаря
{
 //   public void swapValueToKey();
    public void readFromFile(String path);
    public void dictionaryPutString(String string);
    public void deleteKey(String key);
    public String searchKey(String key);
}
