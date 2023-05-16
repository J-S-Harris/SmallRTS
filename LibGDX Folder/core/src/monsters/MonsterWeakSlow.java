package monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MonsterWeakSlow extends MonsterBaseClass {

	public MonsterWeakSlow(float movementSpeed, int attackPower, int goldSteal, int movementDelay) {
		super(movementSpeed, attackPower, goldSteal, movementDelay);
		// TODO Auto-generated constructor stub
	}

	public MonsterWeakSlow() {
		super();

		this.movementSpeed = 10;
		this.attackPower = 1;
		this.goldSteal = 5;
		this.movementDelay = 60;
		this.remainingHP = 10;
		
	}
}
