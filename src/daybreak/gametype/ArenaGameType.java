package daybreak.gametype;

import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;




import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import daybreak.Daybreak;
import daybreak.Enemy;
import daybreak.Player;
import daybreak.Tile;
import daybreak.utils.MapParser;

/**
 * Move from room to room as time or
 * kills allow you to progress.
 */
public class ArenaGameType extends GameType
{
	private long elapsedTime = 0; //Total time in milliseconds.
	private boolean secondRoom = false; //Have we reached the second room?
	private boolean thirdRoom = false;
	private boolean fourthRoom = false;
	private boolean fifthRoom = false;
	private boolean sixthRoom = false;
	private long corridorTime = 0; //Time we enter the corridor
	private long corridorDelay = 15000; //Number of milliseconds we make them wait for the corridor. Start at 15 seconds
	long totalTime = 25*60*1000; //Time it takes to win the game.
    private ArrayList<XYPair> openDoors = new ArrayList<XYPair>();
    private int TimeSinceSpawn; //Milliseconds since last Vampire spawn.
    private boolean gameOver = false;
    private final static int SPAWNTIME = 5000;//Time between enemy spawns (milliseconds)
	Font font;
	TrueTypeFont ttf;

	
	private class XYPair
	{
		private XYPair(int newX, int newY)
		{
			x = newX;
			y = newY;
		}
		
		public int x;
		public int y;
	}
	
	
	public ArenaGameType()
	{
		super(85, 20);
	}

	
	@Override
	public void init()
	{

	}

	@Override //Render is only called when the window has focus.
	public void render(GameContainer gc, StateBasedGame sc, Graphics g) throws SlickException
	{
		super.render(gc, sc, g);
		int minuteAsMili = 60 * 1000;
		 ttf.drawString(0,0,( elapsedTime)/minuteAsMili + ":" + String.format("%02d", ( elapsedTime) % minuteAsMili / 1000  ));
		g.setColor(new Color(0,255,0));
		Rectangle rect = new Rectangle(400, 0, 10* player.getHealth(), 20); //Location of our health bar.
		 g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.setColor(new Color(255,255,255));
		g.drawRect(rect.x, rect.y, 10 *player.DEFAULT_HEALTH, 20);

		 if(gameOver)
		 {
			 sc.enterState(Daybreak.VICTORY);
		 }
		 
		 
	}
	
	@Override
	public void update(int deltaTime)
	{
		TimeSinceSpawn += deltaTime;
		elapsedTime += deltaTime;
		
		//Spawn Enemeies;
		if(TimeSinceSpawn >= SPAWNTIME)
		{
			//Enemies spawn in at a random door.
			Random random = new Random();
			int door = random.nextInt(openDoors.size());
			Enemy enemy = new Enemy(map, player);
			System.out.println("x: " + openDoors.get(door).x + " y: " + openDoors.get(door).y);
			enemy.setPosition(openDoors.get(door).x, openDoors.get(door).y);
			
			synchronized(entities)
			{
				entities.add(enemy);
			}
			
			TimeSinceSpawn = 0;
		}
		
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
	{
		

		try
		{
		map = MapParser.parseStoryMap(new File("maps/arenaMap.csv"),20,20);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		player.setHealth(player.DEFAULT_HEALTH);
		entities.clear();
		openDoors.add(new XYPair(9, 0));
		openDoors.add(new XYPair(18, 9));
		openDoors.add(new XYPair(0, 9));
		openDoors.add(new XYPair(9, 18));
		
		
		 font = new Font("Verdana", Font.BOLD, 20);
		 ttf = new TrueTypeFont(font, true);
		 gameOver = false; //Not strictly necessary, but I'm not taking chances.
		
		player.setMap(map);
		player.setPosition(0, 9);
	}

	
	@Override
	public int getID()
	{
		return Daybreak.ARENA;
	}
}
