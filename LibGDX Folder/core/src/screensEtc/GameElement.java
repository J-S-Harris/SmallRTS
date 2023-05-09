package screensEtc;

import com.badlogic.gdx.Game;
import com.mygdx.smallrts.gameElements.GameScreen;

public class GameElement extends Game {

	@Override
	public void create() {

		setScreen(new GameScreen());

	}

}
