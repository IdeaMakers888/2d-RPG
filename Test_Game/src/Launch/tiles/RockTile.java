package Launch.tiles;

import Display.gfx.Assets;

public class RockTile extends Tile{

	public RockTile(int id) {
		super(Assets.rock_tile, id);

	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
