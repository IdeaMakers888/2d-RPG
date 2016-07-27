package Launch.entities.statics;

import java.awt.Graphics;

import Display.gfx.Assets;
import Launch.Handler;
import Launch.items.Item;

public class Tree extends StaticEntity {
	
	public static final int TILEWIDTH = 128, TILEHEIGHT = 185;

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, TILEWIDTH, TILEHEIGHT);
		
		bounds.x = 34;
		bounds.y = 140;
		bounds.width = 58;
		bounds.height = 44;
	}
	
	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x + TILEWIDTH / 2, (int) y + TILEHEIGHT / 2));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()),
								 (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
	
}
