package daybreak;

import java.util.LinkedList;

/**
 * Represents a game type in Daybreak.
 * 
 * @author Jacob Long
 */
public class GameType
{
	//Entities in the game
	private LinkedList<Entity> entities;
	
	//Width and height of the map array
	protected int mapWidth;
	protected int mapHeight;
	
	//Array containing the map. The array is row-major
	int[][] map;
	
	public GameType(int width, int height)
	{
		mapWidth = width;
		mapHeight = height;
		
		map = new int[height][width];
	}
	
	/**
	 * Updates the game.
	 * @param deltaTime Time since last update in milliseconds.
	 */
	public void update(int deltaTime)
	{
		//Update each entity
		for(Entity e : entities)
		{
			e.update(deltaTime);
		}
	}
	
	/**
	 * Renders objects in the game.
	 */
	public void render()
	{
		//TODO Render the world around the player
		
		//Render each entity
		for(Entity e: entities)
		{
			e.render();
		}
	}
}
