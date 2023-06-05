package monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import levels.LevelManager;

public class MonsterWeakSlow extends MonsterBaseClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MonsterWeakSlow(LevelManager levelManager) {
		super(levelManager);

		this.movementSpeed = 8 + levelManager.getCurrentLevel()/10;
		this.attackPower = 1;
		this.goldSteal = 5;
		this.movementDelay = 60;
		this.remainingHP = 10;
		this.weight = 8;
		this.sprite = new Texture(Gdx.files.internal("enemySpriteBlue-1.png"));
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.pointsGiven = 10;
		
		varyMovementDelay();
		varyMovementSpeed();
	}
}