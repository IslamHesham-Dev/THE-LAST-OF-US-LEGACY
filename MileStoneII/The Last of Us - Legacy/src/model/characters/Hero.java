package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import javax.print.attribute.standard.Sides;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.EmptyCell;
import model.world.Map;
import model.world.TrapCell;

public abstract class Hero extends Character {

	private int actionsAvailable;
	private int maxActions;
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;
	private Map currentCell;
    private Map map;
    private int DeltaX,DeltaY = 1;

	public Hero(String name, int maxHp, int attackDamage, int maxActions) {
		super(name, maxHp, attackDamage);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;
		this.supplyInventory = new ArrayList<Supply>();
		this.vaccineInventory = new ArrayList<Vaccine>();
		
	}

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
		this.actionsAvailable = actionsAvailable;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}
	public void attack()throws InvalidTargetException,NotEnoughActionsException{
		if(actionsAvailable == 0) {
			throw new NotEnoughActionsException("You ran out of actions");
		}
		
		super.attack();
		
		if(this instanceof Fighter && this.specialAction == true) {
			return;
		}
		actionsAvailable --;
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	  public void setStartingCell(Map startingCell, Map map) {
	        this.currentCell = startingCell;
	        this.map = map;
	        this.currentCell.setVisible(true);
	    }

    public void move(Direction d) throws NotEnoughActionsException, MovementException, InvalidTargetException {
 
    	 
        if (actionsAvailable <= 0) {
            throw new NotEnoughActionsException("Not enough actions");
        }
        
       
        Point currentLocation = getLocation();
        int x = (int) currentLocation.getX();
        int y = (int) currentLocation.getY();
        
        
        int deltaX = x;
        int deltaY = y;
        
        if (d == Direction.LEFT) {
            if (this.getLocation().y == 0) {
                throw new MovementException("trying to move left but y is at 0");
            } else {
                deltaY = y - 1;
            }
        } else if (d == Direction.RIGHT) {
            if (this.getLocation().y == 14) {
                throw new MovementException("trying to move right and y is at 14");
            } else {
                deltaY = y + 1;
            }
        } else if (d == Direction.UP) {
            if (this.getLocation().x == 14) {
                throw new MovementException();
            } else {
                deltaX = x + 1;
            }
        } else if (d == Direction.DOWN) {
            if (this.getLocation().x == 0) {
                throw new MovementException();
            } else {
                deltaX = x - 1;
            }
        }
         if (deltaX < 0 || deltaX >= Game.map.length || deltaY < 0 || deltaY >= Game.map[deltaX].length) { // collison check
            throw new MovementException("World Boundary");
        }
        
         if (Game.map[deltaX][deltaY] instanceof CharacterCell) {
            throw new MovementException("Unable to move here");
        }  
         if (Game.map[deltaX][deltaY] instanceof TrapCell) {
            TrapCell trapCell = (TrapCell) Game.map[deltaX][deltaY];
            super.setCurrentHp(super.getCurrentHp() - trapCell.getTrapDamage());
            if (super.getCurrentHp() <= 0) {
                return;
            }
            
        }  
         if (Game.map[deltaX][deltaY] instanceof CollectibleCell) {
            CollectibleCell cell = (CollectibleCell) Game.map[deltaX][deltaY];
            cell.getCollectible().pickUp(this);
        }
        
        
        CharacterCell newCell = new CharacterCell(this);
        Game.map[deltaX][deltaY] = newCell;
        this.setLocation(new Point(deltaX, deltaY));
         
        CharacterCell oldCell = (CharacterCell) Game.map[x][y];
        oldCell.setCharacter(null);
        
        
        for (int i = Math.max(0, deltaX - 1); i <= Math.min(Game.map.length - 1, deltaX + 1); i++) {
            for (int j = Math.max(0, deltaY - 1); j <= Math.min(Game.map[i].length - 1, deltaY + 1); j++) {
                Game.map[i][j].setVisible(true);
            }
        }
        
        
        actionsAvailable--;
    	
    	
    	
}
    public void useSpecial() throws NoAvailableResourcesException {
    	if(supplyInventory.isEmpty())
    		throw new NoAvailableResourcesException("You have ran out of supplies.");
    }

    public void cure() throws NoAvailableResourcesException, NotEnoughActionsException, InvalidTargetException {
    	if (getVaccineInventory().isEmpty()) {
    	  throw new NoAvailableResourcesException("Ran out of Vaccine");
    	}
     	if (actionsAvailable <= 0) {
      	  throw new NotEnoughActionsException("Ran out of actions");
      	}
      	if(super.getTarget() == null) {
      		throw new InvalidTargetException("Target is null");
      	}
    	if(super.getTarget() instanceof Hero) {
      		throw new InvalidTargetException("Target has to be a zombie");
      	}
    	
    	int x = (int) this.getLocation().getX();
    	int y =  (int) this.getLocation().getY();
    	int newx = (int) getTarget().getLocation().getX();
      	int newy = (int) getTarget().getLocation().getY();
    	
    	if(Math.abs(x-newx) <= 1 && Math.abs(y- newy) <= 1) {
    		CharacterCell cell = (CharacterCell) Game.map[newx][newy];
    		if(cell.getCharacter() instanceof Zombie) {
    			int r = (int) (Math.random() * Game.availableHeroes.size());
    			
    			Zombie zombie = (Zombie) cell.getCharacter();
    			Game.zombies.remove(zombie);
  		
    			
    	}
    		 
    		 
    	
    	
    	
        }
    
    
}
}
