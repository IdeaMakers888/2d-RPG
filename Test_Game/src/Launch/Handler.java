package Launch;

import Display.gfx.GameCamera;
import Launch.input.KeyManager;
import Launch.input.MouseManager;
import Launch.worlds.World;

public class Handler {

	private Game game;
	private World world;
	
	Handler(Game game){
		this.game = game;
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}
	
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
