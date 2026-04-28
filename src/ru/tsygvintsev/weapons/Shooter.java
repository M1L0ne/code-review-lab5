package ru.tsygvintsev.weapons;

public class Shooter {
	private String name;
	private Weapon weapon;

	public Shooter(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void shoot() {
		if (this.weapon != null) {
			weapon.shoot();
		} else {
			System.out.println("не могу участвовать в перестрелке");
		}
	}
}
