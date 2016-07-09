package Launch.entities.statics;

import java.awt.Graphics;
import java.util.Random;

import Display.gfx.Assets;
import Launch.Handler;

public class Rock extends StaticEntity{

	private int rock_type = 1;
	
	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, 100, 90);
		
		rock_type = new Random().nextInt(10);
		
//		bounds.x = 0;
//		bounds.y = 45;
//		bounds.width = 100;
//		bounds.height = 25;
		
		switch(rock_type){
			case 0:
				bounds.x = 15;
				bounds.y = 25;
				bounds.width = 77;
				bounds.height = 50;
				break;
				
			case 1:
				bounds.x = 0;
				bounds.y = 45;
				bounds.width = 100;
				bounds.height = 25;
				break;
				
			case 2:
				bounds.x = 20;
				bounds.y = 25;
				bounds.width = 77;
				bounds.height = 50;
				break;
				
			case 3:
				bounds.x = 20;
				bounds.y = 25;
				bounds.width = 77;
				bounds.height = 50;
				break;
				
			case 4:
				bounds.x = 40;
				bounds.y = 25;
				bounds.width = 77;
				bounds.height = 50;
				break;
				
			case 5:
				bounds.x = 40;
				bounds.y = 25;
				bounds.width = 57;
				bounds.height = 50;
				break;
				
			case 6:
				bounds.x = 10;
				bounds.y = 25;
				bounds.width = 77;
				bounds.height = 50;
				break;
				
			case 7:
				bounds.x = 30;
				bounds.y = 25;
				bounds.width = 77;
				bounds.height = 50;
				break;
				
			case 8:
				bounds.x = 15;
				bounds.y = 25;
				bounds.width = 77;
				bounds.height = 50;
				break;
		}
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rocks[rock_type], (int) (x - handler.getGameCamera().getxOffset()),
								 (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
