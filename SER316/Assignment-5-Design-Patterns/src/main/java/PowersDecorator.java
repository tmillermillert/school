package main.java;

/**
 * PowersDecorator Class is a trait of a hero.
 * @author Marcus Miller
 *
 */
public class PowersDecorator implements IPowers {
    private int attackDamage;
    private String name;

    /**
     * Hero1 attacks Hero2.
     * @param hero1 attacking
     * @param hero2 defending
     * @return the damage that would be done to hero2 by hero1.
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
     * PowersDecorator constructor.
     * @param power is being combined with a new power
     * @param damageAmount is being added to power's damage
     * @param name of power being added
     */
    public PowersDecorator(IPowers power, int damageAmount, String name) {
        this.attackDamage = power.get_attack_damage() + damageAmount;
        this.name = power.get_attack_name() + " and " + name;
    }

    /**
     * Returns the power's damage.
     * @return this power's attack damage.
     */
    @Override
    public int get_attack_damage() {
        return attackDamage;
    }

    /**
     * Returns the name of the attack.
     * @return A string of the attack's name
     */
    @Override
    public String get_attack_name() {
        return name;
    }
}
