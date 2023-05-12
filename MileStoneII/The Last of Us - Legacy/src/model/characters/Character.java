package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.Map;

public abstract class Character {
  
	private String name;
	private int maxHp;
	private int currentHp;
	private Point location;
	private int attackDmg;
	private Character target;

	public Character(String name, int maxHp, int attackDamage) {
		this.name = name;
		this.maxHp = maxHp;
		this.attackDmg = attackDamage;
		this.currentHp = maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = (currentHp < 0) ? 0 : (currentHp > maxHp) ? maxHp : currentHp;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}
	 public void attack() throws NotEnoughActionsException, InvalidTargetException { 
		 
		 if(this instanceof Hero && target instanceof Hero) {
			 throw new InvalidTargetException("Can't attack a hero as a hero");
		 }
		 if(this instanceof Zombie && target instanceof Zombie) {
			 throw new InvalidTargetException("Can't attack a hero as a zombie");
		 }
		 if(this instanceof Hero && target == null) {
			 throw new InvalidTargetException("no attack target");
		 }
		 if(this instanceof Zombie) {
			 SettargetToZombie();
			 if(target == null)
				 return;
		 }
		 
		 
		 
		 
		  int x = (int) this.getLocation().getX();
		  int y = (int) this.getLocation().getY();
		  int tx = (int) this.getLocation().getX();
		  int ty = (int) this.getLocation().getY();
		  if(Math.abs(x-tx) <= 1 && Math.abs(y-ty) <= 1) {
			  target.setCurrentHp(target.getCurrentHp() - this.attackDmg);
		  } 
		  else  {
			throw new InvalidTargetException("Target is not adjcent");
		}
	}

	 
	
	private void SettargetToZombie() {
		int  zx =  (int) this.getLocation().getX();
		int  zy =  (int) this.getLocation().getY();
  for(int i =0; i < Game.heroes.size(); i++) {
	  Hero hero = Game.heroes.get(i);
	  
  }
	}

	public void defend(Character c) {
	  this.setCurrentHp(this.currentHp- attackDmg/2);
	}
	public void onCharacterDeath(Map map) {
		 if (this.getCurrentHp() <= 0) {
		        for (int i = 0; i < Game.heroes.size(); i++) {
		            if (Game.heroes.get(i).equals(this)) {
		                Game.heroes.remove(i);
		                break;
		            }
		        }
		    }
		    int x = (int) this.getLocation().getX();
		    int y = (int) this.getLocation().getY();
		    Game.map[x][y] = new CharacterCell(null);
	 
	}
	public void GetDmg(int value) {
		currentHp -= value;
		
	}
	
}


