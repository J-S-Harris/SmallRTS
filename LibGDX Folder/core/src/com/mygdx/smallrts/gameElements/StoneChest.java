package com.mygdx.smallrts.gameElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class StoneChest extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Instance variables
	String team;

	// Permanent values
	Texture background;
	
	// TODO - What is this second resource? Create method to 'mine' it. What is it used for?
		// Brick?

	public StoneChest(int x, int y) {
		this.x = x;
		this.y = y;
		
		background = new Texture(Gdx.files.internal("chestGreen48x48.png"));

		this.width = background.getWidth();
		this.height = background.getHeight();
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Texture getBackground() {
		return background;
	}

	public void setBackground(Texture background) {
		this.background = background;
	}

	// Getters and Setters
	
	
	
	
}
