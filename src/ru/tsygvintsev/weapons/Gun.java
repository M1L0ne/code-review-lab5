package ru.tsygvintsev.weapons;

/**
 * класс пистолета
 * наследует базовое оружие и добавляет лимит магазина
 */
public class Gun extends Weapon {

    /** максимальная вместимость магазина */
    private final int maxBullets;

    /**
     * конструктор пистолета с параметрами по умолчанию
     */
    public Gun() {
        super(0);
        this.maxBullets = 30;
    }

    /**
     * конструктор пистолета с заданным лимитом
     * @param maxBullets максимальное количество патронов
     */
    public Gun(int maxBullets) {
        super(0);
        this.maxBullets = maxBullets;
    }

    /**
     * получение максимальной вместимости
     * @return лимит магазина
     */
    public int getMaxBullets() {
        return maxBullets;
    }

    /**
     * выполнение выстрела
     * проверяет наличие патронов перед выстрелом
     */
    @Override
    public void shoot() {
        if (consumeAmmo()) {
            System.out.println("Бах!");
        } else {
            System.out.println("Клац!");
        }
    }

    /**
     * загрузка патронов с учётом лимита
     * @param bullets количество патронов для загрузки
     * @return остаток патронов, если магазин полон
     */
    @Override
    public int load(int bullets) {
        if (bullets < 0) {
            System.out.println(
                    "Не может быть отрицательного "
                            + "числа патронов."
            );
            return 0;
        }

        if (bullets > maxBullets) {
            ammo = maxBullets;
            return bullets - maxBullets;
        }

        ammo = bullets;
        return 0;
    }

    /**
     * полная разрядка оружия
     * @return количество извлечённых патронов
     */
    public int unload() {
        final int returnBullets = ammo;
        ammo = 0;
        return returnBullets;
    }

    public int getBullets() {
        return ammo;
    }

    /**
     * проверка заряженности
     * @return истина, если в оружии есть патроны
     */
    public boolean isLoaded() {
        return ammo > 0;
    }

    /**
     * строковое представление оружия
     * @return описание текущего состояния пистолета
     */
    @Override
    public String toString() {
        return "Пистолет с максимумом " + maxBullets
                + " патронов, заряженный на "
                + ammo + " пуль.";
    }
}