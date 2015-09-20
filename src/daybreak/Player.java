package daybreak;

import static org.newdawn.slick.Input.KEY_A;
import static org.newdawn.slick.Input.KEY_D;
import static org.newdawn.slick.Input.KEY_DOWN;
import static org.newdawn.slick.Input.KEY_LEFT;
import static org.newdawn.slick.Input.KEY_RIGHT;
import static org.newdawn.slick.Input.KEY_S;
import static org.newdawn.slick.Input.KEY_UP;
import static org.newdawn.slick.Input.KEY_W;
import daybreak.weapons.*;

import org.newdawn.slick.Input;

/**
 * Represents the player.
 */
public class Player extends Entity
{
	public static final int DEFAULT_HEALTH = 15;

	//Reference to the input manager
	private Input input;

	//Time since input was last polled
	private long timeSinceLastUpdate;

	//Time (in milliseconds) between each input poll
	private static final int UPDATE_PERIOD = 100;
	
	private Weapon weapon;

	/**
	 * Creates a new player.
	 * @param map Reference to the game map.
	 */
	public Player(Tile[][] map)
	{
		super(map, "gfx/Player1.png");
		timeSinceLastUpdate = 0;

		setHealth(DEFAULT_HEALTH);
		
		weapon = new Sword();
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
		Entity e = null;
		//First, check if the player is facing the enemy
		switch (direction)
		{
		case Entity.DIRECTION_DOWN:
			if(map[posY+1][posX].entity instanceof Enemy){
				e=map[posY+1][posX].entity;
			}
			break;
		case Entity.DIRECTION_LEFT:
			if(map[posY][posX-1].entity instanceof Enemy){
				e=map[posY][posX-1].entity;
			}
			break;
		case Entity.DIRECTION_RIGHT:
			if(map[posY][posX+1].entity instanceof Enemy){
				e=map[posY][posX+1].entity;
			}
			break;
		case Entity.DIRECTION_UP:
			if(map[posY-1][posX].entity instanceof Enemy){
				e=map[posY-1][posX].entity;
			}
			break;
		}
		if(e!=null){
			e.updateHealth(weapon.damage);
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
