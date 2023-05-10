package model.characters;

public class Zombie extends Character {

	static int ZOMBIES_COUNT;

	public Zombie() {
		super("Zombie " + ++ZOMBIES_COUNT, 40, 10);
	}

}
