package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;

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
		 }
	      
 
	  public static boolean checkWin() {
	  
		   return true;
	    
	 }
	  public static boolean checkGameOver() {
	  if(heroes.isEmpty() )
		return true;
	
	    return false;
		  
	  }
	  public static void endTurn() {
		  
	  }
}
 
