package main.java;

/**
 * Hero Class.
 * @author Marcus Miller
 *
 */
public class Heroes implements IHeroes {
    private int health;
    private boolean isHero;
    private String name;
    private IPowers power;
    
    /**
     * Attack another hero.
     * @param hero is the hero being attacked.
     */
    public boolean attack(IHeroes hero) {
        System.out.printf("%s attacks %s for %d damage with %s%n", this.getName(),
                hero.getName(), power.attack(this, hero), power.get_attack_name());
        hero.takeDamage(power.attack(this, hero));
        if (!hero.isAlive()) {
            System.out.printf("%s killed %s%n", this.getName(), hero.getName());
        }
        return hero.isAlive();
    }
    
    /**
     * Is this hero alive.
     */
    public boolean isAlive() {
        return health > 0;
    }
    
    /**
     * Returns true if this is a hero and false is a villain.
     */
    public boolean isHero() {
        return this.isHero;
    }
    
    /**
     * Returns this hero's name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Damages this hero by amount.
     * @param amount that this hero is damaged.
     */
    public void takeDamage(int amount) {
        health -= amount;
    }
    
    /**
     * Hero Constructor.
     * @param power of hero
     * @param name of hero
     * @param isHero true if is hero and false if villain. 
     * @param health of hero
     */
    public Heroes(IPowers power, String name, boolean isHero, int health) {
        this.isHero = isHero;
        this.health = health;
        this.power = power;
        this.name = name;
    }
}
