package Launch.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Display.gfx.Animation;
import Display.gfx.Assets;
import Launch.Handler;
import Launch.entities.Entity;

public class Player extends Creatures{
	
	private boolean checkAttack = false,// Test
					aUp = false, 
					aDown = false, 
					aLeft = false, 
					aRight = false,
					runAttack = false,
					runALeft = false,
					runARight = false,
					checkSmoke = false,
					isThreadRunning = false;
	
	private int looksTowards = 0; // 0 = left, 1 = right
	
	//Animation
	
	private Animation anim_up, anim_down, anim_left, anim_right, 
					  anim_stands_left, anim_smokes_left, anim_stands_right, anim_smokes_right,
					  anim_runALeft, anim_runARight,
					  anim_aUp, anim_aDown, anim_aLeft, anim_aRight;
	
	//Animation timer
	
	private long currentTime = System.currentTimeMillis(), lastTime = currentTime;
	
	//Attack timer
	
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creatures.DEFAULT_CREATURE_WIDTH, Creatures.DEFAULT_CREATURE_HEIGTH);
		
		bounds.x = 12;
		bounds.y = 34;
		bounds.width = 38;
		bounds.height = 30;
		
		//Animation
		anim_up = new Animation(180, Assets.player_up);
		anim_down = new Animation(180, Assets.player_down);
		anim_left = new Animation(180, Assets.player_left);
		anim_right = new Animation(180, Assets.player_right);
		anim_stands_left = new Animation(600, Assets.player_stands_left);//temp
		anim_smokes_left = new Animation(300, Assets.player_smokes_left);//temp
		anim_stands_right = new Animation(600, Assets.player_stands_right);//temp
		anim_smokes_right = new Animation(300, Assets.player_smokes_right);//temp
		anim_runALeft = new Animation(100, Assets.player_runsLeft);//temp
		anim_runARight = new Animation(100, Assets.player_runsRight);//temp
		
		
		anim_aUp = new Animation(350, Assets.player_atks_up);
		anim_aDown = new Animation(350, Assets.player_atks_down);
		anim_aLeft = new Animation(350, Assets.player_atks_left);
		anim_aRight = new Animation(350, Assets.player_atks_right);
	}

	@Override
	public void update() {
		//Animation
		anim_up.tick();
		anim_down.tick();
		anim_left.tick();
		anim_right.tick();
		
		anim_stands_left.tick();//temp
		anim_smokes_left.tick();//temp
		anim_stands_right.tick();//temp
		anim_smokes_right.tick();//temp
		
		anim_runALeft.tick();
		anim_runARight.tick();
		
		
		anim_aUp.tick();//temp
		anim_aDown.tick();//temp
		anim_aLeft.tick();//temp
		anim_aRight.tick();//temp
		
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		//Attack
		//checkAttacks();
	}
	
	private void checkAttacks(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		checkAttack = true;
		
		Rectangle collisionBounds = getCollisionBounds(0, 0);
		Rectangle attackRange = new Rectangle();
		int arSize = 20;
		attackRange.width = arSize;
		attackRange.height = arSize;
		
		if(aUp){
			attackRange.x = collisionBounds.x - collisionBounds.width / 2 - arSize / 2;
			attackRange.y = collisionBounds.y - arSize;
		}else if(aDown){
			attackRange.x = collisionBounds.x - collisionBounds.width / 2 - arSize / 2;
			attackRange.y = collisionBounds.y + collisionBounds.height;
		}else if(aLeft){
			attackRange.x = collisionBounds.x - arSize;
			attackRange.y = collisionBounds.y + collisionBounds.height / 2 - arSize / 2;
		}else if(aRight){
			attackRange.x = collisionBounds.x + collisionBounds.width;
			attackRange.y = collisionBounds.y + collisionBounds.height / 2 - arSize / 2;
		}else 
			return;
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(attackRange)){
				e.hurt(1);
				return;
			}				
		}			
	}
	
	@Override
	public void die(){
		System.out.println("You lose.");
	}
	
	public void getInput(){
		if(animationTimer(350)){
			//lastTime = currentTime;
			checkAttack = false;//Test
			aUp = false;
			aDown = false;
			aLeft = false;
			aRight = false;
		}
		
		//if(animationTimer(checkSmoke, 5000))
		
		runAttack = false;
		runALeft = false;
		runARight = false;
		
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
		if(handler.getKeyManager().runALeft){
			runALeft = true;//temp
			runAttack = true; //temp
			xMove = -speed - speed;
		}			
		if(handler.getKeyManager().runARight){
			runAttack = true; //temp
			xMove = speed * 2;
		}
		if(handler.getKeyManager().aUp){//attack
			aUp = true;
			checkAttacks();
		}
		if(handler.getKeyManager().aDown){
			aDown = true;
			checkAttacks();
		}
		if(handler.getKeyManager().aLeft){
			aLeft = true;
			checkAttacks();
		}
		if(handler.getKeyManager().aRight){
			aRight = true;
			checkAttacks();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), 
								   			(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				   (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				    bounds.width, bounds.height);
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		currentTime = System.currentTimeMillis();
		
		if(runAttack){
			lastTime = currentTime;
			
			if(runALeft){
				return anim_runALeft.getCurrentFrame();
			}else {
				return anim_runARight.getCurrentFrame();
			}
		} else if(checkAttack){		
			//animationTimer(350, checkAttack, aUp, aDown, aLeft, aRight);
			
			if(aUp){
				return anim_aUp.getCurrentFrame();
			}else if(aDown){
				return anim_aDown.getCurrentFrame();
			}else if(aLeft){
				return anim_aLeft.getCurrentFrame();
			}else //if(aRight)
			{
				return anim_aRight.getCurrentFrame();
			}
		} else if(xMove != 0 || yMove != 0){
			lastTime = currentTime;
			
			if(xMove < 0){
				looksTowards = 0;
				return anim_left.getCurrentFrame();
			}else if(xMove > 0){
				looksTowards = 1;
				return anim_right.getCurrentFrame();
			}else if(yMove < 0){
				return anim_up.getCurrentFrame();
			}else //if(yMove > 0)
			{
				return anim_down.getCurrentFrame();			
			}
		} else if(animationTimer(5000)){		
			switch(looksTowards) {
				case 0:
					return anim_smokes_left.getCurrentFrame();
				case 1:
					return anim_smokes_right.getCurrentFrame();
				default:
					return anim_smokes_left.getCurrentFrame();
			}		
		} else {
			//lastTime = currentTime;
			switch(looksTowards) {
				case 0:
					return anim_stands_left.getCurrentFrame();
				case 1:
					return anim_stands_right.getCurrentFrame();
				default:
					return anim_stands_right.getCurrentFrame();
			}	
		}		
	}
	
	private void animationTimer(int timer, boolean ... list){

		if(!isThreadRunning){
			isThreadRunning = true;
			Thread t = new Thread(){
				@Override
				public void run(){
					try {
						Thread.sleep(timer);
						for(boolean b : list){
							b = false;
						}
						isThreadRunning = false;
						
					} catch (InterruptedException e) {					
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			t.start();
		}	
	}
	
	private boolean animationTimer(int timer){
		if(currentTime - lastTime < timer){
			return false;
		}else
			return true;
	}
}
