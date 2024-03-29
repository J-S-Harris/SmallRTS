package monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import levels.LevelManager;

public class MonsterFastStrong extends MonsterBaseClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MonsterFastStrong(LevelManager levelManager) {
		super(levelManager);

		this.movementSpeed = 4 + levelManager.getCurrentLevel()/10;
		this.attackPower = 2;
		this.goldSteal = 10;
		this.movementDelay = 15;
		this.remainingHP = 15;
		this.weight = 10;
		this.sprite = new Texture(Gdx.files.internal("enemySpritePurple-1.png"));
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.pointsGiven = 20;
		
		varyMovementDelay();
		varyMovementSpeed();
	}

}
