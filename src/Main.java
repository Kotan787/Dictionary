import java.util.Scanner;

/*
 +2 словаря с раными регулярками от интерфейса
 +выбор словаря
 +переиминовать нормально переменные
 +обрабатывать оишбки нормально
 */
public class Main {
    public static int inputForMenu(Scanner scanner) {
        int menuInput;
        while (true) {
            String inputText = scanner.next();
            if (inputText.matches("^\\d{0,10}$")) {
                menuInput = Integer.parseInt(inputText);
                break;
            } else {
                System.out.println("Введите номер пункта меню еще раз");
            }

        }
        return menuInput;
    }

    public static void main(String[] args) {

        try (Scanner scan = new Scanner(System.in)) {

            boolean exitFunctionsMenu = false;
            boolean exitDictionaryMenu = false;

            MyDictionaryFirst dictionaryFirst = new MyDictionaryFirst();
            MyDictionarySecond dictionarySecond = new MyDictionarySecond();
            MyDictionaryAny dictionaryActive = new MyDictionaryAny();
            String path;
            int menuInput;

            while (!exitDictionaryMenu) {
                System.out.println(
                        """
                                Выберете пункт меню:
                                1. Первый словарь
                                2. Второй словарь
                                3. Выход

                                """);

                menuInput = inputForMenu(scan);


                switch (menuInput) {
                    case 1:
                        dictionaryActive = dictionaryFirst;
                        System.out.println("Словарь 1 выбран \n");
                        exitFunctionsMenu = false;
                        exitDictionaryMenu = true;
                        break;
                    case 2:
                        dictionaryActive = dictionarySecond;
                        System.out.println("Словарь 2 выбран \n");
                        exitFunctionsMenu = false;
                        exitDictionaryMenu = true;
                        break;
                    case 3:
                        exitDictionaryMenu = true;
                        exitFunctionsMenu = true;
                        break;
                    default:
                        System.out.println("Пункт меню с таким номером отсутсвует");
                        exitFunctionsMenu = true;
                        break;
                }
                while (!exitFunctionsMenu) {
                    System.out.println(
                            """
                                    Выберете пункт меню:
                                    1. Чтение из файла
                                    2. Запись в файл
                                    3. Просмотр словаря
                                    4. Удаление записи по ключу в словаре
                                    5. Поиск записи по ключу в словаре
                                    6. Добавление записи по ключу удовлетворяющей условиям в словарь
                                    7. Сменить словарь
                                    8. Выход
                                    """);
                    menuInput = inputForMenu(scan);

                    switch (menuInput) {
                        case 1:
                            System.out.println("Введите путь к файлу: ");
                            path = scan.next();
                            dictionaryActive.readFromFile(path);
                            break;
                        case 2:
                            System.out.println("Введите путь к файлу: ");
                            path = scan.next();
                            dictionaryActive.writeToFile(path);
                            break;
                        case 3:
                            dictionaryActive.printDictionary();
                            break;
                        case 4:
                            System.out.println("Введите ключ по которому необходимо удалить запись в словаре: ");
                            String keyDelete = scan.next();
                            dictionaryActive.deleteKey(keyDelete);
                            break;
                        case 5:
                            System.out.println("Введите ключ по которому необходимо найти запись в словаре: ");
                            String keySearch = scan.next();
                            String val = dictionaryActive.searchKey(keySearch);
                            System.out.println("Значение по запрошеному ключу: " + val);
                            break;
                        case 6:
                            System.out.println("Введите ключ: ");
                            String keyAdd = scan.next();
                            System.out.println("Введите значение: ");
                            String valueAdd = scan.next();
                            keyAdd += " - " + valueAdd;
                            dictionaryActive.dictionaryPutString(keyAdd, false);
                            break;
                        case 7:
                            exitFunctionsMenu = true;
                            exitDictionaryMenu = false;
                            break;
                        case 8:
                            exitFunctionsMenu = true;
                            exitDictionaryMenu = true;
                            break;
                        default:
                            System.out.println("Пункт меню с таким номером отсутсвует");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}