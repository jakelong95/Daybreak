package daybreak;

import org.newdawn.slick.*;

public class Daybreak extends BasicGame
{
	public static void main(String[] args)
	{
		try
		{
			//Load the game
			AppGameContainer container = new AppGameContainer(new Daybreak());
			container.setDisplayMode(500, 500, false);
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

	/**
	 * Initializes the game. Loads resources.
	 * @param container Container for the game.
	 */
	@Override
	public void init(GameContainer container) throws SlickException
	{
		
	}
	
	/**
	 * Renders images to the screen.
	 * @param container Container for the game.
	 * @param graphics Graphics context used to render.
	 */
	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException
	{
		
	}

	/**
	 * Updates game objects.
	 * @param container Container holding the game.
	 * @param delta Amount of time that has passed since the last update in milliseconds.
	 */
	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
		
	}
}
