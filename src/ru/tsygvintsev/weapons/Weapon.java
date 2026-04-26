package ru.tsygvintsev.weapons;

/**
 * абстрактный класс оружия
 * базовая реализация для пистолетов и автоматов
 */
public abstract class Weapon {

    /** текущее количество патронов */
    protected int ammo;

    /**
     * конструктор оружия
     * @param ammo начальное количество патронов
     * @throws IllegalArgumentException если патроны
     * отрицательные
     */
    public Weapon(int ammo) {
        if (ammo < 0) {
            throw new IllegalArgumentException(
                    "количество патронов "
                            + "не может быть отрицательным"
            );
        }
        this.ammo = ammo;
    }

    /**
     * выполнение выстрела
     * абстрактный метод для реализации в наследниках
     */
    public abstract void shoot();

    /**
     * получение текущего количества патронов
     * @return текущее количество патронов
     */
    public int getAmmoCount() {
        return ammo;
    }

    /**
     * проверка наличия патрона и его расходование
     * @return истина, если был патрон, иначе ложь
     */
    public boolean consumeAmmo() {
        if (ammo == 0) {
            return false;
        }
        ammo--;
        return true;
    }

    /**
     * загрузка патронов
     * @param bullets количество патронов для загрузки
     * @return количество патронов, которое не влезло
     */
    public int load(int bullets) {
        if (bullets < 0) {
            throw new IllegalArgumentException(
                    "Количество патронов "
                            + "не может быть отрицательным"
            );
        }
        final int tmp = bullets;
        this.ammo = bullets;
        return tmp;
    }
}