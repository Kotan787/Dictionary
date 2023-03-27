import java.util.Scanner;

/*
 2 словаря с раными регулярками от интерфейса
 выбор словаря
 переиминовать нормально переменные
 обрабатывать оишбки нормально
 */
public class Main {
    public static void main(String[] args) {

        try {

            Scanner scan = new Scanner(System.in);
            boolean exit = false;
            boolean choseDictionary = false;

            MyDictionaryFirst dictionaryFirst = new MyDictionaryFirst();
            MyDictionarySecond dictionarySecond = new MyDictionarySecond();
            String path = "";
            // var dictionaryActive;

            while (!choseDictionary) {
                System.out.println(
                        "Выберете пункт меню:" + "\n"
                                + "1. Первый словарь" + "\n"
                                + "2. Второй словарь" + "\n"
                                + "3. Выход" + "\n" +
                                "\n");
                int menuinput = scan.nextInt();
                switch (menuinput) {
                    case 1:
                        //   dictionaryActive = dictionaryFirst;
                        exit = false;
                        break;
                    case 2:
                        //      dictionaryActive = dictionarySecond;
                        exit = false;
                        break;
                    case 3:
                        choseDictionary = true;
                        exit = true;
                        break;
                }
                while (!exit) {
                    System.out.println(
                            "Выберете пункт меню:" + "\n"
                                    + "1. Чтение из файла" + "\n"
                                    + "2. Просмотр словаря" + "\n"
                                    + "3. Удаление записи по ключу в словаре" + "\n"
                                    + "4. Поиск записи по ключу в словаре" + "\n"
                                    + "5. Добавление записи по ключу удовлетворяющей условиям в словарь" + "\n"
                                    + "6. Перевернуть словарь" + "\n"
                                    + "7. Выход" + "\n");
                    menuinput = scan.nextInt();

                    switch (menuinput) {
                        case 1:
                            path = "";
                            System.out.println("Введите путь к файлу: ");
                            path = scan.next();
                            dictionaryFirst.readFromFile(path);
                            dictionaryFirst.printDictionary();
                            break;
                        case 2:
                            dictionaryFirst.printDictionary();
                            break;
                        case 3:

                            System.out.println("Введите ключ по которому необходимо удалить запись в словаре: ");
                            String kdelete = scan.next();
                            dictionaryFirst.deleteKey(kdelete);

                            break;
                        case 4:

                            System.out.println("Введите ключ по которому необходимо найти запись в словаре: ");
                            String ksearch = scan.next();
                            String val = dictionaryFirst.searchKey(ksearch);
                            System.out.println("Значение по запрошеному ключу: " + val);
                            break;
                        case 5:

                            System.out.println("Введите ключ: ");
                            String keyAdd = scan.next();
                            System.out.println("Введите значение: ");
                            String valueAdd = scan.next();
                            keyAdd += " - " + valueAdd;
                            dictionaryFirst.dictionaryPutString(keyAdd);

                            break;
                        case 6:
//                       dictionaryFirst.swapValueToKey();
//                       System.out.println("Словарь перевернут");
                            break;
                        case 7:
                            exit = true;
                            choseDictionary = false;
                            break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}