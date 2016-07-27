package Launch.states;

import java.awt.Graphics;

import Display.gfx.Assets;
import Display.ui.ClickListener;
import Display.ui.UIImageButton;
import Display.ui.UIManager;
import Launch.Handler;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(300, 200, 220, 71, Assets.btn_start, new ClickListener(){
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
	}	
	
	@Override
	public void update() {
		uiManager.update();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
		//Temp
		handler.getMouseManager().setUIManager(null);
		State.setState(handler.getGame().gameState);
	}

}
