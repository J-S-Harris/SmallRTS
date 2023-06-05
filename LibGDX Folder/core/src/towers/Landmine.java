package towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.smallrts.gameElements.PuckRootClass;

import levels.LevelManager;
import monsters.MonsterBaseClass;

public class Landmine extends BaseTowerClass {
	
	int expandedWidth = 200;
	int expandedHeight = 200;

	public Landmine(LevelManager levelManager) {
		super(levelManager);
		
		this.towerModelName = "Basic Tower";
		this.goldToMake = 0;
		this.stoneToMake = 0;
		this.monsterRemainsToMake = 0;
		this.hp = 999;
		this.monsterPushback = 0;
		this.monsterDamageTaken = 40;
//		sprite = new Texture(Gdx.files.internal("landmineUnexploded.png"));
		sprite = new Texture(Gdx.files.internal("castle.png"));
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}
	
	// TODO Give the landmine a sprite on ln24 and ln34
	
	@Override
	public void monsterCollisionMethod(MonsterBaseClass monster) {
		// this.sprite = new Texture(Gdx.files.internal("landmineExploded.png");
//		this.width = sprite.width;
//		this.height = sprite.height;
		this.width = expandedWidth;
		this.height = expandedHeight;
		this.x-= expandedWidth/2;
		this.y-= expandedHeight/2;
		
		for (MonsterBaseClass monster2 : levelManager.getEnemiesThisTurn()) {
			if (monster2.overlaps(this)) {
				monster2.setRemainingHP(monster2.getRemainingHP() - this.monsterDamageTaken);
			}
		}
		
		for (PuckRootClass puck : levelManager.getGameScreen().getTeam()) {
			if (puck.overlaps(this)) {
				puck.setHP(puck.getHP()-this.monsterDamageTaken);
			}
				
		}
		
		this.monsterDamageTaken = 0;
		this.hp = 0;
	}
	
}
