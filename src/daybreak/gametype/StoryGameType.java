package daybreak.gametype;

import java.awt.Font;
import java.io.FileNotFoundException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import daybreak.Daybreak;
import daybreak.Tile;
import daybreak.utils.MapParser;

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
	private boolean sixthRoom = false;
	private long corridorTime = 0; //Time we enter the corridor
	private long corridorDelay = 15000; //Number of milliseconds we make them wait for the corridor. Start at 15 seconds
	Font font;
	TrueTypeFont ttf;
	
	
	public StoryGameType()
	{
		super(85, 20);
	}

	
	@Override
	public void init()
	{
		try
		{
			map = MapParser.parseStoryMap();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		    font = new Font("Verdana", Font.BOLD, 20);
		    ttf = new TrueTypeFont(font, true);

		
		player.setMap(map);
		player.setPosition(70, 10);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sc, Graphics g) throws SlickException
	{
		super.render(gc, sc, g);
		long totalTime = 25*60*1000;
		int minuteAsMili = 60 * 1000;
		 ttf.drawString(0,0,(totalTime - elapsedTime)/minuteAsMili + ":" + String.format("%02d", (totalTime - elapsedTime) % minuteAsMili / 1000  ));
	}
	
	@Override
	public void update(int deltaTime)
	{
		 ttf.drawString(0,0, "hello world");
		
		elapsedTime += deltaTime;
		if(!secondRoom && (elapsedTime > 3 * 60 * 1000 ))//If we haven't reached the second room after 3 minutes, open the doors
		{
			//Change the first doors into walkables.
			map[9][63].setTileType(Tile.FLOOR);//.setTileType(Tile.FLOOR);
			map[12][63].setTileType(Tile.FLOOR);
			secondRoom = true;
		}
		if(!thirdRoom && (elapsedTime > 7 * 60 * 1000 ))
		{
			//Change the second doors into walkables.
			map[7][54].setTileType(Tile.FLOOR);

			thirdRoom = true;
		}
		if(!fourthRoom && (elapsedTime > 12 * 60 * 1000 ))
		{
			//Change the second doors into walkables.
			map[8][40].setTileType(Tile.FLOOR);
			map[12][40].setTileType(Tile.FLOOR);
			fourthRoom = true;
		}
		//Open the right side of the corridor
		if(!fifthRoom && (elapsedTime > 18 * 60 * 1000 ))
		{
			//Change the second doors into walkables.
			map[10][30].setTileType(Tile.FLOOR);
			corridorTime = elapsedTime;
			fifthRoom = true;
		}
		//After the delay open the left side of the corridor
		if(!sixthRoom && ((elapsedTime - corridorTime) > corridorDelay ))
		{
			map[10][16].setTileType(Tile.FLOOR);
			sixthRoom = true;
		}

		
	}

	@Override
	public int getID()
	{
		return Daybreak.STORY;
	}
}
