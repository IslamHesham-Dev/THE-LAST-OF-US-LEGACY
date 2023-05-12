package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {

	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell[][] map;
	 

	public static void loadHeroes(String filePath) throws IOException {
		availableHeroes = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] sp = line.split(",");
			Hero h;
			if (sp[1].equals("EXP")) {
				h = new Explorer(sp[0], Integer.parseInt(sp[2]), Integer.parseInt(sp[4]), Integer.parseInt(sp[3]));
			} else if (sp[1].equals("FIGH")) {
				h = new Fighter(sp[0], Integer.parseInt(sp[2]), Integer.parseInt(sp[4]), Integer.parseInt(sp[3]));
			} else {
				h = new Medic(sp[0], Integer.parseInt(sp[2]), Integer.parseInt(sp[4]), Integer.parseInt(sp[3]));
			}
			availableHeroes.add(h);
			line = br.readLine();
		}
		br.close();
	}
	 public static void startGame(Hero h) {
	 map = new Cell[15][15];
	 for(int i =0;i < map.length;i++) {
		 for(int j = 0; j < map.length;j++)
			 map[i][j] = new CharacterCell(null);
	 }
	 map[0][0] = new CharacterCell(h);
	 availableHeroes.remove(h);
	 heroes.add(h);
	 h.setLocation(new Point (0,0));
	 map[0][0].setVisible(true);
	 map[0][1].setVisible(true);
	 map[1][0].setVisible(true);
	 map[1][1].setVisible(true);
	 
 
	 
	 
 
	 
	 
	 
	 
	 
	 
	// add 5 traps
          SpawnTraps(5);
	     SpawnSupplies(5);
		 SpawnZombie(5);
		 SpawnZombie(5);
	     SpawnVaccine(5);
	 
	 
}
	 private static void SpawnTraps(int value) {
		    int n = 0;
		    for (int x = 0; x < 15; x++) {
		        for (int y = 0; y < 15; y++) {
		            if (map[x][y] instanceof CharacterCell && ((CharacterCell) map[x][y]).getCharacter() == null) {
		                if (Math.random() < 0.1 && n < value) { // 10% chance of spawning a trap
		                	 map[x][y] = new TrapCell();
		                    n++;
		                }
		            }
		            if (n >= value) { // exit the loop once 5 traps have been generated
		                break;
		            }
		        }
		        if (n >= value) { // exit the loop once 5 traps have been generated
		            break;
		        }
		    }
		}

	 
	 private static void SpawnSupplies(int value) {
		    int n = 0;
		    for (int x = 0; x < 15; x++) {
		        for (int y = 0; y < 15; y++) {
		            if (map[x][y] instanceof CharacterCell && ((CharacterCell) map[x][y]).getCharacter() == null) { 
		                if (Math.random() < 0.1 && n < value) {  
		                    Supply supply = new Supply();
		                    map[x][y] = new CollectibleCell(supply);
		                    n++;
		                }
		            }
		            if (n >= value) { 
		                break;
		            }
		        }
		        if (n >= value) {  
		            break;
		        }
		    }
		}

	private static void SpawnVaccine(int value) {
		int n = 0;
		 for (int x = 0; x < 15; x++) {
		     for (int y = 0; y < 15; y++) {
		         if (map[x][y] instanceof CharacterCell&& ((CharacterCell) map[x][y]).getCharacter() == null) { 
		             if (Math.random() < 0.1 && n < value) { // 10% chance of spawning a vaccine
		                 Vaccine vaccine = new Vaccine();
		                 map[x][y] = new CollectibleCell(vaccine);
		                 n++;
		             }
		         }
		         if (n >= value) { // exit the loop once 5 vaccines have been generated
		             break;
		         }
		     }
		     if (n >= value) { // exit the loop once 5 vaccines have been generated
		         break;
		     }
		 }
	}
	private static void SpawnZombie(int value) {
		int n = 0;
		 for (int x = 0; x < 15; x++) {
		     for (int y = 0; y < 15; y++) {
		         if (map[x][y] instanceof CharacterCell && ((CharacterCell) map[x][y]).getCharacter() == null) {
		             if (Math.random() < 0.1 && n < value) { 
		                 Zombie zombie = new Zombie();
		                 zombie.setLocation(new Point(x, y));
		                 map[x][y] = new CharacterCell(zombie);
		                 n++;
		             }
		         }
		         if (n >= value) {  
		             break;
		         }
		     }
		     if (n >= value) {  
		         break;
		     }
		 }
	}
	      
 
	  public static boolean checkWin() {
	  	  int vaccineCount = VaccineCount();
		    int inventoryVaccineCount = InvCounter();

		    // Check the win condition
		    return vaccineCount == 0 && inventoryVaccineCount == 0 && heroes.size() >= 5;
	 }
	 private static int InvCounter() {
		int inventoryVaccineCount = 0;
		
		for (Hero hero : heroes) {
		    inventoryVaccineCount += hero.getVaccineInventory().size();
		}
		return inventoryVaccineCount;
	}
	 private static int VaccineCount() {
		int vaccineCount = 0;
		    for (int i = 0; i < map.length; i++) {
		        for (int j = 0; j < map.length; j++) {
		            if (map[i][j] instanceof CollectibleCell) {
		                Collectible collectible = ((CollectibleCell) map[i][j]).getCollectible();
		                if (collectible instanceof Vaccine) {
		                    vaccineCount++;
		                }
		            }
		        }
		    }
		return vaccineCount;
	}

	 public static boolean checkGameOver() {
	  if( checkWin() || heroes.isEmpty() )
		return true;
	
	    return false;
		  
	  }
	  public static void endTurn() throws NotEnoughActionsException, InvalidTargetException {
		  for (Zombie zombie : zombies) {
			    zombie.attack();
			    zombie.setTarget(null);
			}
		  for (Hero hero : heroes) {
			    hero.setActionsAvailable(hero.getMaxActions());
			    hero.setTarget(null);
			    hero.setSpecialAction(false);
			}
		  ResetVisibilty();
		  CheckVisbility();
		  
		  
		  
		  
		  
		  
		  
		  
		SpawnZombie(1);  
	  }
	private static void ResetVisibilty() {
		for(int i =0;i < map.length; i++) {
			  for(int j =0;j < map.length; j++) {
				  map[i][j].setVisible(false);
			  }
		  }
	}
	private static void CheckVisbility() {
		for (Hero hero : heroes) {
			    int x = (int) hero.getLocation().getX();
			    int y = (int) hero.getLocation().getY();
			    map[x][y].setVisible(true);

			    // Check for adjacent cells
			    for (int i = x - 1; i <= x + 1; i++) {
			        for (int j = y - 1; j <= y + 1; j++) {
			            // Check if cell is within map boundaries
			            if (i >= 0 && i < 15 && j >= 0 && j < 15) {
			                map[i][j].setVisible(true);
			            }
			        }
			    }
			}
	}
	   
 
}
 