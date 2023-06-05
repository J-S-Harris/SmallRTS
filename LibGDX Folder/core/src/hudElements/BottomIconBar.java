package hudElements;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.FillViewport;

import screensEtc.GameScreen;

public class BottomIconBar extends FillViewport {
	
	Camera camera;
	GameScreen gameScreen;

	public BottomIconBar(GameScreen gameScreen, float worldWidth, float worldHeight) {
		super(worldWidth, worldHeight);
		this.gameScreen = gameScreen;
	}

}
