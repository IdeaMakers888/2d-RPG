package Display.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	// loading assets only once
	
	private static final int width = 64, height = 64;

	public static BufferedImage grass, soil, rock_tile, tree, wood;
	public static BufferedImage[] player_up, player_down, player_left, player_right,
								  player_runsLeft, player_runsRight,
								  player_stands_left, player_smokes_left, player_stands_right, player_smokes_right,
								  player_atks_left, player_atks_right, player_atks_up, player_atks_down,
								  btn_start,
								  rocks;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sprite.png"));
		SpriteSheet player_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/bd_spriteList.png"));
		SpriteSheet btn_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/button-play.png"));
		SpriteSheet rocks_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/rocks.png"));
		SpriteSheet tree_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/tree1.png"));
		
		SpriteSheet wood_sheet = new SpriteSheet(ImageLoader.loadImage("/textures/wood.png"));

		//Items
		wood = wood_sheet.crop(0, 0, 32, 32);
		
		//Button
		btn_start = new BufferedImage[2];

		btn_start[0] = btn_sheet.crop(0, 0, 204, 71);
		btn_start[1] = btn_sheet.crop(0, 72, 204, 71);

		// Player
		
		//Stands
		player_smokes_left = new BufferedImage[5];
		
		for(int i = 0; i < 5 ; i++){
			player_smokes_left[i] = player_sheet.crop(i * 77, 0, 77, 97);
		}
		
		player_smokes_right = new BufferedImage[5];
		
		for(int i = 0; i < 5 ; i++){
			player_smokes_right[i] = player_sheet.crop(i * 77, 97, 77, 97);
		}
		
		player_stands_left = new BufferedImage[3];
		
		for(int i = 0; i < 3 ; i++){
			player_stands_left[i] = player_sheet.crop(i * 77, 194, 77, 97);
		}
		
		player_stands_right = new BufferedImage[3];
		
		for(int i = 0; i < 3 ; i++){
			player_stands_right[i] = player_sheet.crop(i * 77, 291, 77, 97);
		}
		
		//Moves
			
		player_left = new BufferedImage[5];
		
		for(int i = 0; i < 5; i++){
			player_left[i] = player_sheet.crop(i * 66, 388, 66, 88);
		}
		
		player_right = new BufferedImage[5];
		
		for(int i = 0; i < 5; i++){
			player_right[i] = player_sheet.crop(i * 66, 476, 66, 88);
		}
		player_up = new BufferedImage[5];
		player_up = player_left;

		player_down = new BufferedImage[5];
		player_down = player_right;

		//Runs
		
		player_runsLeft = new BufferedImage[8];
		
		for(int i = 0; i < 8 ; i++){
			player_runsLeft[i] = player_sheet.crop(i * 100, 564, 100, 85);
		}
		
		player_runsRight = new BufferedImage[8];
		
		for(int i = 0; i < 8 ; i++){
			player_runsRight[i] = player_sheet.crop(i * 100, 649, 100, 85);
		}
		
		//Attacks
		
		player_atks_left = new BufferedImage[2];
		
		for(int i = 0; i < 2 ; i++){
			player_atks_left[i] = player_sheet.crop(i * 100, 734, 100, 88);
		}
		
		player_atks_right = new BufferedImage[2];
		
		for(int i = 0; i < 2 ; i++){
			player_atks_right[i] = player_sheet.crop(i * 100, 822, 100, 88);
		}
		
		player_atks_up = new BufferedImage[2];
		player_atks_up = player_atks_left;
		
		player_atks_down = new BufferedImage[2];
		player_atks_down = player_atks_right;
		
		//Rocks
		rocks = new BufferedImage[9];
		
		for(int i = 0; i < 9; i++){
			rocks[i] = rocks_sheet.crop(i * 100, 0, 100, 100);
		}
		
		//Surface
		grass = sheet.crop(0, 0, width, height);
		soil = sheet.crop(width, 0, width, height);
		rock_tile = sheet.crop(0, height, width, height);
		tree = tree_sheet.crop(0, 0, width * 2, 185);
	}
}
