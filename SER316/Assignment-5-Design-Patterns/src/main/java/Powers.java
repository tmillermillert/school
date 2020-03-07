package main.java;

/**
 * Powers Class.
 * @author Marcus Miller
 *
 */
public class Powers implements IPowers {
    private int attackDamage;
    private String name;

    /**
     * Hero1 attacks Hero2.
     * @param hero1 attacking
     * @param hero2 defending
     */
    @Override
    public int attack(IHeroes hero1, IHeroes hero2) {
        if (hero1.isHero() == hero2.isHero()) {
            return 0;
        } else {
            return attackDamage;
        }
    }

    /**
     * Powers Constructor.
     * @param damageAmount amount of damage the power will do
     * @param name of the power
     */
    public Powers(int damageAmount, String name) {
        this.attackDamage = damageAmount;
        this.name = name;
    }

    /**
     * Returns the power's damage.
     */
    public int get_attack_damage() {
        return attackDamage;
    }

    /**
     * Returns the name of the attack.
     */
    public String get_attack_name() {
        return name;
    }

}
