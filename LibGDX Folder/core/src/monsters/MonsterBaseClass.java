package monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MonsterBaseClass extends Rectangle {

	double movementSpeed;
	int attackPower;
	int goldSteal;
	Texture sprite;
	int movementDelay;
	int remainingHP;

	public MonsterBaseClass(float movementSpeed, int attackPower, int goldSteal, int movementDelay) {

		// Stats
		super();
		this.movementSpeed = movementSpeed;
		this.attackPower = attackPower;
		this.goldSteal = goldSteal;
		this.movementDelay = movementDelay;
		this.movementDelay -= (int) Math.random() * 10;
		System.out.println(this.movementDelay);

		// Sprite
		sprite = new Texture(Gdx.files.internal("puckDarkBlue.png"));
	}

	public MonsterBaseClass() {
		sprite = new Texture(Gdx.files.internal("puckDarkBlue.png"));

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

	public void setRemainingHP(int remainingHP) {
		this.remainingHP = remainingHP;
	}

}
