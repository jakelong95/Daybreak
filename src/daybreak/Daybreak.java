package daybreak;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import daybreak.gametype.ArenaGameType;
import daybreak.gametype.StoryGameType;

public class Daybreak extends StateBasedGame
{
	
    // Game state identifiers
    public static final int MAINMENU     = 0;
    public static final int STORY        = 1;
    public static final int ARCADE       = 2;
    public static final int GAMEOVER     = 3;
    public static final int VICTORY      = 4;
    public static final int ARENA        = 5;
	
	//Width of the game
	public static final int WIDTH = 576;
	//Height of the game
	public static final int HEIGHT = 576;
	
	//Target frame rate
	public static final int FPS = 60;
	
	//Size of each tile in the map
	public static final int TILE_SIZE = 64;
	
	public static void main(String[] args)
	{
		try
		{
			//Load the game
			AppGameContainer container = new AppGameContainer(new Daybreak());
			container.setDisplayMode(WIDTH, HEIGHT, false);
			container.setTargetFrameRate(FPS);
			container.setShowFPS(false);
			container.start();
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}
	
	public Daybreak()
	{
		super("Daybreak");
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException
	{
		//Initialize all of the states for the game
		addState(new MainMenu());
		addState(new StoryGameType());
		addState(new GameOverMenu());
		addState(new VictoryMenu());
		addState(new ArenaGameType());
	}
}
