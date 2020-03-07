package main.java;

/**
 * Hero's Vs. Villains Simulation.
 * @author tmillermillert
 *
 */
public class Main {

    /**
     * Simulation of a hero Vs. a villain.
     * @param args none
     */
    public static void main(String[] args) {
        PowersDecorator power1 = new PowersDecorator(new Powers(10, "Fire"), 5, "Ice");
        PowersDecorator power2 = new PowersDecorator(power1, 5, "Ice");
        Heroes player1 = new Heroes(power1, "Fire and Ice Wizzard", true, 500);
        Heroes player2 = new Heroes(power2, "Fire and Double Ice Wizzard", false, 150);
        boolean isPlayer1Turn = true;
        while (player1.isAlive() && player2.isAlive()) {
            if (isPlayer1Turn) {
                player1.attack(player2);
            } else {
                player2.attack(player1);
            }
            isPlayer1Turn = !isPlayer1Turn;
        }
    }
}
