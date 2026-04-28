package ru.tsygvintsev.names;

/**
 * неизменяемый класс для хранения имени человека
 * поддерживает фамилию и отчество
 */
public final class Names {

	/** фамилия */
	private final String lastName;
	/** имя */
	private final String firstName;
	/** отчество */
	private final String patronymic;

	/**
	 * конструктор только с именем
	 * @param firstName имя
	 * @throws IllegalArgumentException если имя пустое
	 */
	public Names(String firstName) {
		if (firstName == null || firstName.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Имя не может быть пустым"
			);
		}
		this.firstName = firstName;
		this.lastName = null;
		this.patronymic = null;
	}

	/**
	 * конструктор с именем и фамилией
	 * @param firstName имя
	 * @param lastName фамилия
	 * @throws IllegalArgumentException если оба пустые
	 */
	public Names(String firstName, String lastName) {
		if (isEmpty(firstName) && isEmpty(lastName)) {
			throw new IllegalArgumentException(
					"Хотя бы один параметр "
							+ "должен быть заполнен"
			);
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = null;
	}

	/**
	 * конструктор со всеми полями
	 * @param firstName имя
	 * @param lastName фамилия
	 * @param patronymic отчество
	 * @throws IllegalArgumentException если все пустые
	 */
	public Names(
			String firstName,
			String lastName,
			String patronymic
	) {
		if (isEmpty(firstName) && isEmpty(lastName)
				&& isEmpty(patronymic)) {
			throw new IllegalArgumentException(
					"Хотя бы один параметр "
							+ "должен быть заполнен"
			);
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
	}

	/**
	 * проверка строки на пустоту
	 * @param text проверяемая строка
	 * @return истина, если строка null или состоит из пробелов
	 */
	private static boolean isEmpty(String text) {
		return text == null || text.trim().isEmpty();
	}

	/**
	 * строковое представление полного имени
	 * @return имя, фамилия и отчество через пробел
	 */
	@Override
	public String toString() {
		String result = firstName;

		if (lastName != null && !lastName.isEmpty()) {
			result += " " + lastName;
		}

		if (patronymic != null && !patronymic.isEmpty()) {
			result += " " + patronymic;
		}

		return result;
	}
}