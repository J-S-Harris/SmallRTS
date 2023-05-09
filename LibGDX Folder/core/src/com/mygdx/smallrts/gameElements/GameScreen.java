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

		// TODO How make this scale to game units?
		background = new Texture(Gdx.files.internal("background.png"));

//		viewport.apply();

		check123Pressed(); // Check if 1/2/3 are pressed to see which units will be given orders this frame
		assignPuckAction(); // Update their current behaviour
		performActions();

		batch.begin();

		// After all actions are performed, draw updated positions, points, etc
		drawGameUnits();

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
		} else if (puck.getCurrentAction().equals("Move300")) {
			move300(puck);
		} else if (puck.getCurrentAction().equals("Move200")) {
			move200(puck);
		} else if (puck.getCurrentAction().equals("Move100")) {
			move100(puck);
		}

	}

	private void move100(PuckRootClass puck) {
		// TODO Auto-generated method stub

	}

	private void move200(PuckRootClass puck) {
		// TODO Auto-generated method stub

	}

	private void move300(PuckRootClass puck) {
		// TODO Auto-generated method stub

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
			System.out.println("Gold mined! " + puck.getNumber());
			puck.setGoldCurrentlyHeld(puck.getGoldCurrentlyHeld() + 1);
		} else if ((puck.getGoldCurrentlyHeld() > 0 && puck.overlaps(baseLeft))) {
			leftTeamGold++;
			System.out.println("Gold deposited! " + puck.getNumber() + " --> left gold: " + leftTeamGold);
			puck.setGoldCurrentlyHeld(puck.getGoldCurrentlyHeld() - 1);
			
		// If you have no gold at the base, or have gold at the mine: travel to the other one
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

		if (puck.x < puck.targetX) {
			puck.x += movementSpeed;
		} else {
			puck.x -= movementSpeed;
		}
		if (puck.y < puck.targetY) {
			puck.y += movementSpeed;
		} else {
			puck.y -= movementSpeed;
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

				if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
					puck.setCurrentAction("MineGold");
				} else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
					puck.setCurrentAction("AttackEnemyMine");
				} else if (Gdx.input.isKeyPressed(Input.Keys.R)) {
					puck.setCurrentAction("Move300");
				} else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
					puck.setCurrentAction("Move200");
				} else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
					puck.setCurrentAction("Move100");
				} else {
					puck.setCurrentAction("FollowInputs");
				}

			}

		}
	}

	private void check123Pressed() {

		currentInputsLeft = "";
		currentInputsRight = "";

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

//		System.out.println("LEFT: " + currentInputsLeft);
//		System.out.println("RIGHT: " + currentInputsRight);

	}

	private void listenToInput(PuckRootClass puck) {
		// TODO Auto-generated method stub

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
		baseLeft = new BaseRootClass("LEFT", 150, 200);
		mineLeft = new MineRootClass("LEFT", 50, 50);

		rightTeam = new ArrayList<PuckRootClass>();
		rightTeam.add(puckRight1 = new PuckRootClass("RIGHT", 1));
		rightTeam.add(puckRight1 = new PuckRootClass("RIGHT", 2));
		rightTeam.add(puckRight1 = new PuckRootClass("RIGHT", 3));
		baseRight = new BaseRootClass("RIGHT", 550, 200);
		mineRight = new MineRootClass("RIGHT", 650, 350);

	}

	public void setUpThings() {

		currentInputsLeft = "";
		currentInputsRight = "";

		batch = new SpriteBatch();
		font = new BitmapFont();

		camera = new OrthographicCamera(100, 100);
		viewport = new FitViewport(100, 100, camera);

		movementSpeed = 2;

		leftTeamGold = 0;
		rightTeamGold = 0;

	}

	public void assignStartingPositions(ArrayList<PuckRootClass> pucks) {

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