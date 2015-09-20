package daybreak;

import java.awt.Font;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState implements Game {

    private int playersChoice = 0;
    private static final int NOCHOICES = 5;
    private static final int STORY = 0;
    private static final int ARCADE = 1;
    private static final int LOAD = 2;
    private static final int OPTIONS = 3;
    private static final int QUIT = 4;
    private String[] playersOptions = new String[NOCHOICES];
    private boolean exit = false;
    private Font font;
    private TrueTypeFont playersOptionsTTF, foo;
    private Color notChosen = new Color(153, 204, 255);
	private Image background;
    
    public MainMenu() {
        super();
    }

//    public static void main(String[] args)
//            throws SlickException {
//        AppGameContainer app =
//                new AppGameContainer(new MainMenu());
//        app.setDisplayMode(576, 576, false);
//        app.start();
//    }

    private void renderPlayersOptions() {
        for (int i = 0; i < NOCHOICES; i++) {
            if (playersChoice == i) {
                playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions[i]);
            } else {
                playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions[i], notChosen);
            }
        }
    }

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
	    font = new Font("Verdana", Font.BOLD, 40);
        playersOptionsTTF = new TrueTypeFont(font, true);
        font = new Font ("Verdana", Font.PLAIN, 20);
        foo = new TrueTypeFont(font, true);
        playersOptions[0] = "Story";
        playersOptions[1] = "Arcade";
        playersOptions[2] = "Load";
        playersOptions[3] = "Options";
        playersOptions[4] = "Quit";
		
		try
		{
			background = new Image("gfx/menus/titleScreen.bmp");
		} catch (SlickException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sb, Graphics g) throws SlickException
	{
		g.drawImage(background, 0, 0);
		renderPlayersOptions();
	        if (exit) {
	            gc.exit();
	        }
	        
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int arg2) throws SlickException
	{
	    Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (playersChoice == (NOCHOICES - 1)) {
                playersChoice = 0;
            } else {
                playersChoice++;
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (playersChoice == 0) {
                playersChoice = NOCHOICES - 1;
            } else {
                playersChoice--;
            }
        }
        if (input.isKeyPressed(Input.KEY_ENTER)) {
            switch (playersChoice) {
            	case STORY:
            		sb.enterState(Daybreak.STORY);
            		break;
            	case QUIT:
                    exit = true;
                    break;
            }
        }
		
	}

	@Override
	public int getID()
	{
		return Daybreak.MAINMENU;
	}

	@Override
	public boolean closeRequested()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTitle()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}}

