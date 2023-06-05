package screensEtc;

import com.badlogic.gdx.Game;

public class GameObject extends Game {

	MenuScreen menuScreen;
	int width;
	int height;
	
	public GameObject(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void create() {
		menuScreen = new MenuScreen(this, 0, width, height);
		setScreen(menuScreen);

	}
}