package monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import levels.LevelManager;

public class MonsterVerySlowVeryStrong extends MonsterBaseClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MonsterVerySlowVeryStrong(LevelManager levelManager) {
		super(levelManager);

		this.movementSpeed = 6 + levelManager.getCurrentLevel()/10;
		this.attackPower = 8;
		this.goldSteal = 30;
		this.movementDelay = 120;
		this.remainingHP = 100;
		this.weight = 15;
		this.sprite = new Texture(Gdx.files.internal("enemySpriteRedLarge-1"));
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.pointsGiven = 100;
		
		varyMovementDelay();
		varyMovementSpeed();
	}

}
