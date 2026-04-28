package ru.tsygvintsev.weapons;

/**
 * класс автомата
 * наследует пистолет и добавляет скорострельность
 */
public class Automate extends Gun {

	/** скорострельность в выстрелах за секунду */
	private final int speed;

	/**
	 * конструктор автомата с параметрами по умолчанию
	 */
	public Automate() {
		this.speed = 30;
	}

	/**
	 * конструктор с заданным лимитом магазина
	 * @param maxBullets максимальное количество патронов
	 */
	public Automate(int maxBullets) {
		super(maxBullets);
		this.speed = maxBullets / 2;
	}

	/**
	 * конструктор с лимитом и скорострельностью
	 * @param maxBullets максимальное количество патронов
	 * @param speed скорострельность
	 */
	public Automate(int maxBullets, int speed) {
		super(maxBullets);
		this.speed = speed;
	}

	/**
	 * автоматическая очередь
	 * стреляет количество раз равное скорострельности
	 */
	@Override
	public void shoot() {
		for (int i = 0; i < speed; i++) {
			super.shoot();
		}
	}

	/**
	 * длительная стрельба
	 * @param time время стрельбы в секундах
	 */
	public void longFire(int time) {
		final int totalShots = time * speed;
		for (int i = 0; i < totalShots; i++) {
			super.shoot();
		}
	}
}