package ru.tsygvintsev.main;

import ru.tsygvintsev.names.Names;
import ru.tsygvintsev.phones.PhoneBook;
import ru.tsygvintsev.weapons.Automate;
import ru.tsygvintsev.weapons.Gun;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * главный класс для запуска консольного меню задач
 */
public class Main {
    /**
     * точка входа в программу
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int taskNum = 0;

        System.out.println("Для выхода введите '-1'");

        do {
            System.out.println("Введите номер задачи (1-5):");
            System.out.println("1 - Перезарядка пистолета");
            System.out.println("2 - Непустые имена");
            System.out.println("3 - Желтые страницы");
            System.out.println("4 - Автомат");

            try {
                taskNum = readInt(sc);

                switch (taskNum) {
                    case 1: runGunTask(sc); break;
                    case 2: runNamesTask(sc); break;
                    case 3: runPhoneBookTask(sc); break;
                    case 4: runAutomateTask(sc); break;
                    case -1:
                        System.out.println("Выход...");
                        break;
                    default:
                        System.out.println(
                                "Ошибка: число вне диапазона");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число");
            }
        } while (taskNum != -1);
    }

    /**
     * запуск задачи с пистолетом
     * @param sc сканер
     */
    private static void runGunTask(Scanner sc) {
        System.out.println("Введите макс. кол-во патронов:");
        final int max = readInt(sc);
        final Gun gun = new Gun(max);
        int choice;

        do {
            System.out.println("Введите действие:");
            System.out.println("1 - Выстрел, "
                    + "2 - Перезарядка");
            System.out.println("3 - Разрядка, "
                    + "4 - Узнать кол-во");
            System.out.println("5 - Макс. кол-во, "
                    + "6 - Проверка");
            System.out.println("-1 - Выйти");

            choice = readInt(sc);

            switch (choice) {
                case 1: gun.shoot(); break;
                case 2:
                    System.out.println("Введите патроны:");
                    final int reload = readInt(sc);
                    final int ret = gun.load(reload);
                    System.out.println("Вам вернулась "
                            + ret + " пуля.");
                    break;
                case 3:
                    final int unloaded = gun.unload();
                    System.out.println("Вам вернулось "
                            + unloaded + " пуль.");
                    break;
                case 4:
                    System.out.println("Сейчас в пистолете "
                            + gun.getBullets() + " патронов.");
                    break;
                case 5:
                    System.out.println(
                            "Макс. кол-во патронов = "
                            + gun.getMaxBullets());
                    break;
                case 6:
                    if (gun.isLoaded()) {
                        System.out.println(
                                "Пистолет заряжен."
                        );
                    } else {
                        System.out.println(
                                "Пистолет разряжен."
                        );
                    }
                    break;
                case -1:
                    System.out.println("В меню...");
                    break;
                default:
                    System.out.println(
                            "Ошибка: число вне диапазона");
            }
        } while (choice != -1);
    }

    /**
     * запуск задачи с именами
     * @param sc сканер
     */
    private static void runNamesTask(Scanner sc) {
        System.out.println("Введите имя, отчество, фамилию:");
        final String line = sc.nextLine().trim();
        final String[] parts = line.split(" ");

        switch (parts.length) {
            case 1:
                System.out.println(new Names(parts[0]));
                break;
            case 2:
                System.out.println(new Names(parts[0],
                        parts[1]));
                break;
            case 3:
                System.out.println(new Names(
                        parts[0], parts[1], parts[2]
                ));
                break;
            default:
                System.out.println(
                        "Ошибка: введено больше параметров");
        }
    }

