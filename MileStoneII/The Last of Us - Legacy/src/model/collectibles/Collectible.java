package model.collectibles;

import model.characters.Hero;

public interface Collectible {
	public void pickUp(Hero h);
     public	void use(Hero h);
}
