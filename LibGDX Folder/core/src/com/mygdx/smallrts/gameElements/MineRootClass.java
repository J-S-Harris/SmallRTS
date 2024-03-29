package com.mygdx.smallrts.gameElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MineRootClass extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Instance variables
	String team;

	// Permanent values
	Texture background;
	
	// TODO - If this takes place on a bridge/ drawbridge, does a mine make sense?
		// Rename to something else - Coffers? Storage? Supplies?

	public MineRootClass(int x, int y) {
		this.x = x;
		this.y = y;
		
		background = new Texture(Gdx.files.internal("chestBlue48x48.png"));

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
