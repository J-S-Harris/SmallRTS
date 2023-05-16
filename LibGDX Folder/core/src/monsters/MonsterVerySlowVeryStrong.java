package monsters;

public class MonsterVerySlowVeryStrong extends MonsterBaseClass {

	public MonsterVerySlowVeryStrong(float movementSpeed, int attackPower, int goldSteal, int movementDelay) {
		super(movementSpeed, attackPower, goldSteal, movementDelay);
		// TODO Auto-generated constructor stub
	}

	public MonsterVerySlowVeryStrong() {
		super();

		this.movementSpeed = 10;
		this.attackPower = 5;
		this.goldSteal = 30;
		this.movementDelay = 120;
		this.remainingHP = 100;
		
	}

}