    /**
     * запуск задачи с телефонной книгой
     * @param sc сканер
     */
    private static void runPhoneBookTask(Scanner sc) {
        final PhoneBook book = new PhoneBook();
        int choice;

        do {
            System.out.println("Введите действие:");
            System.out.println("1 - Добавить пару, "
                    + "2 - Удалить");
            System.out.println("3 - Получить, "
                    + "4 - Вывести все");
            System.out.println("5 - Проверить, "
                    + "6 - Кол-во");
            System.out.println("7 - Массив, "
                    + "8 - Поиск имён");
            System.out.println("-1 - Выйти");

            choice = readInt(sc);

            switch (choice) {
                case 1:
                    System.out.println(
                            "Введите телефон и имя:");
                    final String add = sc.nextLine().trim();
                    final String[] addParts = add.split(" ");
                    if (addParts.length == 2) {
                        final String old = book.addContact(
                                addParts[0], addParts[1]
                        );
                        if (old != null) {
                            System.out.println("Старый телефон: "
                                    + old);
                        }
                    } else {
                        System.out.println(
                                "Ошибка: введите 2 параметра");
                    }
                    break;
                case 2:
                    System.out.println("Введите имя:");
                    book.removeContact(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Введите имя:");
                    System.out.println(book.getContact(
                            sc.nextLine()));
                    break;
                case 4:
                    System.out.println(book);
                    break;
                case 5:
                    System.out.println(
                            "Введите имя или номер:");
                    System.out.println(book.checkContact(
                            sc.nextLine()));
                    break;
                case 6:
                    System.out.println("Всего "
                            + book.countContact()
                            + " контактов.");
                    break;
                case 7:
                    System.out.println(
                            "1 - Все, 2 - Телефоны, 3 - Имена:");
                    final int arrCh = readInt(sc);
                    final String[] arr = book.getContactArray(
                            arrCh);
                    printArray(arr);
                    break;
                case 8:
                    System.out.println("Укажите часть имени:");
                    final String[] found = book.contactSearch(
                            sc.nextLine());
                    printArray(found);
                    break;
                case -1:
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println(
                            "Ошибка: число вне диапазона");
            }
        } while (choice != -1);
    }

    /**
     * вывод массива строк
     * @param arr массив
     */
    private static void printArray(String[] arr) {
        if (arr.length == 0) {
            System.out.println("Список пуст");
            return;
        }

        for (String item : arr) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }

    /**
     * запуск задачи с автоматом
     * @param sc сканер
     */
    private static void runAutomateTask(Scanner sc) {
        System.out.println("Введите макс. патронов и скорость:");
        System.out.println("Можно ввести 0, 1 или 2 числа:");
        final String autoIn = sc.nextLine().trim();
        Automate automate;

        if (autoIn.isEmpty()) {
            automate = new Automate();
        } else {
            final String[] paramsStr = autoIn.split(" ");
            final int[] params = new int[paramsStr.length];
            boolean valid = true;

            for (int i = 0; i < paramsStr.length; i++) {
                try {
                    params[i] = Integer.parseInt(paramsStr[i]);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Ошибка: введены не числа!"
                    );
                    valid = false;
                    break;
                }
            }

            if (valid) {
                switch (params.length) {
                    case 1:
                        automate = new Automate(params[0]);
                        break;
                    case 2:
                        automate = new Automate(
                                params[0], params[1]);
                        break;
                    default:
                        System.out.println(
                                "Ошибка: много параметров");
                        return;
                }
            } else {
                return;
            }
        }

        int choice;
        do {
            System.out.println("Выберите действие:");
            System.out.println("1 - Стрелять, "
                    + "2 - Очередь (сек)");
            System.out.println("3 - Перезарядить, -1 - Выход");

            choice = readInt(sc);

            switch (choice) {
                case 1: automate.shoot(); break;
                case 2:
                    System.out.println("Введите секунды:");
                    automate.longFire(readInt(sc));
                    break;
                case 3:
                    System.out.println("Введите патроны:");
                    final int ret = automate.load(readInt(sc));
                    System.out.println("Вам вернулась "
                            + ret + " пуля.");
                    break;
                case -1:
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println(
                            "Ошибка: число вне диапазона");
            }
        } while (choice != -1);
    }

    /**
     * безопасное чтение целого числа
     * @param sc сканер
     * @return число
     */
    private static int readInt(Scanner sc) {
        try {
            final int val = sc.nextInt();
            sc.nextLine();
            return val;
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: введите целое число");
            sc.nextLine();
            return 0;
        }
    }
}