package daybreak;

import org.newdawn.slick.Input;

import static org.newdawn.slick.Input.*;

/**
 * Represents the player.
 * 
 * @author Jacob Long
 */
public class Player extends Entity
{
	//Reference to the input manager
	private Input input;
	
	/**
	 * Creates a new player.
	 * @param map Reference to the game map.
	 */
	public Player(Tile[][] map)
	{
		super(map);
		
		this.input = input;
	}

	//How many vamps the player has killed
	private int killCount;
	
	//TODO - Add weapons, etc.
	
	@Override
	public void update(int deltaTime)
	{
		//Check if the player is pressing any of the movement keys
		if(input.isKeyDown(KEY_W) || input.isKeyDown(KEY_UP))
		{
			//Move the player up
			--posY;
		}
		else if(input.isKeyDown(KEY_A) || input.isKeyDown(KEY_LEFT))
		{
			//Move the player left
			--posX;
		}
		else if(input.isKeyDown(KEY_S) || input.isKeyDown(KEY_DOWN))
		{
			//Move the player down
			++posY;
		}
		else if(input.isKeyDown(KEY_D) || input.isKeyDown(KEY_RIGHT))
		{
			//Move the player right
			++posX;
		}
	}
	
	/**
	 * Updates the reference to the input manager.
	 * @param input Input manager.
	 */
	public void setInput(Input input)
	{
		this.input = input;
	}
}
