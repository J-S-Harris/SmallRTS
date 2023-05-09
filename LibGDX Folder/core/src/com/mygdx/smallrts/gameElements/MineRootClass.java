package com.mygdx.smallrts.gameElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class MineRootClass extends Rectangle {

	// Instance variables
	String team;

	// Permanent values
	Texture background;

	public MineRootClass(String team, int x, int y) {
		this.x = x;
		this.y = y;
		
		this.width = 50;
		this.height = 50;
		
		this.team = team;
		background = new Texture(Gdx.files.internal("mine.png"));
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
