package daybreak;

import static org.newdawn.slick.Input.KEY_A;
import static org.newdawn.slick.Input.KEY_D;
import static org.newdawn.slick.Input.KEY_DOWN;
import static org.newdawn.slick.Input.KEY_LEFT;
import static org.newdawn.slick.Input.KEY_RIGHT;
import static org.newdawn.slick.Input.KEY_S;
import static org.newdawn.slick.Input.KEY_UP;
import static org.newdawn.slick.Input.KEY_W;

import java.awt.Color;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Represents the player.
 */
public class Player extends Entity
{
	//Reference to the input manager
	private Input input;

	//Time since input was last polled
	private long timeSinceLastUpdate;
	
	//Time (in milliseconds) between each input poll
	private static final int UPDATE_PERIOD = 100;
	
	/**
	 * Creates a new player.
	 * @param map Reference to the game map.
	 */
	public Player(Tile[][] map)
	{
		super(map, "gfx/protagonist.bmp");
		
		timeSinceLastUpdate = 0;
	}

	//How many vamps the player has killed
	private int killCount;
	
	//TODO - Add weapons, etc.
	
	@Override
	public void update(int deltaTime)
	{
		timeSinceLastUpdate += deltaTime;
		
		//Make sure input isn't polled too often
		if(timeSinceLastUpdate < UPDATE_PERIOD)
		{
			return;
		}
		
		timeSinceLastUpdate = 0;
		
		//The new positions of the player
		int x = posX;
		int y = posY;
		
		//Check if the player is pressing any of the movement keys
		if(input.isKeyDown(KEY_W) || input.isKeyDown(KEY_UP))
		{
			//Check if the player can move up
			if(map[posY - 1][posX].canPlayerPass && map[posY - 1][posX].entity == null)
			{
				--y;
			}
		}
		else if(input.isKeyDown(KEY_S) || input.isKeyDown(KEY_DOWN))
		{
			//Check if the player can move down
			if(map[posY + 1][posX].canPlayerPass && map[posY + 1][posX].entity == null)
			{
				++y;
			}
		}
		else if(input.isKeyDown(KEY_A) || input.isKeyDown(KEY_LEFT))
		{
			//Check if the player can move left
			if(map[posY][posX - 1].canPlayerPass && map[posY][posX - 1].entity == null)
			{
				--x;
			}
		}
		else if(input.isKeyDown(KEY_D) || input.isKeyDown(KEY_RIGHT))
		{
			//Check if the player can move right
			if(map[posY][posX + 1].canPlayerPass && map[posY][posX + 1].entity == null)
			{
				++x;
			}
		}
		
		setPosition(x, y);
	}
	
	/**
	 * Updates the reference to the input manager.
	 * @param input Input manager.
	 */
	public void setInput(Input input)
	{
		this.input = input;
	}
	
	/**
	 * Renders the player's image.
	 */
	public void render()
	{
		//Always render the player at the center of the screen
		getImage().draw(4 * Daybreak.TILE_SIZE, 4 * Daybreak.TILE_SIZE);
	}
	
	/**
	 * Sets the reference to the map.
	 * @param map Reference to the map.
	 */
	public void setMap(Tile[][] map)
	{
		this.map = map;
	}
}
