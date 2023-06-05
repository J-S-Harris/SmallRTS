package towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import levels.LevelManager;
import monsters.MonsterBaseClass;

public class BaseTowerClass extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LevelManager levelManager;
	
	Texture sprite;
	String towerModelName;
	int goldToMake; // Gold to create tower
	int stoneToMake; // Stone to create tower
	int monsterRemainsToMake; // Secondary resource needed to make
	int hp; // Dmg building can take before being destroyed
	int monsterPushback; // Monster pushed back by x when it attacks
	int monsterDamageTaken; // Generally 0, might be higher for barbed wire, etc

	public BaseTowerClass(LevelManager levelManager) {
		this.levelManager = levelManager;
		
		sprite = new Texture(Gdx.files.internal("base.png"));
		this.towerModelName = "BaseTowerClass";
		this.goldToMake = 0;
		this.monsterRemainsToMake = 0;
		this.hp = 20;
		this.monsterPushback = 20;
		this.monsterDamageTaken = 1;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}

	// Basic method called when this overlaps a monster
	public void monsterCollisionMethod(MonsterBaseClass monster) {

		// This tower gets damaged
		this.hp -= monster.getAttackPower();

		// Monster is damaged and repulsed
		monster.setRemainingHP(monster.getRemainingHP() - monsterDamageTaken);
		monster.x += monsterPushback;

	}

	public Texture getSprite() {
		return sprite;
	}

	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}

	public String getTowerModelName() {
		return towerModelName;
	}

	public void setTowerModelName(String towerModelName) {
		this.towerModelName = towerModelName;
	}

	public int getGoldToMake() {
		return goldToMake;
	}

	public void setGoldToMake(int goldToMake) {
		this.goldToMake = goldToMake;
	}

	public int getMonsterRemainsToMake() {
		return monsterRemainsToMake;
	}

	public void setMonsterRemainsToMake(int monsterRemainsToMake) {
		this.monsterRemainsToMake = monsterRemainsToMake;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMonsterPushback() {
		return monsterPushback;
	}

	public void setMonsterPushback(int monsterPushback) {
		this.monsterPushback = monsterPushback;
	}

	public int getMonsterDamageTaken() {
		return monsterDamageTaken;
	}

	public void setMonsterDamageTaken(int monsterDamageTaken) {
		this.monsterDamageTaken = monsterDamageTaken;
	}

	public int getStoneToMake() {
		return stoneToMake;
	}

	public void setStoneToMake(int stoneToMake) {
		this.stoneToMake = stoneToMake;
	}

}
