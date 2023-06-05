package droppedTreasure;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import monsters.MonsterBaseClass;

public class DroppedTreasure extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	int droppedGold;
	int droppedStone;
	int droppedPoints;
	
	Texture sprite = new Texture(Gdx.files.internal("chest16x16.png"));
	
	
	public DroppedTreasure(MonsterBaseClass monster) {
		droppedGold = (int) (Math.random()*monster.getWeight()+3);
		droppedStone = (int) (Math.random()*monster.getWeight()+3);
		droppedPoints = monster.getWeight()*3;
		
		this.x = monster.x;
		this.y = monster.y;
	}


	public int getDroppedGold() {
		return droppedGold;
	}


	public void setDroppedGold(int droppedGold) {
		this.droppedGold = droppedGold;
	}


	public int getDroppedStone() {
		return droppedStone;
	}


	public void setDroppedStone(int droppedStone) {
		this.droppedStone = droppedStone;
	}


	public int getDroppedPoints() {
		return droppedPoints;
	}


	public void setDroppedPoints(int droppedPoints) {
		this.droppedPoints = droppedPoints;
	}


	public Texture getSprite() {
		return sprite;
	}


	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}
	
}
