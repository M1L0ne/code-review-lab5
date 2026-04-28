package ru.tsygvintsev.phones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * телефонная книга для хранения контактов
 * использует пару телефон - имя
 */
public class PhoneBook {

	/** карта контактов: телефон -> имя */
	private final Map<String, String> contacts;

	/**
	 * конструктор телефонной книги
	 */
	public PhoneBook() {
		this.contacts = new HashMap<>();
	}

	/**
	 * добавление контакта
	 * @param phoneNumber номер телефона
	 * @param name имя контакта
	 * @return старый номер, если контакт обновлён
	 */
	public String addContact(String phoneNumber, String name) {
		String previousNumber = null;

		for (Map.Entry<String, String> entry :
				contacts.entrySet()) {
			if (entry.getValue().equals(name)) {
				previousNumber = entry.getKey();
				contacts.remove(previousNumber);
			}
		}

		contacts.put(phoneNumber, name);
		return previousNumber;
	}

	/**
	 * удаление контакта по имени
	 * @param name имя для удаления
	 */
	public void removeContact(String name) {
		for (Map.Entry<String, String> entry :
				contacts.entrySet()) {
			if (entry.getKey().equals(name)) {
				contacts.remove(entry.getKey());
			}
		}
	}

	/**
	 * поиск контакта по имени
	 * @param name имя для поиска
	 * @return строка с контактом или сообщение об ошибке
	 */
	public String getContact(String name) {
		for (Map.Entry<String, String> entry :
				contacts.entrySet()) {
			if (entry.getValue().equals(name)) {
				return entry.getKey() + " - "
						+ entry.getValue();
			}
		}

		return "Не найдено";
	}

	/**
	 * проверка существования контакта
	 * @param text имя или номер для поиска
	 * @return строка с контактом или сообщение об отсутствии
	 */
	public String checkContact(String text) {
		for (Map.Entry<String, String> entry :
				contacts.entrySet()) {
			if (entry.getValue().equals(text)
					|| entry.getKey().equals(text)) {
				return entry.getKey() + " - "
						+ entry.getValue();
			}
		}

		return "Не существует";
	}

	/**
	 * количество контактов в книге
	 * @return число сохранённых записей
	 */
	public int countContact() {
		return contacts.size();
	}

	/**
	 * получение контактов в виде массива
	 * @param choice тип выборки: 1 - все, 2 - телефоны, 3 - имена
	 * @return массив строк с выбранными данными
	 */
	public String[] getContactArray(int choice) {
		final String[] contactsArray =
				new String[contacts.size()];
		int i = 0;

		switch (choice) {
			case 1:
				for (Map.Entry<String, String> entry :
						contacts.entrySet()) {
					contactsArray[i] = entry.getKey() + " - "
							+ entry.getValue();
					i++;
				}
				break;
			case 2:
				for (Map.Entry<String, String> entry :
						contacts.entrySet()) {
					contactsArray[i] = entry.getKey();
					i++;
				}
				break;
			case 3:
				for (Map.Entry<String, String> entry :
						contacts.entrySet()) {
					contactsArray[i] = entry.getValue();
					i++;
				}
				break;
			default:
				return contactsArray;
		}

		return contactsArray;
	}

	/**
	 * поиск контактов по части имени
	 * @param namePart начальная часть имени
	 * @return массив найденных имён
	 */
	public String[] contactSearch(String namePart) {
		final List<String> foundContacts = new ArrayList<>();

		for (Map.Entry<String, String> entry :
				contacts.entrySet()) {
			final String name = entry.getValue();

			if (name.startsWith(namePart)) {
				foundContacts.add(name);
			}
		}

		return foundContacts.toArray(new String[0]);
	}

	/**
	 * строковое представление книги
	 * @return все контакты через разделитель
	 */
	@Override
	public String toString() {
		String result = "";

		for (Map.Entry<String, String> entry :
				contacts.entrySet()) {
			result = result + entry.getKey() + " - "
					+ entry.getValue() + "; ";
		}

		return result;
	}
}