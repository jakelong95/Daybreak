package daybreak.gametype;

import java.io.FileNotFoundException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import daybreak.utils.mapParser;

/**
 * Move from room to room as time or
 * kills allow you to progress.
 */
public class StoryGameType extends GameType
{
	private long elapsedTime = 0; //Total time in milliseconds.
	private boolean secondRoom = false; //Have we reached the second room?
	private boolean thirdRoom = false;
	private boolean fourthRoom = false;
	private boolean fifthRoom = false;

	public StoryGameType()
	{
		super(85, 20);
	}

	@Override
	public void init()
	{
		System.out.println("spider-man, spider-man put the time into it");
		try
		{
			map = mapParser.parseStoryMap();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		player.setPosition(5, 10);
	}

	@Override
	public void update(int deltaTime)
	{
		elapsedTime += deltaTime;
		if(!secondRoom && elapsedTime > 3 * 60 * 1000 )//If we haven't reached the second room after 3 minutes, open the doors
		{
			
		}
		
		//TODO Perform game logic here
	}

	@Override
	public int getID()
	{
		return 0;
	}
}
