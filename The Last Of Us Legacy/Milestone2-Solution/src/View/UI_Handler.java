package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.CubicCurve2D;
import java.io.IOException;
import java.nio.MappedByteBuffer;

import javax.print.attribute.standard.Media;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.world.CharacterCell;
import model.world.CollectibleCell;

public class UI_Handler extends JFrame implements ActionListener {
  JButton attacButton = new JButton("Attack");
  JButton upButton = new JButton("UP");
  JButton downButton = new JButton("DOWN");
  JButton rightButton = new JButton("RIGHT");
  JButton leftButton = new JButton("LEFT");
  JButton cureButton = new JButton("Cure");
  JButton useSpecialButton = new JButton("Use special");
  JButton endTurnButton = new JButton("EndTurn");
  boolean GameHasStarted;
  JRadioButton[] HeroRadios;	
  Hero selectedHero;	
  CharacterCell selectCell;
  Zombie selectedZombie;
  JButton[][] MapButtons = new JButton[15][15];
  JButton startGameButton = new JButton("Start Game!");
  JPanel mapPanel;
  JPanel heroPanel = new JPanel();
  JPanel ControlPanel;
  JPanel backgroundPanel = new JPanel();
  ButtonGroup heroButtonGroup = new ButtonGroup();
  ImageIcon imageIcon = new ImageIcon("PlayerLegacy.png");  
	
public UI_Handler() {
 Game game = new Game();
  
 try {
	game.loadHeroes("Heroes.csv");
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.Y_AXIS));
 getContentPane().add(heroPanel, BorderLayout.CENTER);
 getContentPane().add(startGameButton,BorderLayout.SOUTH);
 startGameButton.addActionListener(this);
 
 
 
 
 
 
 
 
 
 

 
 HeroRadios = new JRadioButton[game.availableHeroes.size()];
 for(int i = 0; i< HeroRadios.length;i++) {
	 HeroRadios[i] = new JRadioButton(game.availableHeroes.get(i).getName());
	 
	 HeroRadios[i].addActionListener(this);
	 heroButtonGroup.add(HeroRadios[i]);
	 heroPanel.add(HeroRadios[i]);
 }
	
 
 
 
 
 this.setSize(1000,600);
 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
 this.setVisible(true);
}
 
