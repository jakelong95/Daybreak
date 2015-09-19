package daybreak.gametype;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import daybreak.Daybreak;
import daybreak.Entity;
import daybreak.Player;
import daybreak.Tile;

/**
 * Represents a game type in Daybreak.
 */
public abstract class GameType extends BasicGameState
{
	//List of entities in the game
	protected LinkedList<Entity> entities;
	
	//Array containg the map
	protected Tile[][] map;
	
	protected Player player;
	
	public GameType()
	{
		player = new Player();
		
		entities = new LinkedList<Entity>();
		entities.add(player);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		Tile.loadTiles();
		
		init();
	}
	
	public abstract void init();
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException
	{
		//Render 100 tiles with the player at the center
		int playerX = player.getPosX();
		int playerY = player.getPosY();
		
		//Render each tile around the player
		for(int x = -5; x <=  5; ++x)
		{
			for(int y = -5; y <= 5; ++y)
			{
				graphics.drawImage(map[playerY + y][playerX + x].tileImage, (x + 5) * Daybreak.TILE_SIZE, (y + 5) * Daybreak.TILE_SIZE);
			}
		}
		
		//Draw each entity
//		for(Entity e : entities)
//		{
//			e.render();
//		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int deltaTime) throws SlickException
	{
		//Update each entity
		for(Entity e : entities)
		{
			e.update(deltaTime);
		}
		
		update(deltaTime);
	}
	
	public abstract void update(int deltaTime);
}
