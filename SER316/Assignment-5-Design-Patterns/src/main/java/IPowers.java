package main.java;

/**
 * IPowers Class represents the traits that every power has.
 * @author Marcus Miller
 *
 */
public interface IPowers {
    /**
     * Hero1 attacks Hero2.
     * @param hero1 attacking
     * @param hero2 defending
     * @return damage that would be done
     */
    public int attack(IHeroes hero1, IHeroes hero2);
    
    /**
     * The attack damage that this power does.
     * @return attack damage
     */
    public int get_attack_damage();
    
    
    /**
     * Gets the name of the attack.
     * @return String of the attack's name
     */
    public String get_attack_name();
}
