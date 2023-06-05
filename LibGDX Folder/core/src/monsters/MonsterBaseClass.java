package monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import levels.LevelManager;

public class MonsterBaseClass extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LevelManager levelManager;
	
	private boolean treasureDropBool = false;

	double movementSpeed;
	int attackPower;
	int goldSteal;
	Texture sprite;
	int movementDelay;
	int remainingHP;
	int weight;
	
	int pointsGiven;

	public MonsterBaseClass(LevelManager levelManager) {
		sprite = new Texture(Gdx.files.internal("puckDarkBlue.png"));
		this.levelManager = levelManager;

	}

	void varyMovementDelay() {
		this.movementDelay += (Math.random()*6-3);
	}
	
	void varyMovementSpeed() {
		movementSpeed += ((Math.random()+2)-1);
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getGoldSteal() {
		return goldSteal;
	}

	public void setGoldSteal(int goldSteal) {
		this.goldSteal = goldSteal;
	}

	public Texture getSprite() {
		return sprite;
	}

	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}

	public int getMovementDelay() {
		return movementDelay;
	}

	public void setMovementDelay(int movementDelay) {
		this.movementDelay = movementDelay;
	}

	public int getRemainingHP() {
		return remainingHP;
	}
	
	

	public int getPointsGiven() {
		return pointsGiven;
	}

	public void setPointsGiven(int pointsGiven) {
		this.pointsGiven = pointsGiven;
	}

	public void setRemainingHP(int remainingHP) {
		this.remainingHP = remainingHP;
	}

	public boolean getTreasureDropBool() {
		return treasureDropBool;
	}

	public void setTreasureDropBool(boolean treasureDropBool) {
		this.treasureDropBool = treasureDropBool;
	}

	
}
