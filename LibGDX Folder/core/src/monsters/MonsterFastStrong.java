package monsters;

public class MonsterFastStrong extends MonsterBaseClass {

	public MonsterFastStrong(float movementSpeed, int attackPower, int goldSteal, int movementDelay) {
		super(movementSpeed, attackPower, goldSteal, movementDelay);
		// TODO Auto-generated constructor stub
	}

	public MonsterFastStrong() {
		super();

		this.movementSpeed = 10;
		this.attackPower = 2;
		this.goldSteal = 10;
		this.movementDelay = 30;
		this.remainingHP = 15;
		
	}

}
