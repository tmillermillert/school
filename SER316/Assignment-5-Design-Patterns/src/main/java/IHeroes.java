package main.java;

/**
 * IHeroes Class are the methods that all heroes have.
 * @author Marcus Miller
 *
 */
public interface IHeroes {
    
    /**
     * Attack another hero.
     * @param hero is the hero being attacked.
     */
    public boolean attack(IHeroes hero);
    
    /**
     * Is this hero alive.
     */
    public boolean isAlive();
    
    /**
     * Returns true if this is a hero and false is a villain.
     */
    public boolean isHero();
    
    /**
     * Returns this hero's name.
     */
    public String getName();
    
    /**
     * Damages this hero by amount.
     * @param amount that this hero is damaged.
     */
    public void takeDamage(int amount);
}
