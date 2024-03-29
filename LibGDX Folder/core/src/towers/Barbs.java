package towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import levels.LevelManager;

public class Barbs extends BaseTowerClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Barbs(LevelManager levelManager) {
		super(levelManager);
		this.towerModelName = "BarbedWire";
		this.goldToMake = 6;
		this.stoneToMake = 2;
		this.monsterRemainsToMake = 0;
		this.hp = 50;
		this.monsterPushback = 0;
		this.monsterDamageTaken = 1;
		sprite = new Texture(Gdx.files.internal("barbs.png"));
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}
	
}
