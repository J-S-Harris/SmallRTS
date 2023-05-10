package com.mygdx.smallrts.gameElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class BaseRootClass extends Rectangle {
		
	// Instance variables
	String team;
	Texture background;
	int goldGathered;	// gather 100 to win
	
	public BaseRootClass(String team, int x, int y) {
		this.x = x;
		this.y = y;
		
		this.width = 50;
		this.height = 50;
		
		this.team = team;
		goldGathered = 0;
		
		// TODO Make sprite for bases
		this.background = new Texture(Gdx.files.internal("base.png"));
		
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

	public int getGoldGathered() {
		return goldGathered;
	}

	public void setGoldGathered(int goldGathered) {
		this.goldGathered = goldGathered;
	}
	
	

}
