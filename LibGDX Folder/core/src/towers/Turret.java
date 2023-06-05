package towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import levels.LevelManager;

public class Turret extends BaseTowerClass {

	// TODO - Implement this
//		barbs do passive damage and don't block
// 		basic tower slows progress but does no damage
//		TURRETS should damage a horizontal section of the board every tick
	
	// TODO Give it a method that spawns a projectile/laser every X frames
	// This will travel across the map, damage anything it hits and push back,
	// then despawn after hitting enemies X times or leaving the map 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Turret(LevelManager levelManager) {
		super(levelManager);
		this.towerModelName = "Turret";
		this.goldToMake = 15;
		this.monsterRemainsToMake = 0;
		this.hp = 40;
		this.monsterPushback = 10;
		this.monsterDamageTaken = 0;
		sprite = new Texture(Gdx.files.internal("castle.png"));
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}

}
