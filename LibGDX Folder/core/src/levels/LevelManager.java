package levels;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import monsters.MonsterBaseClass;
import monsters.MonsterFastStrong;
import monsters.MonsterWeakSlow;

public class LevelManager {

	// Overall class variables
	ArrayList<MonsterBaseClass> enemiesThisTurn;
	int currentLevel;
	int movementCounter;
	SpriteBatch batch;
	int nextTurnDelay;
	int nextTurnDelayValue;

	// Instance variables for generating monsters;
	int quantity;
	int count;
	double startingY;

	public LevelManager(SpriteBatch batch) {
		this.batch = batch;
		enemiesThisTurn = new ArrayList<MonsterBaseClass>();
		currentLevel = 0;

		startNewTurn();

		nextTurnDelay = 0;
		nextTurnDelayValue = 30;

	}

	public void startNewTurn() {
		movementCounter = 0;
		currentLevel++;
		enemiesThisTurn.clear();

		populateVerySlowVeryStrong();
		populateWeakSlow();
		populateFastStrong();

		setStartingPositions();

	}

	public void runTurn() {

		if (enemiesThisTurn.size() == 0) {
			startNewTurn();
		}

		if (nextTurnDelay == 0) {

			checkForKills();

			moveEachEnemy();

			checkIfTurnOver();

		} else {
			nextTurnDelay--;
		}

	}

	private void checkIfTurnOver() {
		if (enemiesThisTurn.size() == 0) {
			nextTurnDelay = nextTurnDelayValue;
			System.out.println("Turn over!");
		}

	}

	private void checkForKills() {
		
		for (Iterator<MonsterBaseClass> it = enemiesThisTurn.iterator(); it.hasNext();) {
			MonsterBaseClass monster = it.next();
			if (monster.getRemainingHP() < 1) {
				it.remove();
			}

			if (monster.getRemainingHP() < 1) {
				enemiesThisTurn.remove(monster);
			}

		}
	}

	private void moveEachEnemy() {

		if (enemiesThisTurn.size() > 0) {
			if (movementCounter >= 360) {
				movementCounter = 0;
			}
			movementCounter++;

			for (MonsterBaseClass monster : enemiesThisTurn) {
				if (movementCounter % monster.getMovementDelay() == 0) {
					monster.x -= monster.getMovementSpeed();
				}
				if (monster.getRemainingHP() > 0) {
					batch.draw(monster.getSprite(), monster.x, monster.y);
				}
			}

		}

	}

	private void setStartingPositions() {

		// How randomise this array so that they don't come out in a set order?
		for (MonsterBaseClass monster : enemiesThisTurn) {

			monster.x = 725;

			startingY = Math.random() * 3;
			if (startingY > 2) {
				monster.y = 300;
			} else if (startingY > 1) {
				monster.y = 200;
			} else {
				monster.y = 100;
			}

			for (MonsterBaseClass overlapMonster : enemiesThisTurn) {
				if (monster.x == overlapMonster.x && monster.y == overlapMonster.y && monster != overlapMonster) {
					monster.x += 40;
				}
			}
			System.out.println("MONSTER X: " + monster.x);

		}

	}

	public void before() {

		quantity = currentLevel * currentLevel + 1;
		count = 0;
	}

	private void populateWeakSlow() {
		before();

		while (count < quantity) {
			count++;
			enemiesThisTurn.add(new MonsterWeakSlow());
		}

	}

	private void populateFastStrong() {

		before();

		while (count < quantity) {
			count++;
			if (count % 2 == 0) {
				enemiesThisTurn.add(new MonsterFastStrong());
			}
		}

	}

	private void populateVerySlowVeryStrong() {
		before();

		while (count < quantity) {
			count++;
			if (count % 3 == 0) {
				enemiesThisTurn.add(new MonsterFastStrong());
			}
		}

	}

	public ArrayList<MonsterBaseClass> getEnemiesThisTurn() {
		return enemiesThisTurn;
	}

	public void setEnemiesThisTurn(ArrayList<MonsterBaseClass> enemiesThisTurn) {
		this.enemiesThisTurn = enemiesThisTurn;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getMovementCounter() {
		return movementCounter;
	}

	public void setMovementCounter(int movementCounter) {
		this.movementCounter = movementCounter;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public int getNextTurnDelay() {
		return nextTurnDelay;
	}

	public void setNextTurnDelay(int nextTurnDelay) {
		this.nextTurnDelay = nextTurnDelay;
	}

	public int getNextTurnDelayValue() {
		return nextTurnDelayValue;
	}

	public void setNextTurnDelayValue(int nextTurnDelayValue) {
		this.nextTurnDelayValue = nextTurnDelayValue;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getStartingY() {
		return startingY;
	}

	public void setStartingY(double startingY) {
		this.startingY = startingY;
	}

}