@Override
public void actionPerformed(ActionEvent e) {
 
 
SelectionLogic(e);
if(e.getSource() == startGameButton) {
	StartGame();
	GameHasStarted = true;
	return;
}	
getHeroActionEvents(e);
getHeroMoveHandler(e);

if(e.getSource() == attacButton) {
	try {
		selectedHero.setTarget(selectedZombie);
		selectedHero.attack();
		UpdateMap();
	} catch (NotEnoughActionsException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InvalidTargetException e1) {
		JOptionPane.showMessageDialog(this,"Choose the attack target!");
		e1.printStackTrace();
	}
	 
}
if(e.getSource() == cureButton) {
	selectedHero.setTarget(selectedHero);
	try {
		selectedHero.cure();
	} catch (NoAvailableResourcesException | InvalidTargetException | NotEnoughActionsException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

if(e.getSource() == useSpecialButton) {
	 
	try {
		selectedHero.setTarget(selectedHero);
		selectedHero.useSpecial();
		UpdateMap();
	} catch (NoAvailableResourcesException | InvalidTargetException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 
}
if(e.getSource() ==endTurnButton) {
	try {
		Game.endTurn();
		UpdateMap();
	} catch (NotEnoughActionsException | InvalidTargetException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
 
}

if(Game.checkWin()) {
	JOptionPane.showMessageDialog(this,"You won");
}
else if(Game.checkGameOver() && GameHasStarted == true) {
	JOptionPane.showMessageDialog(this,"you lost ");
}
 
}

 

private void SelectionLogic(ActionEvent e) {
    Object source = e.getSource();

    for (int i = 0; i < MapButtons.length; i++) {
        for (int j = 0; j < MapButtons.length; j++) {
            if (source == MapButtons[i][j]) {
                selectCell = (CharacterCell) Game.map[i][j];
                if (selectCell.getCharacter() instanceof Hero) {
                    selectedHero = (Hero) selectCell.getCharacter();
                    String playerStats = GetPlayerStats(selectedHero);
                    showPlayerStatsDialog(playerStats);
                } else if (selectCell.getCharacter() instanceof Zombie) {
                    selectedZombie = (Zombie) selectCell.getCharacter();
                    String zombieStats = GetZombieStats(selectedZombie);
                    showZombieSstatsDialog(zombieStats);
                }
                break;
            }
        }
    }
}


private void getHeroActionEvents(ActionEvent e) {
	for (int i = 0; i < HeroRadios.length; i++) {
	if(e.getSource() == HeroRadios[i]) {
		  String playerStats = GetPlayerStats(Game.availableHeroes.get(i));
		  
		    selectedHero = Game.availableHeroes.get(i);
		    
		    showPlayerStatsDialog(playerStats);
	}	
	
 
}
}
private void getHeroMoveHandler(ActionEvent e) {
	 
		if(e.getSource() ==  upButton) {
			try {
				selectedHero.move(Direction.UP);
			} catch (MovementException | NotEnoughActionsException e1) {
				JOptionPane.showMessageDialog(this,"Invaild movement");
				e1.printStackTrace();
			}
			UpdateMap();
		}
	if(e.getSource() ==  downButton) {
		try {
			selectedHero.move(Direction.DOWN);
		} catch (MovementException | NotEnoughActionsException e1) {
			JOptionPane.showMessageDialog(this,"Invaild movement");
			e1.printStackTrace();
		}
		UpdateMap();
	}
	if(e.getSource() ==  rightButton) {
		try {
			selectedHero.move(Direction.RIGHT);
		} catch (MovementException | NotEnoughActionsException e1) {
			JOptionPane.showMessageDialog(this,"Invaild movement");
			e1.printStackTrace();
		}
		UpdateMap();
	}
	if(e.getSource() ==  leftButton) {
		try {
			selectedHero.move(Direction.LEFT);
		} catch (MovementException | NotEnoughActionsException e1) {
			JOptionPane.showMessageDialog(this,"Invaild movement");
			e1.printStackTrace();
		}
		UpdateMap();
	}
}

public String GetPlayerStats(Hero h) {
	   StringBuilder statString = new StringBuilder();

	    if (h instanceof Medic) {
	        statString.append("Medic\n");
	    } else if (h instanceof Fighter) {
	        statString.append("Fighter\n");
	    } else if (h instanceof Explorer) {
	        statString.append("Explorer\n");
	    }

	    statString.append("Name: ").append(h.getName()).append("\n")
	            .append("HP: ").append(h.getCurrentHp()).append("\n")
	            .append("Attack Damage: ").append(h.getAttackDmg()).append("\n")
	            .append("Max Actions: ").append(h.getMaxActions()).append("\n")
	            .append("ActionsAvaliable: ").append(h.getActionsAvailable()).append("\n")
	            .append("Vaccines: ").append(h.getVaccineInventory().size()).append("\n")
	            .append("Supplies: ").append(h.getSupplyInventory().size());

	    return statString.toString();
}
public String GetZombieStats(Zombie z) {
	   StringBuilder statString = new StringBuilder();
	    statString.append("Name: ").append(z.getName()).append("\n")
	    .append("HP: ").append(z.getCurrentHp()).append("\n")
	   .append("Attack Damage: ").append(z.getAttackDmg()).append("\n");
	    return statString.toString();
}
public void showZombieSstatsDialog(String playerStats) {
    JTextArea textArea = new JTextArea(playerStats);
    textArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(300, 200));

 
 

    JOptionPane.showMessageDialog(this, scrollPane, "Player Stats", JOptionPane.INFORMATION_MESSAGE);
}
public void showPlayerStatsDialog(String playerStats) {
    JTextArea textArea = new JTextArea(playerStats);
    textArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setPreferredSize(new Dimension(300, 200));

 
    String imagePath = getImagePath(selectedHero);
 
    ImageIcon icon = new ImageIcon(imagePath);

 
    Image resizedImage = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
    icon = new ImageIcon(resizedImage);

    JOptionPane.showMessageDialog(this, scrollPane, "Player Stats", JOptionPane.INFORMATION_MESSAGE, icon);
}
 

private String getImagePath(Hero hero) {
    if (hero instanceof Medic) {
        System.out.println("Medic image path");
        return "Medic.png";
    } else if (hero instanceof Fighter) {
        return "Fighter.png";
    } else if (hero instanceof Explorer) {
        return "Explorer.png";
    }
    else
     return "Def.png";
}


void StartGame() {
	Game.startGame(selectedHero);
	
	heroPanel.setVisible(false);
	startGameButton.setVisible(false);
	
	this.getContentPane().setLayout(null);
	mapPanel = new JPanel();
	mapPanel.setLayout(new GridLayout(15,15));
    mapPanel.setBounds(200,10,600,600);	
	this.getContentPane().add(mapPanel);
	
	
	ControlPanel = new JPanel();
	ControlPanel.setLayout(new GridLayout(6,1));
	ControlPanel.setBounds(10,10,150,200);	
	this.getContentPane().add(ControlPanel);
	 
	
	attacButton .addActionListener(this);
    endTurnButton.addActionListener(this);
	upButton.addActionListener(this);
	leftButton.addActionListener(this);
	rightButton.addActionListener(this);
	downButton.addActionListener(this);
	
	
	
	
	
	ControlPanel.add(attacButton);
	
	ControlPanel.add(upButton);
	ControlPanel.add(downButton);
	ControlPanel.add(leftButton);
	ControlPanel.add(rightButton);
	ControlPanel.add(cureButton);
	ControlPanel.add(endTurnButton);
	ControlPanel.add(useSpecialButton);
	
	MapButtons = new JButton[15][15];
	for (int i = 0; i < MapButtons.length; i++) {
		for (int j = 0; j < MapButtons.length; j++) {
			MapButtons[MapButtons.length-1-i][j] = new JButton();
			MapButtons[MapButtons.length-1-i][j].addActionListener(this);
			mapPanel.add(MapButtons[MapButtons.length-1-i][j]);
		}
	}
	UpdateMap();
}



void UpdateMap() {
	for (int i = 0; i < MapButtons.length; i++) {
		for (int j = 0; j < MapButtons.length; j++) {
		 MapButtons[i][j].setIcon(null);
		 MapButtons[i][j].setBackground(Color.YELLOW);
		 
		 if(Game.map[i][j].isVisible() == false) {
			 if (!Game.map[i][j].isVisible()) {
				    ImageIcon imageIcon = new ImageIcon("EmptyCells.png"); // Replace "path_to_your_image.jpg" with the actual path to your image file
				    Image image = imageIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH); // Replace buttonWidth and buttonHeight with the desired dimensions of the button
				    MapButtons[i][j].setIcon(new ImageIcon(image));
				}

		 }
		 
		 else if(Game.map[i][j] instanceof CharacterCell) {
			 CharacterCell cell = (CharacterCell) Game.map[i][j];
			 if(cell.getCharacter() == null) {
				 MapButtons[i][j].setBackground(Color.WHITE);
			 }else if(cell.getCharacter() instanceof Zombie)
				 MapButtons[i][j].setBackground(Color.GREEN); 
			 else if(cell.getCharacter() instanceof Hero)
				 
				 MapButtons[i][j].setBackground(Color.BLUE); 
			 
		 }
		 else if(Game.map[i][j] instanceof CollectibleCell) {
			 CollectibleCell cell=  (CollectibleCell) Game.map[i][j];
			 if(cell.getCollectible() instanceof Supply) {
				 MapButtons[i][j].setBackground(Color.MAGENTA);
			 }
		 }
	 
		 
		}
	}
}



public static void main(String[] args) {
	new UI_Handler();
}
 















}
