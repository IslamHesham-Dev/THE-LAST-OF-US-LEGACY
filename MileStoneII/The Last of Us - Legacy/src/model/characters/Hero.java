package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.EmptyCell;
import model.world.Map;

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
      /* if (actionsAvailable <= 0) {
          throw new NotEnoughActionsException("Not enough actions.");
        }
	
        int currentRow = map.getNumRows() - 1 - currentCell.getNumRows();
        int currentCol = currentCell.getNumRows();
       
        int newRow = currentRow;
        int newCol = currentCol;
         switch (d) {
        case UP:
            newRow = currentRow - DeltaY;
            break;
        case DOWN:
            newRow = currentRow + DeltaY;
            break;
        case LEFT:
            newCol = currentCol - DeltaX;
            break;
        case RIGHT:
            newCol = currentCol + DeltaX;
            break;
        }
         if (newRow < 0 || newRow >= map.getNumRows() || newCol < 0 || newCol >= map.getNumCols()) // collsion check
         {
           throw new MovementException("World Boundry.");
         }

         Map newCell = (Map) map.getCell(map.getNumRows() - 1 - newRow, newCol);
         actionsAvailable--; 
         currentCell.setVisible(true);
         newCell.setVisible(true);
        currentCell = newCell;    
       
      
          map.updateMap(this);
     
    	*/
    	if(actionsAvailable ==0)
    		throw new NotEnoughActionsException("Not enough actions");
    	int x = (int) getLocation().getX();
    	int y = (int) getLocation().getX();
         //new loc
    	int newx,newy;
    	if(d == Direction.LEFT) {
    		newx = x;
    		newy = y-1;
    	}
    	else if(d== Direction.RIGHT) {
    		newx = x;
    		newy = y +1;
    	}
    	else if(d== Direction.RIGHT) {
    		newx = x+1;
    		newy = y;
    	}
    	else {
    		newx = x +1;
    		newy = y;
    	}
    	if(newx <0||newy < 0 || newx == 15) {
    		throw new MovementException("World Boundry");
    	}
    	if(Math.abs(x - newx) <= 1 && Math.abs(y-newy) <=1) {
    	CharacterCell cell = (CharacterCell) Game.map[newx][newy];
    	if(cell.getCharacter() instanceof Zombie) {
    		int r = (int) (Math.random() * Game.availableHeroes.size());
    		Zombie zombie = (Zombie) cell.getCharacter();
    		Game.zombies.remove(zombie);
    		Hero hero = Game.availableHeroes.remove(r);
    		hero.setLocation(new Point (newx,newx));
    		cell.setCharacter(hero);
    		Game.heroes.add(hero);
    		if(newx-1 >=0 && newy-1 >= 0) {
    			Game.map[newx-1][newy -1].setVisible(true);
    		}
    		if(newx +1 <15  && newy-1 >= 0) {
    			Game.map[newx+1][newy -1].setVisible(true);
    		}
    		if(newx -1 >= 0  && newy +1 < 15) {
    			Game.map[newx-1][newy +1].setVisible(true);
    		}
    		if(newx +1 < 15 && newy +1 < 15) {
    			Game.map[newx+1][newy +1].setVisible(true);
    		}
    		if(newx +1 < 15) {
    			Game.map[newx + 1][newy].setVisible(true);
    		}
    		if(newx -1 >= 0) {
    			Game.map[newx -1][newy].setVisible(true);
    		}
    		if(newx -1 >= 0) {
    			Game.map[newx][newy-1].setVisible(true);
    		}
    		if(newx +1 <15) {
    			Game.map[newx][newy+1].setVisible(true);
    		}else {
    			throw new InvalidTargetException("Out of range");
    		}
    	}
    	vaccineInventory.get(0).use(this);
    	actionsAvailable--;
    	}
      // move actions 
    	Game.map[newx][newy] = new CharacterCell(this);
    	this.setLocation(new Point(newx,newy));
    	((CharacterCell) Game.map[x][y]).setCharacter(null);
    	
    	if(newx -1 >= 0 && newy -1 >= 0) {
    		Game.map[newx-1][newy-1].setVisible(true);
    	}
    	if(newx +1 < 15 && newy -1 >= 0) {
    		Game.map[newx+1][newy-1].setVisible(true);
    	}
       	if(newx -1 >= 0 && newy +1 < 15) {
    		Game.map[newx+1][newy-1].setVisible(true);
    	}
     	if(newx +1 < 15 && newy +1 < 15) {
    		Game.map[newx+1][newy-1].setVisible(true);
    	}
     	if(newx + 1 < 15)
        Game.map[newx+1][newy].setVisible(true);
	
     	if(newx -1 >= 0)
        Game.map[newx-11][newy].setVisible(true);
    	
     	if(newy -1 >= 0)
            Game.map[newx][newy-1].setVisible(true);
        	
     	if(newy +1 >= 0)
            Game.map[newx][newy+1].setVisible(true);
        		
    	actionsAvailable--;
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
}
    public void useSpecial() {
    	
    }

    public void cure() throws NoAvailableResourcesException {
    	if (getVaccineInventory().isEmpty()) {
    	  throw new NoAvailableResourcesException("Ran out of Vaccine");
    	}
        }
       

	
    
    
    
    
}
