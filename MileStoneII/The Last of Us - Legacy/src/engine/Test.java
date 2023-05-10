package engine;

 import model.characters.Zombie;
import model.world.CharacterCell;
import model.world.EmptyCell;
import model.world.Map;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
import model.characters.Direction;
import model.characters.Fighter;
   
public class Test {
  public static void main(String[] args) {
	/*	Fighter fighter = new Fighter("Big Chungus", 100, 20, 5);
		Zombie zombie = new Zombie();
		System.out.println(fighter.getName() + " has " + fighter.getCurrentHp() + " HP");
		System.out.println(zombie.getName() + " has " + zombie.getCurrentHp() + " HP");
		try {
			fighter.attack();
		} catch (NotEnoughActionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fighter.getName() + " attacks " + zombie.getName() + ".");
		System.out.println(zombie.getName() + " has " + zombie.getCurrentHp() + " HP");
		zombie.defend(zombie);
		System.out.println(zombie.getName() + " defends.");
		try {
			zombie.attack();
		} catch (NotEnoughActionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(zombie.getName() + " attacks " + fighter.getName() + ".");
		System.out.println(fighter.getName() + " has " + fighter.getCurrentHp() + " HP");
		fighter.defend(fighter);
		System.out.println(fighter.getName() + " defends.");
		try {
			zombie.attack();
		} catch (NotEnoughActionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(zombie.getName() + " attacks " + fighter.getName() + ".");
		System.out.println(fighter.getName() + " has " + fighter.getCurrentHp() + " HP");
		try {
			fighter.attack();
		} catch (NotEnoughActionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fighter.getName() + " attacks " + zombie.getName() + ".");
		System.out.println(zombie.getName() + " has " + zombie.getCurrentHp() + " HP");
		try {
			zombie.attack();
		} catch (NotEnoughActionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(zombie.getName() + " attacks " + fighter.getName() + ".");
			  System.out.println(fighter.getName() + " has " + fighter.getCurrentHp() + " HP");
		*/
	  
	//  Map map = new Map(15, 15);
	  //Fighter playFighter = new Fighter("Big Chungs", 100, 20, 5);
	  //map.setCell(2, 2, new EmptyCell());

	  //System.out.println("Hero is at row " + map.getRow(playFighter) + ", column " + map.GetCol(playFighter));

	 
	  //try {
		//playFighter.move(Direction.DOWN);
	//} catch (NotEnoughActionsException e) {
		// TODO Auto-generated catch block
	//	e.printStackTrace();
	//} catch (MovementException e) {
		// TODO Auto-generated catch block
	//	e.printStackTrace();
	//}
	  

	  //map.updateMap(playFighter);
	  //map.SetPos(map.getRow(playFighter), map.GetCol(playFighter), new CharacterCell(playFighter));

	  //System.out.println("Hero moved to row " + map.getRow(playFighter) + ", column " + map.GetCol(playFighter));	 
  }	  
}
