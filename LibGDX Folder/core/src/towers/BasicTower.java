package towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import levels.LevelManager;

public class BasicTower extends BaseTowerClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public BasicTower(LevelManager levelManager) {
		super(levelManager);
		this.towerModelName = "Basic Tower";
		this.goldToMake = 10;
		this.stoneToMake = 15;
		this.monsterRemainsToMake = 0;
		this.hp = 100;
		this.monsterPushback = 10;
		this.monsterDamageTaken = 0;
		sprite = new Texture(Gdx.files.internal("castle.png"));
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}

}
