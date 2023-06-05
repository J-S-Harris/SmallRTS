package utilityClasses;

import java.util.ArrayList;

import com.mygdx.smallrts.gameElements.PuckRootClass;

import screensEtc.GameScreen;
import screensEtc.MenuScreen;
import towers.Barbs;
import towers.BasicTower;
import towers.Landmine;

public class MenuScreenTextManager {

	MenuScreen menuScreen;
	GameScreen gameScreen;
	ArrayList<String> leftText;
	ArrayList<String> rightText;
	
	PuckRootClass puckRef = new PuckRootClass(1, null);
	BasicTower towerRef = new BasicTower(null);
	Barbs barbsRef = new Barbs(null);
	Landmine landmineRef = new Landmine(null);
	

	public MenuScreenTextManager(MenuScreen menuScreen) {

		this.menuScreen = menuScreen;
		gameScreen = menuScreen.getGameScreen();
		gatherText();

	}

	public void gatherText() {
		createLeftSideText();
		createRightSideText();

	}

	public void renderText() {
		int count = 0;
		while (count < leftText.size()) {
			if ((!leftText.get(count).contains("score:"))) {
				menuScreen.getFont().draw(menuScreen.getBatch(), leftText.get(count), menuScreen.getlInsetX(),
						menuScreen.getGameScreen().getBackground().getHeight() - (50 * count) + 30);
			} else {
				menuScreen.getFontHighScore().draw(menuScreen.getBatch(), leftText.get(count), menuScreen.getlInsetX(),
						menuScreen.getGameScreen().getBackground().getHeight() - (50 * count) + 30);

			}
			count++;
		}
		count = 0;
		while (count < rightText.size()) {
			menuScreen.getFont().draw(menuScreen.getBatch(), rightText.get(count), menuScreen.getrInsetX(),
					menuScreen.getGameScreen().getBackground().getHeight() - (50 * count) + 30);
			count++;
		}

	}

	public void createLeftSideText() {

		leftText = new ArrayList<String>();
		leftText.add("CASTLE DEFENCE");
		leftText.add("High score: " + Integer.toString(menuScreen.getHighScore()));
		leftText.add("The castle is under attack!");
		leftText.add("Control your units to gather gold and stone, build towers,\nor attack enemies directly.");
		leftText.add("You start with 3 units with limited HP,\nand have a limited number of citizens to protect.");
		leftText.add("Citizens will die when a monster reaches the castle.");
		leftText.add("The stronger the monster, the more citizens you lose!");
		leftText.add("Help the citizens survive as long as you can,\nand go for the high score.");
		leftText.add("Good luck!");
		leftText.add("PRESS SPACE TO START");

	}

	public void createRightSideText() {

		rightText = new ArrayList<String>();
		rightText.add("Controls");
		rightText.add("TO DIRECTLY MOVE A UNIT:");
		rightText.add("TAP its number on the number pad,\nand then hold WASD to move it.");
		rightText.add("TO ISSUE A CONTINUOUS ORDER TO A UNIT:\nHOLD the letter, then tap its number on the number pad:");
		rightText.add("Q - Harvest gold\nE - Harvest stone");
		rightText.add("R - Continuously attack enemies (deals small damage each hit;\nunit takes damage too!)");
		rightText.add("T - build simple tower ("+towerRef.getGoldToMake()+" gold, "+towerRef.getStoneToMake()+ " stone; blocks enemies.\nsturdy, but deals no damage)");
		rightText.add("B - build damaging barbs ("+barbsRef.getGoldToMake()+" gold, "+barbsRef.getStoneToMake()+" stone; hurts enemies.\nbreaks easily and doesn't block enemies)");
		rightText.add("L - build a landmine("+landmineRef.getGoldToMake()+" gold, "+landmineRef.getStoneToMake()+" stone; Explodes, large AoE damage.\nExpensive; last-ditch weapon. WARNING: Will kill your units!)");
		rightText.add("U - Upgrades a unit (increases HP + speed; "+gameScreen.getCostToUpgradeUnit()+" gold)");
		rightText.add("0 - Buy new unit ("+menuScreen.getGameScreen().getNewUnitGoldCost()+" gold; max of 9)");
		rightText.add("1/2/3 - Move to low/medium/high defensive position");
		rightText.add("Escape - Removes the unit from list of units being directly moved");

	}

	public ArrayList<String> getLeftText() {
		return leftText;
	}

	public void setLeftText(ArrayList<String> leftText) {
		this.leftText = leftText;
	}

	public ArrayList<String> getRightText() {
		return rightText;
	}

	public void setRightText(ArrayList<String> rightText) {
		this.rightText = rightText;
	}

}
