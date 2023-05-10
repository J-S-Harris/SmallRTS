package com.mygdx.smallrts.gameElements;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {

	SpriteBatch batch;
	BitmapFont font;
	FitViewport viewport;
	OrthographicCamera camera;

	int pointsLeftTeam;
	int pointsRightTeam;

	Screen gameScreen;

	Texture background;

	// For tracking current inputs
	String currentInputsLeft;
	String currentInputsRight;

	// Game elements
	// TODO - Refactor these both so I have a single ArrayList of (arraylist of?)
	// all pucks to act on
	ArrayList<PuckRootClass> leftTeam;
	PuckRootClass puckLeft1;
	PuckRootClass puckLeft2;
	PuckRootClass puckLeft3;
	BaseRootClass baseLeft;
	MineRootClass mineLeft;

	ArrayList<PuckRootClass> rightTeam;
	PuckRootClass puckRight1;
	PuckRootClass puckRight2;
	PuckRootClass puckRight3;
	BaseRootClass baseRight;
	MineRootClass mineRight;

	// Movement Speed
	int movementSpeed;

	// Gold gained by each team
	int leftTeamGold;
	int rightTeamGold;

	// Points earned by each team so far
	int leftTeamPoints;
	int rightTeamPoints;

	// Left team sprites
	Texture leftTeamNoGold = new Texture(Gdx.files.internal("puckLightBlue.png"));
	Texture leftTeamWithGold = new Texture(Gdx.files.internal("puckLightBlue-Gold.png"));

	public GameScreen() {

		setUpThings();
		createAllElements();

		assignStartingPositions(leftTeam);
		assignStartingPositions(rightTeam);

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 1, 0, 1);

//		viewport.apply();

		check123Pressed(); // Check if 1/2/3 are pressed to see which units will be given orders this frame
		assignPuckAction(); // Update their current behaviour
		performActions();

		batch.begin();

		// After all actions are performed, draw updated positions, points, etc
		drawGameUnits();

		// Display left team's gold + points
		font.draw(batch, "POINTS\n", 20, 382);
		font.draw(batch, Integer.toString(leftTeamPoints), 20, 365);

		font.draw(batch, "GOLD\n", 20, 45);
		font.draw(batch, Integer.toString(leftTeamGold), 20, 28);

		// Display right team's gold + points
		font.draw(batch, "POINTS\n", 625, 382);
		font.draw(batch, Integer.toString(rightTeamPoints), 660, 365);

		font.draw(batch, "GOLD\n", 640, 45);
		font.draw(batch, Integer.toString(rightTeamGold), 660, 28);

		batch.end();

	}

	private void performActions() {

		for (PuckRootClass puck : leftTeam) {
			performActionsLeft(puck);
		}

		for (PuckRootClass puck : rightTeam) {
			performActionsRight(puck);
		}

	}

	private void performActionsLeft(PuckRootClass puck) {

		if (puck.getCurrentAction().equals("FollowInputs")) {
			followInputs(puck);
		} else if (puck.getCurrentAction().equals("MineGold")) {
			mineGold(puck);
		} else if (puck.getCurrentAction().equals("AttackEnemyBase")) {
			attackEnemyBase(puck);
		} else if (puck.getCurrentAction().equals("Move300")) {
			move300(puck);
		} else if (puck.getCurrentAction().equals("Move200")) {
			move200(puck);
		} else if (puck.getCurrentAction().equals("Move100")) {
			move100(puck);
		}

	}

	private void attackEnemyBase(PuckRootClass puck) {

		// If not overlapping mine OR base: travel there
		if (puck.getGoldCurrentlyHeld() < 1 && !puck.overlaps(baseRight)) {
			puck.targetX = (int) (baseRight.x + baseRight.width / 2);
			puck.targetY = (int) (baseRight.y + baseRight.height / 2);
		} else if (puck.getGoldCurrentlyHeld() > 0 && !puck.overlaps(baseLeft)) {
			puck.targetX = (int) (baseLeft.x + baseLeft.width / 2);
			puck.targetY = (int) (baseLeft.y + baseLeft.height / 2);

			// If you no gold at the mine, or have gold at the base: mine/deposit it
		} else if ((puck.getGoldCurrentlyHeld() < 1 && puck.overlaps(baseRight))) {
			puck.setGoldCurrentlyHeld(puck.getGoldCurrentlyHeld() + 5);
			rightTeamGold -= 5;
			puck.setBackground(leftTeamWithGold);

		} else if ((puck.getGoldCurrentlyHeld() > 0 && puck.overlaps(baseLeft))) {

			if (leftTeamGold < 100) {
				leftTeamGold += 5;
			}
			puck.setGoldCurrentlyHeld(puck.getGoldCurrentlyHeld() - 5);
			puck.setBackground(leftTeamNoGold);

			// If you have no gold at the base, or have gold at the mine: travel to the
			// other one
		} else if ((puck.getGoldCurrentlyHeld() < 1 && puck.overlaps(baseLeft))) {
			puck.targetX = (int) (baseRight.x + baseRight.width / 2);
			puck.targetY = (int) (baseRight.y + baseRight.height / 2);

		} else if ((puck.getGoldCurrentlyHeld() > 0 && puck.overlaps(baseRight))) {
			puck.targetX = (int) (baseLeft.x + baseLeft.width / 2);
			puck.targetY = (int) (baseLeft.y + baseLeft.height / 2);
		}

		homeIn(puck);

	}

	private void move100(PuckRootClass puck) {

		if (puck.x < 290 || puck.x > 305) {
			puck.setTargetX(300);
		} else {
			puck.setTargetX(0);
		}

		if (puck.y < 90 || puck.y > 105) {
			puck.setTargetY(100);
		} else {
			puck.setTargetY(0);
		}

		homeIn(puck);

	}

	private void move200(PuckRootClass puck) {

		if (puck.x < 290 || puck.x > 305) {
			puck.setTargetX(300);
		} else {
			puck.setTargetX(0);
		}

		if (puck.y < 190 || puck.y > 205) {
			puck.setTargetY(200);
		} else {
			puck.setTargetY(0);
		}

		homeIn(puck);

	}

	private void move300(PuckRootClass puck) {

		if (puck.x < 290 || puck.x > 305) {
			puck.setTargetX(300);
		} else {
			puck.setTargetX(0);
		}

		if (puck.y < 290 || puck.y > 305) {
			puck.setTargetY(300);
		} else {
			puck.setTargetY(0);
		}

		homeIn(puck);
	}

	private void mineGold(PuckRootClass puck) {

		// TODO How make this less resource-intensive?

		// If not overlapping mine OR base: travel there
		if (puck.getGoldCurrentlyHeld() < 1 && !puck.overlaps(mineLeft)) {
			puck.targetX = (int) (mineLeft.x + mineLeft.width / 2);
			puck.targetY = (int) (mineLeft.y + mineLeft.height / 2);
		} else if (puck.getGoldCurrentlyHeld() > 0 && !puck.overlaps(baseLeft)) {
			puck.targetX = (int) (baseLeft.x + baseLeft.width / 2);
			puck.targetY = (int) (baseLeft.y + baseLeft.height / 2);

			// If you no gold at the mine, or have gold at the base: mine/deposit it
		} else if ((puck.getGoldCurrentlyHeld() < 1 && puck.overlaps(mineLeft))) {
			puck.setGoldCurrentlyHeld(puck.getGoldCurrentlyHeld() + 1);
			puck.setBackground(leftTeamWithGold);
		} else if ((puck.getGoldCurrentlyHeld() > 0 && puck.overlaps(baseLeft))) {

			if (leftTeamGold < 100) {
				leftTeamGold++;
			}
			puck.setGoldCurrentlyHeld(puck.getGoldCurrentlyHeld() - 1);
			puck.setBackground(leftTeamNoGold);

			// If you have no gold at the base, or have gold at the mine: travel to the
			// other one
		} else if ((puck.getGoldCurrentlyHeld() < 1 && puck.overlaps(baseLeft))) {
			puck.targetX = (int) (mineLeft.x + mineLeft.width / 2);
			puck.targetY = (int) (mineLeft.y + mineLeft.height / 2);
		} else if ((puck.getGoldCurrentlyHeld() > 0 && puck.overlaps(mineLeft))) {
			puck.targetX = (int) (baseLeft.x + baseLeft.width / 2);
			puck.targetY = (int) (baseLeft.y + baseLeft.height / 2);
		}
		homeIn(puck);

	}

	private void homeIn(PuckRootClass puck) {

		if (puck.getTargetX() != 0) {

			if (puck.x < puck.targetX) {
				puck.x += movementSpeed;
			} else {
				puck.x -= movementSpeed;
			}
		}

		if (puck.getTargetY() != 0) {
			if (puck.y < puck.targetY) {
				puck.y += movementSpeed;
			} else {
				puck.y -= movementSpeed;
			}

		}

	}

	private void followInputs(PuckRootClass puck) {

		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			puck.y -= movementSpeed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			puck.y += movementSpeed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			puck.x -= movementSpeed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			puck.x += movementSpeed;
		}

		puck.setCurrentAction("");

	}

	private void performActionsRight(PuckRootClass puck) {
		// TODO Auto-generated method stub

	}

	private void assignPuckAction() {
		// Puck actions:
//		 FollowInputs = move according to player input
//		MineGold = travel between mine and base, collecting resources
//		AttackEnemyMine = move towards enemy mine
//		Move300 = move to y=300 (High)
//		Move200 = move to y=200 (Mid)
//		Move100 = move to y=100 (Low)

		for (PuckRootClass puck : leftTeam) {

			if (currentInputsLeft.equals(Integer.toString(puck.getNumber()))) {

				// TODO - big to do; make EVERYTHING team-agnostic,
					// then just pass in team name.
					// Remove code bloat.
				
				// Harvest gold from OWN MINE(+1) or ENEMY BASE(+5/-5)
				if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
					puck.setCurrentAction("MineGold");
				} else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
					puck.setCurrentAction("AttackEnemyBase");

					// Move puck to pre-defined locations
					// TODO - condense to one method
				} else if (Gdx.input.isKeyPressed(Input.Keys.R)) {
					puck.setCurrentAction("Move300");
				} else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
					puck.setCurrentAction("Move200");
				} else if (Gdx.input.isKeyPressed(Input.Keys.C)) {
					puck.setCurrentAction("Move100");

					// If nothing else pressed, puck will follow user inputs:
				} else {
					puck.setCurrentAction("FollowInputs");
				}

			}

		}
	}

	private void check123Pressed() {

		currentInputsLeft = "";
		currentInputsRight = "";

		// TODO Get these to refer to a enum of input + appropriate method for each team

		// For left player
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			currentInputsLeft = "1";
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			currentInputsLeft = "2";
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
			currentInputsLeft = "3";
		}

		// For right player
		if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)) {
			currentInputsRight = "1";
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
			currentInputsRight = "2";
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)) {
			currentInputsRight = "3";
		}

	}

	private void drawGameUnits() {

		batch.draw(background, 0, 0);

		batch.draw(baseLeft.getBackground(), baseLeft.x, baseLeft.y);
		batch.draw(baseRight.getBackground(), baseRight.x, baseRight.y);

		batch.draw(mineLeft.getBackground(), mineLeft.x, mineLeft.y);
		batch.draw(mineRight.getBackground(), mineRight.x, mineRight.y);

		for (PuckRootClass puck : leftTeam) {
			batch.draw(puck.getBackground(), puck.x, puck.y);
		}

		for (PuckRootClass puck : rightTeam) {
			batch.draw(puck.getBackground(), puck.x, puck.y);
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

// Helper methods

	public void createAllElements() {

		leftTeam = new ArrayList<PuckRootClass>();
		leftTeam.add(puckLeft1 = new PuckRootClass("LEFT", 1));
		leftTeam.add(puckLeft1 = new PuckRootClass("LEFT", 2));
		leftTeam.add(puckLeft1 = new PuckRootClass("LEFT", 3));
		baseLeft = new BaseRootClass("LEFT", 50, 300);
		mineLeft = new MineRootClass("LEFT", 50, 50);

		rightTeam = new ArrayList<PuckRootClass>();
		rightTeam.add(puckRight1 = new PuckRootClass("RIGHT", 1));
		rightTeam.add(puckRight1 = new PuckRootClass("RIGHT", 2));
		rightTeam.add(puckRight1 = new PuckRootClass("RIGHT", 3));
		baseRight = new BaseRootClass("RIGHT", 600, 50);
		mineRight = new MineRootClass("RIGHT", 600, 300);

	}

	public void setUpThings() {

		currentInputsLeft = "";
		currentInputsRight = "";

		batch = new SpriteBatch();
		font = new BitmapFont();

		camera = new OrthographicCamera(100, 100);
		viewport = new FitViewport(100, 100, camera);

		movementSpeed = 1;

		leftTeamGold = 0;
		rightTeamGold = 100;

		// TODO How make this scale to game units?
		background = new Texture(Gdx.files.internal("background1.png"));

	}

	public void assignStartingPositions(ArrayList<PuckRootClass> pucks) {

		// TODO This, and everywhere else, set these numbers to variables to reference
		
		for (PuckRootClass puck : pucks) {
			if (puck.getNumber() == 1) {
				puck.y = 300;
			}
			if (puck.getNumber() == 2) {
				puck.y = 200;
			}
			if (puck.getNumber() == 3) {
				puck.y = 100;
			}

			if (puck.getTeam().equals("LEFT")) {
				puck.x = 250;
			} else {
				puck.x = 450;
			}

		}

	}

// Getters and Setters

}